package concepts.js_handle;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class AsElementTest {

    @Test
    public void testJSHandleAsElement() {

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

            // Navigate to the local HTML file
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Find the first <h1> element on the page
            JSHandle jsHandle = page.evaluateHandle("() => document.querySelector('h1')");

            // Check if the element handle is valid (optional, but recommended)
            if (jsHandle != null) {
                // Convert the JSHandle to an ElementHandle for element-specific actions
                ElementHandle elementHandle = jsHandle.asElement();

                // Ensure the ElementHandle is valid
                if (elementHandle != null) {
                    // Extract the text content of the <h1> element
                    String text = elementHandle.textContent();

                    // Print the extracted text
                    System.out.println("Text content of the <h1> element: " + text);
                }
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
