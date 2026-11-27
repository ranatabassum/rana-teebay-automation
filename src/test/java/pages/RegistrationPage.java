package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RegistrationPage {

    private final Page page;

    public RegistrationPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("http://localhost:3000/register");
        page.locator("input[name='firstName']").waitFor();
    }

    public void register(
            String firstName,
            String lastName,
            String address,
            String email,
            String phoneNumber,
            String password
    ) {
        page.locator("input[name='firstName']").fill(firstName);
        page.locator("input[name='lastName']").fill(lastName);
        page.locator("input[name='address']").fill(address);
        page.locator("input[name='email']").fill(email);
        page.locator("input[name='phoneNumber']").fill(phoneNumber);
        page.locator("input[name='password']").fill(password);
        page.locator("input[name='confirmPassword']").fill(password);
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Register")
        ).click();
    }

    public boolean isServerErrorVisible() {
        return page.getByText(
                "Internal error occurred. Please check the server!",
                new Page.GetByTextOptions().setExact(true)
        ).isVisible();
    }
}
