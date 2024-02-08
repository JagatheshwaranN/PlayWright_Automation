package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class FocusTest {

    @Test
    public void testLocatorFocus() {
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

            // Create a new isolated browser context
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the specified URL
            page.navigate("https://letcode.in/edit");

            // Focus on the element with id "join" with a timeout of 3000 milliseconds
            page.locator("#join").focus(new Locator.FocusOptions().setTimeout(3000));

            // Fill in the value "I'm fine" in the input field with id "join"
            page.locator("#join").fill("I'm fine");
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
