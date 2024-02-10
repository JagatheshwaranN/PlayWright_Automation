package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GetByTitleTest {

    @Test
    public void testLocatorGetByTitle() {
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

            // Use getByTitle to find an element with the title "google"
            Locator googleLink = page.getByTitle("google");

            // Use assertThat to perform assertions on the found element's text
            assertThat(googleLink).hasText("GoogleLink");
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
