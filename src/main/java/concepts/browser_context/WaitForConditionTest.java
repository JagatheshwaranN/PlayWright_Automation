package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code demonstrates a basic Playwright test where a browser is launched,
 * a specific page is navigated to, a condition related to an element's visibility
 * is waited upon, and then the title of the page is retrieved and printed. The code
 * includes proper exception handling and resource cleanup in the finally block.
 * <p>
 * The waitForCondition() method is used to wait for a condition to be true before
 * proceeding. The condition is defined as the visibility of an element with the
 * CSS selector #subscribe_email on the page.
 *
 * @author Jagatheshwaran N
 */
public class WaitForConditionTest {

    @Test
    public void testBrowserContextWaitForCondition() {
        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        try {
            // Create a Playwright instance
            playwright = Playwright.create();

            // Select Chromium as the browser type
            browserType = playwright.chromium();

            // Launch a browser in non-headless mode (visible window).
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a separate browsing session within the browser
            BrowserContext browserContext = browser.newContext();

            // Set up a blank page within the established context
            Page page = browserContext.newPage();

            // Send the page to the Playwright documentation for Java
            page.navigate("https://automationexercise.com/");

            /*
                Other possible scenarios
                ========================
                browserContext.waitForCondition(() -> page.textContent(".my-text").equals("New text"));
                browserContext.waitForCondition(() -> page.url().endsWith("success"));
            */

            // Pause test execution until the specified element becomes visible within the browser context
            browserContext.waitForCondition(
                    // Define the condition to wait for using a JavaScript predicate function
                    () -> {
                        // Check if the element with ID "subscribe_email" is visible on the page
                        return page.isVisible("#subscribe_email");
                    }
            );

            // Get the title of the current page.
            String title = page.title();

            // Print the title to the console.
            System.out.println(title);
        } catch (Exception e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
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
