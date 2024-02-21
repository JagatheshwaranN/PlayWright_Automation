package concepts.js_handle;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class DisposeTest {

    @Test
    public void testJSHandleDispose() {

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

            // Find an element with ID "google" using JavaScript evaluation
            JSHandle jsHandle = page.evaluateHandle("() => document.getElementById('google')");

            // Check if the element exists (optional, but recommended)
            if (jsHandle != null) {
                // Extract the text content of the element using another JavaScript evaluation
                Object elementText = jsHandle.evaluate("element => element.innerText");

                // Print the extracted text
                System.out.println("Text content of the element: " + elementText);
            }
            // Release the reference to the JavaScript object for garbage collection
            assert jsHandle != null;
            jsHandle.dispose();
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
