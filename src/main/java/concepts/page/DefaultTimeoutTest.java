package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class DefaultTimeoutTest {

    @Test
    public void testPageDefaultTimeoutTest() {
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

            // Create a new browser context to manage pages and settings
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Set a default timeout for various operations within this page
            page.setDefaultTimeout(5000);  // Wait up to 5 seconds for most operations

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
