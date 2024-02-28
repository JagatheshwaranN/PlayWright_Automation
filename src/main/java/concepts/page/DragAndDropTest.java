package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class DragAndDropTest {

    @Test
    public void testPageDragAndDrop() {
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

            // Perform a drag-and-drop operation from the element with id "draggable" to the element with id "droppable"
            page.dragAndDrop("#draggable", "#droppable");

            // Locate the message element after the drag-and-drop operation
            Locator dragMessage = page.locator("//div[@id='droppable']//p");

            // Print the text content of the message element
            System.out.println(dragMessage.textContent());

            Thread.sleep(4000);
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
