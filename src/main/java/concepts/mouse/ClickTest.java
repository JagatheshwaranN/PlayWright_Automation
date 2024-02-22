package concepts.mouse;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.testng.annotations.Test;

public class ClickTest {

    @Test
    public void testMouseClick() {

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

            // Navigate to a local HTML file using the file protocol
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Locate the password input element on the page using an XPath selector
            Locator password = page.locator("//input[@title='pass']");

            // Get the bounding box (position and size) of the password input element
            BoundingBox passwordBoundingBox = password.boundingBox();

            // Simulate a mouse click at the center of the password input element
            page.mouse().click(
                    passwordBoundingBox.x + passwordBoundingBox.width / 2,
                    passwordBoundingBox.y + passwordBoundingBox.height / 2
            );
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
