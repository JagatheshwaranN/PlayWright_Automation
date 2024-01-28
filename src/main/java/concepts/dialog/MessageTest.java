package concepts.dialog;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This test script demonstrates how to handle JavaScript alert dialogs using Playwright.
 * It sets up a dialog listener to print the message of the dialog to the console and
 * accepts the dialog, triggered by clicking a button on the webpage.
 *
 * @author Jagatheshwaran N
 */
public class MessageTest {

    @Test
    public void testDialogMessage() {
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

            // Set up a listener for handling dialogs on the page
            page.onDialog(dialog -> {
                // Print the message of the dialog to the console
                System.out.println("Dialog Message: " + dialog.message());

                // Accept the dialog, which is equivalent to clicking the "OK" button
                dialog.accept();
            });

            // Trigger a button click that opens a JavaScript alert
            page.click("xpath=//button[@onclick='jsAlert()']");
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
