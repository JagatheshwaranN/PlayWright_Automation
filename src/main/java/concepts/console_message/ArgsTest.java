package concepts.console_message;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code demonstrates the usage of consoleMessage.args() within the listener for
 * console messages. This method retrieves the arguments passed to a console message,
 * allowing for the extraction and examination of data associated with console logs.
 * In the provided example, the code prints the JSON values of the arguments, showcasing
 * how to analyze and utilize information from console messages in a Playwright
 * automation script.
 *
 * @author Jagatheshwaran N
 */
public class ArgsTest {

    @Test
    public void testConsoleMessageArgs() {
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

            // Set up a listener for console messages
            page.onConsoleMessage(message -> {

                // Iterate through the arguments and print their JSON values
                for (JSHandle arg : message.args()) {
                    System.out.println("Argument: " + arg.jsonValue());
                }
            });

            // Trigger a console message with arguments using page.evaluate
            page.evaluate("console.log('Hello from console!', 5, {key: 'value'});");
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

