package concepts.frame_locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code not only tests the general functionality of navigating frames and
 * interacting with elements but specifically demonstrates the ability to navigate
 * to the Nth position of a frame using Playwright's nth method.
 * This feature is useful when dealing with multiple frames, allowing precise
 * navigation to a particular frame among the available options.
 *
 * @author Jagatheshwaran N
 */
public class NthPositionTest {

    @Test
    public void testFrameLocatorNthPosition() {

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
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/iframes.html");

            // Locate the iframe using an XPath expression targeting an iframe with the name attribute 'frame'
            FrameLocator frame = page.frameLocator("//iframe[@name='frame']");

            // Navigate to the first frame that matches the XPath expression (index 0)
            Locator userName = frame.nth(0).getByLabel("UserName");

            // Fill the located input element with the text "Brownie"
            userName.fill("Brownie");
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