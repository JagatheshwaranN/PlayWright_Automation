package concepts.browser_type;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code demonstrates a basic Playwright test scenario, launching a Chromium browser,
 * navigating to a web page, retrieving and printing the page title, and handling exceptions.
 * The code also includes proper cleanup procedures to close the browser and release resources.
 *
 * @author Jagatheshwaran N
 */
public class LaunchTest {

    @Test
    public void testBrowserTypeLaunch() {
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
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));

            // Create a new page in the browser
            Page page = browser.newPage();

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
