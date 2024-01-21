package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code is to demonstrate how to use Playwright to launch a browser, create a browser
 * context, set up an event listener to automatically accept dialogs within that context,
 * create a new page, trigger a demo alert (which would be handled by the event listener),
 * and then close the browser context. This illustrates the automation of handling browser
 * dialogs using Playwright.
 *
 * @author Jagatheshwaran N
 */
public class OnDialogTest {

    @Test
    public void testBrowserContextOnDialog() {
        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        try {
            // Create a Playwright instance
            playwright = Playwright.create();

            // Select Chromium as the browser type
            browserType = playwright.chromium();

            // Launch a browser in non-headless mode (visible window).
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a separate browsing session within the browser
            BrowserContext browserContext = browser.newContext();

            // Set up an event listener handler to automatically accept dialogs in the browser context
            browserContext.onDialog(Dialog::accept);

            // Create a new page within the browser context
            Page page = browserContext.newPage();

            // Trigger a demo alert within the page
            page.evaluate("alert('This is a demo alert!')");

            // Close the browser context
            browserContext.close();
        } catch (Exception e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
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
