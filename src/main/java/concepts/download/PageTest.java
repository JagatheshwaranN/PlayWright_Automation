package concepts.download;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This code tests the download functionality of a web page, specifically downloading a
 * PDF file. Additionally, it retrieves and prints information about the web page where
 * the download was initiated, such as the URL and title. The script utilizes Playwright
 * to automate these browser interactions and provides proper exception handling and
 * resource cleanup.
 *
 * @author Jagatheshwaran N
 */
public class PageTest {

    @Test
    public void testDownloadPage() {
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

            // Print the URL of the associated page where the download was triggered
            System.out.println("URL of the downloaded file's page: " + download.page().url());

            // Print the title of the associated page where the download was triggered
            System.out.println("Title of the downloaded file's page: " + download.page().title());
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
