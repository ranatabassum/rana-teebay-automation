package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductCreateTest extends BaseTest {

    @Test
    public void createProduct() {

        login();

        ProductPage productPage =
                new ProductPage(page);

        productPage.openMyProducts();

        productPage.clickAddProductButton();

        page.waitForTimeout(2000);

        String title = "AUTOMATION_CREATE_" + System.currentTimeMillis();
        try {
            productPage.createProduct(

                    title,
                    "Created by Playwright Automation",
                    "1000",
                    "100"
            );

            page.waitForTimeout(3000);

            assertTrue(page.url().contains("my-products"));
            assertTrue(productPage.isProductVisible(title));
        } finally {
            if (page.url().contains("my-products") && productPage.isProductVisible(title)) {
                productPage.deleteProduct(title);
            }
        }

    }
}