package concepts.frame;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class FrameLocatorTest {

    @Test
    public void testFrameFrameLocator() {

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

            // Get a reference to the main frame of the page
            Frame frame = page.mainFrame();

            // Use frameLocator to create a locator for the iframe with the ID "myIframe"
            FrameLocator frameLocator = frame.frameLocator("#myIframe");

            // Use locator method on frameLocator to find the input element with ID "userInput" within the iframe
            Locator userInputLocator = frameLocator.locator("#userInput");

            // Fill the input element with the text "Hello, Teddy!"
            userInputLocator.fill("Hello, Teddy!");
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

    /*

        Explanation
        ===========
        Frame frameObj = page.frame("MyFrame");

        In plain language, this line is trying to find a frame on a web page with the name
        "MyFrame" and store it in a variable called frameObj. Think of a frame as a separate
        section of a webpage; it's like a smaller window within the main browser window.

        ElementHandle frameElement = frameObj.frameElement();

        Now, this line is taking the frameObj (the frame we found) and getting its associated
        HTML element. Every frame in a webpage is represented by an HTML element (like an
        <iframe> or <frame> tag). The frameElement() method gets this HTML element and stores
        it in the frameElement variable.

        Frame contentFrame = frameElement.contentFrame();

        Finally, this line is taking the HTML element we got in the previous step (frameElement)
        and trying to find the actual content frame inside it. A content frame is like the
        interior of the frame where the actual webpage content is displayed. The contentFrame()
        method returns this content frame, and it is stored in the variable contentFrame.
    */

}