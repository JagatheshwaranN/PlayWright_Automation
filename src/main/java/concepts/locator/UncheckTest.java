package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class UncheckTest {

    @Test
    public void testLocatorUncheck() {
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

            // Navigate to a local HTML file
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Locate the checkbox on the page using its ARIA role
            Locator checkbox = page.getByRole(AriaRole.CHECKBOX);

            // Check the checkbox with the option to force the action (setForce(false) means not forcing)
            checkbox.check(new Locator.CheckOptions().setForce(false));

            // Check if the checkbox is checked after the check operation
            boolean isChecked = checkbox.isChecked();

            // Print the result of checking the checkbox
            System.out.println("After Check, Is checkbox checked? : " + isChecked);

            // Uncheck the checkbox with the option to force the action (setForce(true) means forcing)
            checkbox.uncheck(new Locator.UncheckOptions().setForce(true));

            // Check if the checkbox is checked after the uncheck operation
            isChecked = checkbox.isChecked();

            // Print the result of unchecking the checkbox
            System.out.println("After Uncheck, Is checkbox checked? : " + isChecked);
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
