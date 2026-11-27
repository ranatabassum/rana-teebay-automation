package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import base.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    public void submitRegistrationUntilServerError() {
        RegistrationPage registrationPage = new RegistrationPage(page);
        registrationPage.open();

        String email = "rana.tabassum." + System.currentTimeMillis() + "@example.com";
        registrationPage.register(
                "Rana",
                "Tabassum",
                "Dhaka",
                email,
                "01700000000",
                "Test@12345"
        );

        assertTrue(
                registrationPage.isServerErrorVisible(),
                "Register should display the application's known server error"
        );
    }
}
