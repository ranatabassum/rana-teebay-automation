package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccountSettingsPage {

    private final Page page;

    public AccountSettingsPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.getByText("Account Settings", new Page.GetByTextOptions().setExact(true)).click();
        page.waitForURL("**/account-settings");
        page.locator("input[name='first_name']").waitFor();
    }

    public String getFirstName() {
        return field("first_name").inputValue();
    }

    public void setFirstName(String firstName) {
        field("first_name").fill(firstName);
    }

    public void update() {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Update")
        ).click();
    }

    public void reload() {
        page.reload();
        page.locator("input[name='first_name']").waitFor();
    }

    private Locator field(String name) {
        return page.locator("input[name='" + name + "']");
    }
}
