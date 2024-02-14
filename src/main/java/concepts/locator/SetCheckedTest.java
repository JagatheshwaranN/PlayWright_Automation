package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class SetCheckedTest {

    @Test
    public void testLocatorIsChecked() {
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

            // Locate a checkbox element by its ARIA role
            Locator genderCheckBox;

            // Get the checkbox element by its ARIA role using AriaRole.CHECKBOX constant
            genderCheckBox = page.getByRole(AriaRole.CHECKBOX);

            // Set the checkbox to the specified state (true in this case, meaning checked)
            genderCheckBox.setChecked(true);

            // Re-locate the checkbox element after it has been checked
            genderCheckBox = page.getByRole(AriaRole.CHECKBOX);

            // Check if the checkbox is currently checked
            boolean isChecked = genderCheckBox.isChecked();

            // Print the result to the console
            System.out.println("Is checkbox checked? : " + isChecked);
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
