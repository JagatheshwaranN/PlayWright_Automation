package concepts.download;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 *  This code tests the download functionality of a web page, specifically a PDF file.
 *  It uses Playwright to automate the browser interactions, retrieves the URL of the
 *  downloaded file, and prints this URL to the console. The script includes proper
 *  exception handling and resource cleanup.
 *
 * @author Jagatheshwaran N
 */
public class UrlTest {

    @Test
    public void testDownloadUrl() {
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

            // Initiate a download and wait for it to complete
            Download download = page.waitForDownload( () -> {

                // Trigger the download by clicking the link to the PDF
                page.click("//a[contains(@href,'Free_Test_Data_10.5MB_PDF.pdf')]");
            });

            // Get the URL of the downloaded file using the download.url() method
            String downloadFileUrl = download.url();

            // Print the download URL to the console
            System.out.println("Download URL of the downloaded file: " + downloadFileUrl);
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
