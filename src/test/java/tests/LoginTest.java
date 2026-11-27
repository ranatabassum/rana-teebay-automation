package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin(){

        LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                "testuser@teebay.com",
                "123456"
        );

        page.waitForTimeout(5000);
    }
}