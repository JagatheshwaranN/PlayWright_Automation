package concepts.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * This code serves as a demonstration of using Playwright to trace browser
 * activities within a Chromium browser instance. It starts tracing, navigates
 * to a specific page, retrieves and prints the page title, stops the tracing,
 * and closes the browser. Error handling and resource cleanup are incorporated
 * for a smooth and clean test execution environment.
 *
 * @author Jagatheshwaran N
 */
public class TracingTest {

    @Test
    public void testBrowserTracing() {
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

            // Create a new page with a base URL set to https://playwright.dev/.
            Page page = browser.newPage(new Browser.NewPageOptions().setBaseURL("https://playwright.dev/"));

            // Start tracing and save output to a JSON file
            browser.startTracing(page, new Browser.StartTracingOptions()
                    .setPath(Paths.get("D:\\Environment_Collection\\Intellij_Env\\Playwright_Concepts\\trace.json")));

            // Navigate to the "java" page (presumably relative to a base URL)
            page.navigate("java");

            // Retrieve the title of the current page
            String title = page.title();

            // Print the title to the console
            System.out.println(title);

            // Stop tracing and finalize the trace file
            browser.stopTracing();
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
