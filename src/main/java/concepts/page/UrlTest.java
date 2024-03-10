package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class UrlTest {

    @Test
    public void testPageUrl() {
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

            // Create a new page instance within the browser context
            Page page = browserContext.newPage();

            // Send the page to the Playwright documentation for Java
            page.navigate("https://playwright.dev/java");

            // Get the url of the current page.
            String url = page.url();

            // Print the url to the console.
            System.out.println(url);
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
