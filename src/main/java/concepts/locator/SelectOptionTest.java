package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.Test;

public class SelectOptionTest {

    @Test
    public void testLocatorSelectOption() {
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
            page.navigate("https://letcode.in/dropdowns");

            // Locate the single dropdown element using its CSS selector
            Locator singleDropdown = page.locator("#fruits");

            // Select the option "Pine Apple" from the single dropdown
            singleDropdown.selectOption("Pine Apple");

            // Verify and print the selection message for the single dropdown
            verifySelectionMessage(page);

            // Select the option at index 1 from the single dropdown
            singleDropdown.selectOption(new SelectOption().setIndex(1));

            // Verify and print the selection message for the single dropdown
            verifySelectionMessage(page);

            // Select the option with the value "1" from the single dropdown
            singleDropdown.selectOption(new SelectOption().setValue("1"));

            // Verify and print the selection message for the single dropdown
            verifySelectionMessage(page);

            // Select the option with the label "Orange" from the single dropdown
            singleDropdown.selectOption(new SelectOption().setLabel("Orange"));

            // Verify and print the selection message for the single dropdown
            verifySelectionMessage(page);

            // Locate the multi-select dropdown element using its CSS selector
            Locator multiDropdown = page.locator("#superheros");

            // Select the options "The Avengers" and "Batman" from the multi-select dropdown
            multiDropdown.selectOption(new String[]{"The Avengers", "Batman"});

            // Verify and print the selection message for the multi-select dropdown
            verifySelectionMessage(page);
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

    // Method to verify and print the selection message for a dropdown
    public void verifySelectionMessage(Page page) {
        // Locate the selection message element using its XPath
        Locator selectionMessage = page.locator("//p[@class='subtitle']");

        // Check if the selection message element is visible
        selectionMessage.isVisible();

        // Print the text content of the selection message element
        System.out.println(selectionMessage.textContent());
    }

}
