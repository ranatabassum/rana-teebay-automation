package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String email, String password){

        page.locator("input[type='email']").fill(email);
        page.locator("input[type='password']").fill(password);

        page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON
        ).click();
    }
}