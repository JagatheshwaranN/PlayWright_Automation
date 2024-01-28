package concepts.dialog;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This test script demonstrates how to handle a JavaScript prompt dialog using Playwright.
 * It sets up a dialog listener to automatically accept the prompt and provides a specific
 * input ("Accept Prompt") when the prompt appears on the page.
 *
 * @author Jagatheshwaran N
 */
public class AcceptWithPromptInputTest {

    @Test
    public void testDialogAcceptWithPromptInput() {
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

            // Navigate to a page with JavaScript alerts
            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            // Set up a listener for handling dialogs on the page
            page.onDialog(dialog -> {
                // Inside the dialog event handler, use accept ("Accept Prompt") to automatically accept
                // the dialog and, if the dialog is a prompt, enter the specified text ("Accept Prompt")
                // into the prompt input field.
                dialog.accept("Accept Prompt");
            });

            // Trigger a button click that opens a JavaScript alert
            page.click("xpath=//button[@onclick='jsPrompt()']");
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
