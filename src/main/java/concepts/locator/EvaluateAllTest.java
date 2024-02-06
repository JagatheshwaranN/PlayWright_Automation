package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class EvaluateAllTest {

    @Test
    public void testLocatorEvaluateAll() {
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

            // Navigate to the local HTML file using the file:/// protocol
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Locate all elements with role "listitem" on the page
            Locator unorderedList = page.getByRole(AriaRole.LISTITEM);

            // Evaluate a JavaScript function on all matching elements to check if the total number of list items is equal to 6
            // The evaluateAll method returns a boolean indicating whether the condition is met or not
            boolean totalListItems = (boolean) unorderedList.evaluateAll("(li, expected) => li.length == expected", 6);

            // Print the result to the console
            System.out.println(totalListItems);
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
