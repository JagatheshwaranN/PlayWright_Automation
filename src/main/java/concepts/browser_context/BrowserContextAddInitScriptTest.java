package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;


/**
 * This Java code demonstrates how to use Playwright's Java API to launch a browser,
 * create a browser context, inject a script to modify the page behavior, navigate
 * to a website, and perform basic interactions with the page while handling
 * potential exceptions and properly closing resources.
 * <p>
 * The addInitScript method is used to inject a script into the page before other
 * scripts execute.
 * The injected script modifies the page's appearance by setting the background color
 * of the body element to green (document.body.style.backgroundColor = 'green';).
 * <p>
 * addInitScript method is pivotal in this scenario as it allows injecting a script
 * to manipulate the page's appearance or behavior before any other scripts are executed.
 * <p>
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextAddInitScriptTest {

    @Test
    public void testBrowserContextAddInitScript() {
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

            // Create a new isolated browser context.
            BrowserContext browserContext = browser.newContext();

            // Access the browser context object
            browserContext.addInitScript(
                    // Inject a script to execute before any page scripts
                    "document.body.style.backgroundColor = 'green';" // Set the background color to green
            );

            // Create a new page within the context.
            Page page = browserContext.newPage();

            // Navigate the page to the specified URL.
            page.navigate("http://www.example.com/");

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

