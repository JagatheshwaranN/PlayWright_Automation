package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * The code sets up a browser context, navigates to Google's homepage, prints the
 * page title, captures the storage state, and then closes the browser and releases
 * resources.The primary focus is to capture the storage state of the browser
 * context after interacting with a page.
 * <p>
 * The storageState() methods in the Playwright Java API are used to capture the
 * current state of browser storage within a specific browser context. Browser
 * storage includes data such as cookies, local storage, and session storage. This
 * captured state can later be used to restore the browser to the same storage state.
 *
 * @author Jagatheshwaran N
 */
public class StorageStateTest {

    @Test
    public void testBrowserContextStorageState() {
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

            // Send the page to the Google home page
            page.navigate("https://www.google.com/");

            // Get the title of the current page.
            String title = page.title();

            // Print the title to the console.
            System.out.println(title);

            // Create an instance of StorageStateOptions to configure storage state capture
            BrowserContext.StorageStateOptions options = new BrowserContext.StorageStateOptions();

            // Set the path where the storage state data will be saved (use Paths.get for a file path)
            options.setPath(Paths.get("storage//storage_data.json"));

            // Capture the storage state of the current browser context with the specified options
            browserContext.storageState(options);
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
