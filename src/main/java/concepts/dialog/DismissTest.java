package concepts.dialog;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This test script automates the interaction with a webpage containing JavaScript alerts
 * using the Playwright library. It demonstrates how to set up a dialog listener to
 * automatically handle and dismiss dialogs, such as alerts, during the browser automation
 * process.
 *
 * @author Jagatheshwaran N
 */
public class DismissTest {

    @Test
    public void testDialogDismiss() {
        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        try {
            // Create Playwright instance
            playwright = Playwright.create();

            // Use Chromium as the browser type
            browserType = playwright.chromium();

            // Launch the browser
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new isolated browser context
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to a page with JavaScript alerts
            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            // Set up a listener to automatically dismiss all dialogs
            page.onDialog(Dialog::dismiss);

            // Trigger a button click that opens a JavaScript confirm alert
            page.click("xpath=//button[@onclick='jsConfirm()']");
        } catch (Exception ex) {
            // Print the exception stack trace for debugging
            ex.printStackTrace();
        } finally {
            // Close the browser if it's running
            if (browser != null) {
                browser.close();
            }

            // Close the Playwright object to release resources
            if (playwright != null) {
                playwright.close();
            }
        }
    }

}
