package concepts.frame;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

public class NavigateTest {

    @Test
    public void testFrameNavigate() {

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

            // Navigate the frame to the specified URL with custom options
            // Here, it waits until the network is idle before considering the navigation as successful
            Response response = frame.navigate("https://www.londonfreelance.org/courses/frames/index.html",
                    new Frame.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

            // Print the status code of the response to the console
            System.out.println(response.status());
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