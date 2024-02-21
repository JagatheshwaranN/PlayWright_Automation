package concepts.file_chooser;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code tests Playwright's ability to interact with a file chooser element
 * on a webpage.
 * It launches a Chromium browser with touch support, navigates to a specific
 * page with a file upload demo, triggers a file chooser dialog, and checks the
 * visibility of the file upload button inside the dialog.
 * The browser is launched in non-headless mode for visual inspection, and proper
 * resource cleanup is implemented in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class ElementTest {

    @Test
    public void testFileChooserElement() {

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

            // Create a browser context with touch support
            BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setHasTouch(true));

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the file upload demo page
            page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

            // Wait for a file chooser dialog to appear after clicking on an element with id "filesToUpload"
            FileChooser fileChooser = page.waitForFileChooser(() -> page.locator("#filesToUpload").click());

            // Get the element handle representing the file upload button inside the file chooser dialog
            ElementHandle fileUpload = fileChooser.element();

            // Check if the file upload button is visible
            System.out.println("FileUpload Button is visible? : " + fileUpload.asElement().isVisible());
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