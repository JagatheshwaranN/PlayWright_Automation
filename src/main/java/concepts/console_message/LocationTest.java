package concepts.console_message;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code's primary focus is on using the Playwright API to capture console messages and
 * their corresponding source locations. The message.location() method is employed to
 * retrieve and log the source location of each console message, providing insight into where
 * the messages originated in the HTML file. This can be valuable for debugging and
 * understanding the context of console messages in the browser automation script.
 *
 * @author Jagatheshwaran N
 */
public class LocationTest {

    @Test
    public void testConsoleMessageLocation() {
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

            // Register a listener for console messages within the Page object
            page.onConsoleMessage(message -> {
                // Log the content of the console message
                System.out.println("Console Message: " + message.text());

                // Retrieve and log the location of the console message's source
                System.out.println("Console Message Location: " + message.location());
            });

            // Navigate to the specified HTML file
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/console.html");

            // Perform the action that triggers the console message
            page.click("#console");
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

