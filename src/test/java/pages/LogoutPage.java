package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LogoutPage {

    private final Page page;

    public LogoutPage(Page page) {
        this.page = page;
    }

    public void requestLogout() {
        page.getByText("Logout", new Page.GetByTextOptions().setExact(true)).click();
        page.getByText("Are you sure you want to log out?")
                .waitFor();
    }

    public void cancelLogout() {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Cancel")
        ).click();
    }

    public void confirmLogout() {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Yes I am sure!")
        ).click();
    }

    public boolean isConfirmationVisible() {
        return page.getByText("Are you sure you want to log out?").isVisible();
    }

    public boolean isAuthenticated() {
        return page.getByText(
                "Logout",
                new Page.GetByTextOptions().setExact(true)
        ).count() > 0;
    }
}
