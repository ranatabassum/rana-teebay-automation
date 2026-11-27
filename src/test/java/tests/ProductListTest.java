package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductListTest extends BaseTest {

    @Test
    public void readMyProductsList() {
        login();

        ProductPage productPage = new ProductPage(page);
        productPage.openMyProducts();
        page.waitForTimeout(2000);

        assertTrue(page.url().contains("my-products"));
        assertTrue(page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new com.microsoft.playwright.Page.GetByRoleOptions().setName("Add Product")
        ).isVisible());
    }
}
