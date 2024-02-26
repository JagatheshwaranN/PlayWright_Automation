package concepts.frame;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class WaitForLoadStateTest {

    @Test
    public void testFrameWaitForLoadState() {

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
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/demo.html");

            // Get the main frame of the page
            Frame frame = page.mainFrame();

            // Click on the element with the id "button" within the main frame
            frame.click("#button");

            // Wait for the main frame to complete loading its content
            frame.waitForLoadState();

            // Print the title of the page to the console
            System.out.println(page.title());
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