package concepts.mouse;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.MouseButton;
import org.testng.annotations.Test;

public class DblClickTest {

    @Test
    public void testMouseDblClick() {

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

            // Locate the playwright button element on the page using an ID selector
            Locator playwrightButton = page.locator("#submit");

            // Get the bounding box (position and size) of the playwright button element
            BoundingBox pwBtnBoundingBox = playwrightButton.boundingBox();

            // Simulate a mouse double click at the center of the playwright button element
            page.mouse().dblclick(
                    pwBtnBoundingBox.x + pwBtnBoundingBox.width / 2,
                    pwBtnBoundingBox.y + pwBtnBoundingBox.height / 2,
                    new Mouse.DblclickOptions().setButton(MouseButton.LEFT)
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
