package concepts.mouse;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.MouseButton;
import org.testng.annotations.Test;

public class UpTest {

    @Test
    public void testMouseUp() {

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
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/mouse.html");

            // Locate the element with the ID "mouse" on the page and get its bounding box
            BoundingBox paraElement = page.locator("#mouse").boundingBox();

            // Move the mouse to the center of the bounding box of the "mouse" element
            page.mouse().move(
                    paraElement.x + paraElement.width / 2,
                    paraElement.y + paraElement.height / 2
            );

            // Simulate a mouse button press (left mouse button) at the center of the "mouse" element
            page.mouse().down(new Mouse.DownOptions().setButton(MouseButton.LEFT));

            // Simulate a mouse button release (left mouse button) at the center of the "mouse" element
            page.mouse().up(new Mouse.UpOptions().setButton(MouseButton.LEFT));
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
