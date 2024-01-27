package concepts.console_message;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The primary focus of the code is on using Playwright's ConsoleMessage.page() method
 * to retrieve the associated Page object for each console message. This allows for
 * additional actions or inspections on the page related to specific console messages,
 * providing more context and facilitating debugging in browser automation scripts. In
 * the example, the page title is logged for each console message's source page.
 *
 * @author Jagatheshwaran N
 */
public class PageTest {

    @Test
    public void testConsoleMessagePage() {
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

                // Retrieve and log the page title of the console message's source
                System.out.println("Console Message Page Title: " + message.page().title());
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

