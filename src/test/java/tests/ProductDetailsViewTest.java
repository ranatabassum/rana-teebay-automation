package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ProductBrowsePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetailsViewTest extends BaseTest {

    @Test
    public void openingProductDetailsIncrementsViews() {
        login();

        ProductBrowsePage browsePage = new ProductBrowsePage(page);
        browsePage.open();

        String title = "Last of Us Part II PS5 game";
        int viewsBeforeOpening = browsePage.openProductDetails(title);

        assertTrue(page.url().matches(".*/product-details/\\d+$"));
        assertTrue(
                browsePage.getProductDetailsViewCount() > viewsBeforeOpening,
                "Opening product details should increment the view count"
        );
    }
}
