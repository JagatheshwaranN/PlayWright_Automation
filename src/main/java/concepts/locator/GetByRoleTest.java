package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class GetByRoleTest {

    @Test
    public void testLocatorGetByRole() {
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

            // Locate the submit button using the ARIA role "button"
            Locator submitButton = page.getByRole(AriaRole.BUTTON);

            // Print whether the Playwright Submit Button is displayed or not
            System.out.println("Playwright Submit Button is displayed: " + submitButton.isVisible());

            // Locate the checkbox using the ARIA role "checkbox"
            Locator genderCheckBox = page.getByRole(AriaRole.CHECKBOX);

            // Check the checkbox
            genderCheckBox.check();

            // Check if a heading with the role "heading" and name "An Unordered HTML List" is visible
            boolean listHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("An Unordered HTML List")).isVisible();

            // Print whether the Unordered List Heading is displayed or not
            System.out.println("Unordered List Heading is displayed: " + listHeading);
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
