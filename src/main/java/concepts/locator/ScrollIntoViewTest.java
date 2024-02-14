package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class ScrollIntoViewTest {

    @Test
    public void testLocatorScrollIntoView() {
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

            // Navigate to the specified URL
            page.navigate("https://playwright.dev/java/");

            // Locate the image element with a source containing 'VSCode'
            Locator vsCode = page.locator("//img[contains(@src,'VSCode')]");

            // Scroll the page to make the VSCode image visible in the viewport
            vsCode.scrollIntoViewIfNeeded();

            // Check if the VSCode image is visible after scrolling
            System.out.println("VSCode Image is Visible? : " + vsCode.isVisible());
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
