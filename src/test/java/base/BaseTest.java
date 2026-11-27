package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeEach
    public void setup() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );

        page = browser.newPage();

        page.navigate("http://localhost:3000");
    }

    @AfterEach
    public void tearDown() {

        browser.close();
        playwright.close();
    }

    protected void login() {

        page.locator("input[type='email']")
                .fill("testuser@teebay.com");

        page.locator("input[type='password']")
                .fill("123456");

        page.locator("button")
                .click();
    }
}