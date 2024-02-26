package concepts.frame;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class ParentFrameTest {

    @Test
    public void testFrameParentFrame() {

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

            // Get the frame named "MyFrame" from the 'page' object
            Frame frame = page.frame("MyFrame");

            // Obtain the parent frame of the previously obtained frame
            Frame parentFrame = frame.parentFrame();

            // Get the title of the parent frame
            String pageTitle = parentFrame.title();

            // Print the title of the parent frame to the console
            System.out.println(pageTitle);
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