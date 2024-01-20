package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * The code demonstrates a simple Playwright test scenario where browser
 * interactions are controlled, and network requests are routed based on
 * a pre-recorded HAR file. The test outputs the title of the page to the
 * console. The script includes proper exception handling and cleanup in
 * the finally block to ensure resources are released even in case of
 * exceptions.
 * <p>
 * In Playwright's Java API, the BrowserContext.routeFromHAR methods are
 * used to intercept and route network requests based on a pre-recorded HAR
 * (HTTP Archive) file. HAR files contain a log of network requests and
 * responses, and by using these methods, you can instruct Playwright to mimic
 * the captured network interactions during your automation tests.
 *
 * @author Jagatheshwaran N
 */
public class RouteFromHARTest {

    @Test
    public void testBrowserContextRouteFromHAR() {
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

            // Create a new browser context to isolate test environment
            BrowserContext browserContext = browser.newContext();

            // Route network requests based on a pre-recorded HAR file
            // - Paths.get() constructs a file path object
            // - bookcart.har contains captured network interactions
            // - RouteFromHAROptions specifies behavior:
            //   - setUpdate(false) prevents HAR updates during test
            browserContext.routeFromHAR(
                    Paths.get("src/main/java/concepts/browser_context/har/bookcart.har"),
                    new BrowserContext.RouteFromHAROptions().setUpdate(false)
            );

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the specified URL
            page.navigate("https://bookcart.azurewebsites.net/");

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
