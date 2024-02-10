package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class GetByTextTest {

    @Test
    public void testLocatorGetByText() {
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

            // Check if an element with text "Playwright Examples" is visible
            page.getByText("Playwright Examples").isVisible();

            // Check if an element with text "Examples" is visible
            page.getByText("Examples").isVisible();

            // Check if an element with exact text "Playwright" is visible (case-sensitive)
            page.getByText("Playwright", new Page.GetByTextOptions().setExact(true)).isVisible();

            // Find an element using a regex pattern and check if it is visible
            page.getByText(Pattern.compile("Automation")).isVisible();

            // Find an element using a case-insensitive regex pattern and check if it is visible
            page.getByText(Pattern.compile("^automation$", Pattern.CASE_INSENSITIVE)).isVisible();
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
