package concepts.dialog;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This test script demonstrates how to handle JavaScript alert dialogs using Playwright.
 * It sets up a dialog listener to accept the alert, retrieves the associated page, and
 * prints the title and URL of that page to the console.
 *
 * @author Jagatheshwaran N
 */
public class PageTest {

    @Test
    public void testDialogPage() {
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
                // Accept the dialog, which is equivalent to clicking the "OK" button
                dialog.accept();

                // Retrieve the page instance associated with the dialog
                Page associatedPage = dialog.page();

                // Print the title of the associated page to the console
                System.out.println("Page Title: " + associatedPage.title());

                // Print the URL of the associated page to the console
                System.out.println("Page URL: " + associatedPage.url());
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
