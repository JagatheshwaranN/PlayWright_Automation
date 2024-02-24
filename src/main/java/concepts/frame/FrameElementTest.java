package concepts.frame;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;


public class FrameElementTest {

    @Test
    public void testFrameFrameElement() {

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

            // Find a frame on the web page with the name "MyFrame" and store it in a variable called frameObj
            Frame frameObj = page.frame("MyFrame");

            // Get the HTML element associated with the frame and store it in a variable called frameElement
            ElementHandle frameElement = frameObj.frameElement();

            // Find the content frame inside the frameElement and store it in a variable called contentFrame
            Frame contentFrame = frameElement.contentFrame();

            // Check if frameObj and contentFrame refer to the same frame (print true if they do, false otherwise)
            System.out.println(frameObj == contentFrame);

            // Print the HTML element associated with the frame
            System.out.println(frameElement.asElement());
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