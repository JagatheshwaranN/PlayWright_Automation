package concepts.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;


public class ReloadTest {

    @Test
    public void testPageReload() {

        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        try {
            // Create Playwright instance
            playwright = Playwright.create();

            // Use Chromium as the browser type
            browserType = playwright.chromium();

            // Launch the browser
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a browser context with touch support
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigating to the specified URL
            page.navigate("https://playwright.dev/");

            // Reload the page with the specified options
            page.reload(
                    // Creating a new ReloadOptions object to customize the reloading behavior
                    new Page.ReloadOptions()
                            // Setting the waitUntil option to LOAD, ensuring the reload waits until the page has fully loaded
                            .setWaitUntil(WaitUntilState.LOAD));
        } catch (Exception ex) {
            // Print the exception stack trace for debugging
            ex.printStackTrace();
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