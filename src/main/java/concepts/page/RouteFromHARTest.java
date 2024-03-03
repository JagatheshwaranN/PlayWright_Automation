package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;


public class RouteFromHARTest {

    @Test
    public void testPageRouteFromHAR() {
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

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Route network requests based on a pre-recorded HAR file
            // - Paths.get() constructs a file path object
            // - bookcart.har contains captured network interactions
            // - RouteFromHAROptions specifies behavior:
            //   - setUpdate(false) prevents HAR updates during test
            page.routeFromHAR(
                    Paths.get("src/main/java/concepts/browser_context/har/bookcart.har"),
                    new Page.RouteFromHAROptions().setUpdate(false)
            );

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
