package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductDeleteTest extends BaseTest {

    @Test
    public void deleteProduct() {
        login();

        ProductPage productPage = new ProductPage(page);
        productPage.openMyProducts();
        page.waitForTimeout(2000);

        productPage.clickAddProductButton();
        page.waitForTimeout(2000);

        String title = "DELETE_AUTOMATION_PRODUCT_" + System.currentTimeMillis();
        productPage.createProduct(
                title,
                "Product created to verify delete",
                "500",
                "50"
        );
        page.waitForTimeout(3000);

        productPage.deleteProduct(title);

        assertFalse(
                page.getByText(
                        title,
                        new com.microsoft.playwright.Page.GetByTextOptions().setExact(true)
                ).count() > 0
        );
    }
}
