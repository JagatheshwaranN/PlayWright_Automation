package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 *  The code is to demonstrate how to use Playwright to launch a browser, set up a tracing
 *  session with specific options, navigate a page, and then save the tracing data to a ZIP
 *  file for further analysis. It also includes error handling to manage exceptions and
 *  ensures proper resource cleanup.
 *
 * @author Jagatheshwaran N
 */
public class TracingTest {

    @Test
    public void testBrowserContextTracing() {
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

            // Create a new browser context to isolate the tracing session
            BrowserContext browserContext = browser.newContext();

            // Start tracing with detailed options
            browserContext.tracing().start(new Tracing.StartOptions()
                    // Capture screenshots during the trace
                    .setScreenshots(true)
                    // Capture browser viewport snapshots during the trace
                    .setSnapshots(true)
                    // Include JavaScript source files for debugging
                    .setSources(true)
                    // Set a descriptive name for the trace
                    .setName("Tracing"));

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the target URL
            page.navigate("https://playwright.dev/java/");

            // Stop tracing and save the trace data to a ZIP file
            browserContext.tracing().stop(new Tracing.StopOptions()
                    // Specify the path to save the trace file
                    .setPath(Paths.get("src/main/java/concepts/browser_context/tracing/trace.zip")));
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
