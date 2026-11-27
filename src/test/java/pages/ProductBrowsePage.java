package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductBrowsePage {

    private final Page page;

    public ProductBrowsePage(Page page) {
        this.page = page;
    }

    public void open() {
        page.getByText("Browse Products", new Page.GetByTextOptions().setExact(true)).click();
        page.waitForURL("**/browse-products");
    }

    public void searchByTitle(String title) {
        page.locator("input[name='title']").fill(title);
        applyFilters();
    }

    public void searchByCategory(String category) {
        page.locator("div[name='category']").click();
        page.getByRole(
                AriaRole.OPTION,
                new Page.GetByRoleOptions().setName(category)
        ).click();
        applyFilters();
    }

    public void enableBuyFilter() {
        setFilterEnabled("is_buy_filter_turned_on", true);
    }

    public void setRentRange(String minimum, String maximum) {
        setFilterEnabled("is_rent_filter_turned_on", true);
        page.locator("input[name='min_rent_range']").fill(minimum);
        page.locator("input[name='max_rent_range']").fill(maximum);
    }

    public void applyFilters() {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Filter")
        ).click();
    }

    public boolean isProductVisible(String title) {
        return page.getByText(
                title,
                new Page.GetByTextOptions().setExact(true)
        ).first().isVisible();
    }

    public boolean isBuyFilterEnabled() {
        return page.locator("input[name='is_buy_filter_turned_on']").isChecked();
    }

    public boolean hasNoProductsMessage() {
        return page.getByText(
                "No products to display",
                new Page.GetByTextOptions().setExact(true)
        ).isVisible();
    }

    public int getBrowseViewCount(String title) {
        String text = page.locator("body").innerText();
        Matcher matcher = Pattern.compile(
                Pattern.quote(title) + "(?s).*?(\\d+)\\s+views"
        ).matcher(text);
        if (!matcher.find()) {
            throw new IllegalStateException("Could not find view count for product: " + title);
        }
        return Integer.parseInt(matcher.group(1));
    }

    public int openProductDetails(String title) {
        int viewsBeforeOpening = getBrowseViewCount(title);
        Locator productTitle = page.getByText(
                title,
                new Page.GetByTextOptions().setExact(true)
        ).first();
        productTitle.click();
        page.waitForURL("**/product-details/**");
        page.getByText("PRODUCT DETAILS", new Page.GetByTextOptions().setExact(true)).waitFor();
        return viewsBeforeOpening;
    }

    public int getProductDetailsViewCount() {
        Matcher matcher = Pattern.compile("Views:\\s*(\\d+)")
                .matcher(page.locator("body").innerText());
        if (!matcher.find()) {
            throw new IllegalStateException("Product details page did not show a view count");
        }
        return Integer.parseInt(matcher.group(1));
    }

    private void setFilterEnabled(String name, boolean enabled) {
        Locator checkbox = page.locator("input[name='" + name + "']");
        if (checkbox.isChecked() != enabled) {
            checkbox.locator("xpath=..").click();
        }
    }
}
