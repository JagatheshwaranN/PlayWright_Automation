package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class DragToTest {

    @Test
    public void testLocatorDragTo() {
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
            page.navigate("https://letcode.in/dropable");

            // Locate the source element to be dragged
            Locator dragSource = page.locator("#draggable");

            // Locate the destination element where the source will be dropped
            Locator dragDestination = page.locator("#droppable");

            // Perform the drag-and-drop operation from the source to the destination
            dragSource.dragTo(dragDestination);

            // Locate the message element after the drag-and-drop operation
            Locator dragMessage = page.locator("//div[@id='droppable']//p");

            // Print the text content of the message element
            System.out.println(dragMessage.textContent());
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
