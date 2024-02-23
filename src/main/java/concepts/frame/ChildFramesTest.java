package concepts.frame;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.util.List;

public class ChildFramesTest {

    @Test
    public void testFrameChildFrames() {

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

            // Retrieve a list of child frames associated with the main frame
            List<Frame> framesList = page.mainFrame().childFrames();

            // Iterate through the list of child frames
            for (Frame childFrame : framesList) {

                // Print the URL of each child frame to the console
                System.out.println("Child Frame URL: " + childFrame.url());
            }
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