package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductUpdateTest extends BaseTest {

    @Test
    public void updateProduct() {
        login();

        ProductPage productPage = new ProductPage(page);
        productPage.openMyProducts();

        String originalTitle = "UPDATE_AUTOMATION_" + System.currentTimeMillis();
        productPage.clickAddProductButton();
        page.waitForTimeout(2000);
        String updatedTitle = originalTitle + "_UPDATED";
        try {
            productPage.createProduct(
                    originalTitle,
                    "Product created before update",
                    "900",
                    "90"
            );
            page.waitForTimeout(2000);

            productPage.openEditProduct(originalTitle);
            productPage.updateProduct(
                    updatedTitle,
                    "Updated by Playwright Automation",
                    "1200",
                    "120"
            );

            productPage.openMyProducts();
            assertTrue(productPage.isProductVisible(updatedTitle));
        } finally {
            if (!page.url().contains("my-products")) {
                productPage.openMyProducts();
            }
            if (productPage.isProductVisible(updatedTitle)) {
                productPage.deleteProduct(updatedTitle);
            } else if (productPage.isProductVisible(originalTitle)) {
                productPage.deleteProduct(originalTitle);
            }
        }
    }
}
