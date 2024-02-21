package concepts.frame_locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 *  The code tests Playwright's ability to interact with iframes and locate an
 *  input element within the iframe by its associated placeholder value.
 *  It then fills the located input element with the text "Chocolate".
 *  The browser is launched in non-headless mode for visual inspection, and proper
 *  resource cleanup is implemented in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class GetByPlaceholderTest {

    @Test
    public void testFrameLocatorGetByPlaceholder() {

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

            // Navigate to a local HTML file using the file:// protocol
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/iframe.html");

            // Locate the iframe with the ID "myIframe"
            FrameLocator frame = page.frameLocator("#myIframe");

            // Locate an input element within the iframe using its associated placeholder value
            Locator teddyInput = frame.getByPlaceholder("Teddy");

            // Fill the located input element with the text "Chocolate"
            teddyInput.fill("Chocolate");
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