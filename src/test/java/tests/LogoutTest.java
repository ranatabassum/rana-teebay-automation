package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.LogoutPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {

    @Test
    public void cancelLogoutKeepsUserSignedIn() {
        login();

        LogoutPage logoutPage = new LogoutPage(page);
        logoutPage.requestLogout();
        assertTrue(logoutPage.isConfirmationVisible());

        logoutPage.cancelLogout();

        assertFalse(logoutPage.isConfirmationVisible());
        assertTrue(logoutPage.isAuthenticated());
    }

    @Test
    public void confirmingLogoutEndsSession() {
        login();

        LogoutPage logoutPage = new LogoutPage(page);
        logoutPage.requestLogout();
        logoutPage.confirmLogout();

        page.waitForURL("**/");
        assertFalse(logoutPage.isAuthenticated());
    }
}
