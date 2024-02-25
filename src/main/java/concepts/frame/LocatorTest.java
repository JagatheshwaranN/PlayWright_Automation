package concepts.frame;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class LocatorTest {

    @Test
    public void testFrameLocator() {

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

            // Retrieve the frame with the name "MyFrame" from the page
            Frame frame = page.frame("MyFrame");

            // Create a locator for the element with the ID "#userInput" in the frame
            Locator frameElement = frame.locator("#userInput");

            // Fill the element with the text "ShadowEye"
            frameElement.fill("ShadowEye");
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