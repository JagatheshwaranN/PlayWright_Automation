package concepts.js_handle;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class EvaluateHandleTest {

    @Test
    public void testJSHandleAsEvaluateHandle() {

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
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Locate an element on the page with ID "playwright"
            JSHandle jsHandle = page.evaluateHandle("() => document.getElementById('playwright')");

            // Check if the element exists
            if (jsHandle != null) {
                // Extract the text content of the element
                JSHandle textHandle = jsHandle.evaluateHandle("ele => ele.textContent");
                String text = textHandle.toString();

                // Print the extracted text content
                System.out.println("Text content of the element: " + text);
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
