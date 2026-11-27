package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class ProductPage {

    private final Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    public void openMyProducts() {
        page.getByText("My Products").click();
    }

    public void clickAddProductButton() {
        page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Add Product")
        ).click();
    }

    public void enterTitle(String title) {
        page.locator("input[name='title']").fill(title);
    }

    public void enterDescription(String description) {
        page.locator("textarea[name='description']").fill(description);
    }

    public void enterPurchasePrice(String price) {
        page.locator("input[name='purchase_price']").fill(price);
    }

    public void enterRentPrice(String price) {
        page.locator("input[name='rent_price']").fill(price);
    }

    public void clickAddProduct() {
        System.out.println("CLICKING ADD PRODUCT");

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Add Product")
        ).click();
    }

    public void deleteProduct(String title) {
        Locator titleLocator = page.getByText(
                title,
                new Page.GetByTextOptions().setExact(true)
        ).first();

        Locator deleteButton = titleLocator
                .locator("xpath=ancestor::div[button][1]")
                .locator("button")
                .first();

        deleteButton.click();
        page.getByText("Are you sure you want to delete this product?")
                .waitFor();
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Yes, delete")
        ).click();
        titleLocator.waitFor(new Locator.WaitForOptions().setState(
                com.microsoft.playwright.options.WaitForSelectorState.DETACHED
        ));
    }

    public void openEditProduct(String title) {
        page.getByText(title, new Page.GetByTextOptions().setExact(true))
                .first()
                .click();
        page.waitForURL("**/edit-product/**");
    }

    public void updateProduct(
            String title,
            String description,
            String purchasePrice,
            String rentPrice
    ) {
        enterTitle(title);
        enterDescription(description);
        enterPurchasePrice(purchasePrice);
        enterRentPrice(rentPrice);

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Add Product")
        ).click();
    }

    public boolean isProductVisible(String title) {
        return page.getByText(
                title,
                new Page.GetByTextOptions().setExact(true)
        ).count() > 0;
    }

    public void selectCategory() {
        page.locator("div[name='categories']").click();
        page.getByText("Electronics").click();
    }

    public void selectRentDuration() {

        page.locator("div[name='rent_duration_type']")
                .click();

        page.getByRole(
                com.microsoft.playwright.options.AriaRole.OPTION,
                new Page.GetByRoleOptions().setName("Daily")
        ).click();
    }

    public void createProduct(
            String title,
            String description,
            String purchasePrice,
            String rentPrice
    ) {

        enterTitle(title);

        selectCategory();

        enterDescription(description);

        enterPurchasePrice(purchasePrice);

        enterRentPrice(rentPrice);

        selectRentDuration();


        clickAddProduct();
    }
}