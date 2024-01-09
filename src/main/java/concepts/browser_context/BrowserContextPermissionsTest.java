package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.util.List;


/**
 * This Java code demonstrates how to create a browser context, grant specific
 * permissions to an origin within that context, navigate a page, interact with
 * it, and then clear the granted permissions once the operations are completed.
 * This approach allows testing how a web application behaves under different
 * permission scenarios.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextPermissionsTest {

    @Test
    public void testBrowserContextPermissions() {
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

            // Create a new isolated browser context.
            BrowserContext browserContext = browser.newContext();

            // Grant specific permissions to a given origin (https://www.example.com)
            browserContext.grantPermissions(
                    List.of("geolocation", "notifications", "clipboard-write", "camera"),
                    new BrowserContext.GrantPermissionsOptions().setOrigin("https://www.example.com"));

            // Create a new page within the context.
            Page page = browserContext.newPage();

            // Navigate the page to the specified URL.
            page.navigate("http://www.example.com/");

            // Get the title of the current page.
            String title = page.title();

            // Print the title to the console.
            System.out.println(title);

            // Clear the granted permissions for the context
            browserContext.clearPermissions();
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

