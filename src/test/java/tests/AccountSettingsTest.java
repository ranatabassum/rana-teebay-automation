package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.AccountSettingsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountSettingsTest extends BaseTest {

    @Test
    public void updateAccountInformation() {
        login();

        AccountSettingsPage accountSettings = new AccountSettingsPage(page);
        accountSettings.open();

        String originalFirstName = accountSettings.getFirstName();
        String updatedFirstName = originalFirstName + " QA";

        try {
            accountSettings.setFirstName(updatedFirstName);
            accountSettings.update();
            accountSettings.reload();

            assertEquals(updatedFirstName, accountSettings.getFirstName());
        } finally {
            accountSettings.setFirstName(originalFirstName);
            accountSettings.update();
        }
    }
}
