package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.Test;

public class WaitForTest {

    @Test
    public void testLocatorWaitFor() {
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
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            // Locate the username input element on the page
            Locator usernameInput = page.locator("input[name='username']");

            // Wait for the username input to be visible
            usernameInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            // Check if the username input is visible
            boolean isVisible = usernameInput.isVisible();

            // Print the visibility status of the username input to the console
            System.out.println("Is UserName input visible? : " + isVisible);
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
