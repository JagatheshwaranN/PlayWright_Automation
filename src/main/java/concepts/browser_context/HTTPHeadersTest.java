package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * This test is to showcase how to set custom HTTP headers for all requests
 * within a Playwright browser context, which can be useful for scenarios
 * where you need to include specific headers (like "Test: Automation") with
 * every request made by pages within that context.
 *
 * @author Jagatheshwaran N
 */
public class HTTPHeadersTest {

    @Test
    public void testBrowserContextHTTPHeaders() {
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

            // Create a map to store custom HTTP headers
            Map<String, String> headers = new HashMap<>();

            // Add a custom header "Test" with the value "Automation"
            headers.put("Test", "Automation");

            // Inject the headers into all requests within the browser context
            browserContext.setExtraHTTPHeaders(headers);

            // Set up a blank page within the established context
            Page page = browserContext.newPage();

            // Send the page to the Playwright documentation for Java
            page.navigate("https://playwright.dev/java");

            // Get the title of the current page.
            String title = page.title();

            // Print the title to the console.
            System.out.println(title);
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
