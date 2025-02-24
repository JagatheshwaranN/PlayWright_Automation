package concepts.download;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This code is designed to test the download functionality of a web page, specifically
 * focusing on handling download failures.Checks if the download failed by examining
 * the failure property of the Download object.Checks for download interruptions, and
 * provides appropriate feedback messages based on the outcome of the download.
 *
 * @author Jagatheshwaran N
 */
public class FailureTest {

    @Test
    public void testDownloadFailure() {
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
            page.navigate("https://freetestdata.com/document-files/pdf/");

            // Set up an event listener for the 'download' event defines the action to wait for and download the file.
            Download downloadFile = page.waitForDownload(() -> {

                // Click the link containing the specific PDF we want to download.
                page.click("//a[contains(@href,'Free_Test_Data_10.5MB_PDF.pdf')]");
            });

            // Check if the download failed by looking for a non-null failure object.
            boolean flag = downloadFile.failure() != null;

            if (flag) {
                // Download interrupted, print an error message and the failure details.
                System.out.println("File download got interrupted");
                System.out.println(downloadFile.failure());
            } else {
                // Download successful, print a success message.
                System.out.println("No interruption in file download");
            }

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
