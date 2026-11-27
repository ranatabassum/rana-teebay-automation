package tests;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ProductBrowsePage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSearchFiltersTest extends BaseTest {

    private ProductBrowsePage browsePage;

    @BeforeEach
    public void openBrowsePage() {
        login();
        browsePage = new ProductBrowsePage(page);
        browsePage.open();
    }

    @Test
    public void filtersProductsByTitle() {
        browsePage.searchByTitle("Blender");

        assertTrue(browsePage.isProductVisible("Blender"));
        assertFalse(browsePage.isProductVisible("Ikea couch"));
    }

    @Test
    public void filtersProductsByCategory() {
        browsePage.searchByCategory("Toys");

        assertTrue(browsePage.isProductVisible("Funshine bear"));
        assertFalse(browsePage.isProductVisible("Ikea couch"));
    }

    @Test
    public void filtersProductsByRentRange() {
        browsePage.setRentRange("1000", "1000");
        browsePage.applyFilters();

        assertTrue(browsePage.isProductVisible("Ikea couch"));
        assertFalse(browsePage.isProductVisible("Funshine bear"));
    }

    @Test
    public void filtersProductsAvailableToBuy() {
        browsePage.enableBuyFilter();
        assertTrue(browsePage.isBuyFilterEnabled());
        browsePage.applyFilters();

        assertTrue(browsePage.hasNoProductsMessage());
    }
}
