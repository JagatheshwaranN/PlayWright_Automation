package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code is to demonstrate how to use Playwright to launch a browser, navigate a page,
 * set up an event listener for the browser context's "close" event, and then close the
 * browser context. The onClose event handler prints a message to the console when the
 * browser context is closed, showcasing the ability to perform actions upon context
 * closure.
 *
 * @author Jagatheshwaran N
 */
public class OnCloseTest {

    @Test
    public void testBrowserContextOnClose() {
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

            // Set up a blank page within the established context
            Page page = browserContext.newPage();

            // Send the page to the Playwright documentation for Java
            page.navigate("https://playwright.dev/java");

            // Get the title of the current page.
            String title = page.title();

            // Print the title to the console.
            System.out.println(title);

            // Register an event listener handler for the browser context's "close" event
            browserContext.onClose(handler -> {
                // This code will execute when the context is closed
                System.out.println("Browser Context Closed");  // Log a message to the console
            });

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
