package scenarios;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class PopupAfterRefreshTest {
    @Test
    void testPopupButtonEnabling() {

        Playwright playwright = null;

        BrowserType browserType;

        Browser browser = null;

        try {

            playwright = Playwright.create();

            browserType = playwright.chromium();

            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext browserContext = browser.newContext();

            Page page = browserContext.newPage();

            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/popup2.html");

            Locator refreshButton = page.locator("#refreshButton");

            Locator popupButton = page.locator("#popupButton");

            long startTime = System.currentTimeMillis();

            long endTime = startTime + 2 * 60 * 1000; // End time set to 2 minutes from start

            while (System.currentTimeMillis() < endTime) {
                refreshButton.click();
                page.waitForTimeout(30 * 1000); // Wait for 30 seconds using Playwright's waitForTimeout
            }

            refreshButton.click();

            Assert.assertFalse(popupButton.isDisabled(), "Popup button should be enabled after 2 minutes");

            page.onDialog(dialog -> {
                String text = dialog.message();
                System.out.println(text);
                System.out.println(dialog.type());
                dialog.accept();
            });

            popupButton.click();

        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {
            if (browser != null) {
                browser.close();
            }
            if (playwright != null) {
                playwright.close();
            }
        }
    }
}
