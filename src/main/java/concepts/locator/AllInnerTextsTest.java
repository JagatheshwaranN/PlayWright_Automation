package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.List;

/**
 * The code sets up Playwright, launches a Chromium browser, navigates to a local
 * HTML file, extracts and prints text content from elements with a specific role,
 * and then cleans up by closing the browser and Playwright.The script is structured
 * with exception handling to manage errors during execution.
 * <p>
 * In Playwright, the locator.allInnerTexts() method is used to retrieve the inner
 * text content of all the elements that match a given locator.
 * Additionally, it returns a list of strings, where each string corresponds to the
 * inner text of an individual element found on the page.
 *
 * @author Jagatheshwaran N
 */
public class AllInnerTextsTest {

    @Test
    public void testLocatorAllInnerTexts() {
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

            // Create a new isolated browser context
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the demo page
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Get a list of text content from all elements with the Aria role LINK on the page
            List<String> linkTextList = page.getByRole(AriaRole.LINK).allInnerTexts();

            // Iterate through the list of link text and print each one using a lambda expression
            linkTextList.forEach(System.out::println);
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
