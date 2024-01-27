package concepts.console_message;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The primary focus of the code is on using ConsoleMessage.type() to retrieve the type
 * of the console message. This type is then logged to the standard output. In the
 * provided example, the console message triggered using page.evaluate will typically
 * have a type like "log," indicating a standard log message.
 * <p>
 * The code showcases a simple setup for capturing and logging the type of console messages
 * in a Playwright browser automation script.
 *
 * @author Jagatheshwaran N
 */
public class TypeTest {

    @Test
    public void testConsoleMessageType() {
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

            // Create a new isolated browser context.
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context.
            Page page = browserContext.newPage();

            // Attach a listener to the Page object for console messages
            page.onConsoleMessage(message -> {

                // Print the type of the captured console message to the standard output
                System.out.println("Console Message Type: " + message.type());
            });

            // Trigger a console message with arguments using page.evaluate
            page.evaluate("console.log('Hello from console!');");
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

