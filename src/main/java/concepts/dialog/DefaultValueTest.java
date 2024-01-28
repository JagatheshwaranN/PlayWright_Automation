package concepts.dialog;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This test script demonstrates how to handle a JavaScript prompt dialog using Playwright.
 * It sets up a dialog listener to print the default value of the prompt and accepts the
 * prompt, which is triggered by executing JavaScript with a specified message and default
 * value.
 *
 * @author Jagatheshwaran N
 */
public class DefaultValueTest {

    @Test
    public void testDialogDefaultValue() {
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

            // Set up a listener for handling dialogs on the page
            page.onDialog(dialog -> {
                // Print the default value of the prompt dialog to the console
                System.out.println("Default Value of Prompt: " + dialog.defaultValue());

                // Accept the dialog, which is equivalent to clicking the "OK" button
                dialog.accept();
            });

            // Use page.evaluate() to trigger a JavaScript prompt with a specified message
            // ("This is demo prompt") and default value ("Hello")
            page.evaluate("prompt('This is demo prompt','Hello')");
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
