package concepts.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Media;
import org.testng.annotations.Test;

import java.nio.file.Path;

public class PdfTest {

    @Test
    public void testPagePdf() {

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
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));

            // Create a browser context with touch support
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to a local HTML file using the file:// protocol
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Get a reference to a specific frame with the name "MyFrame"
            page.emulateMedia(new Page.EmulateMediaOptions().setMedia(Media.SCREEN));

            page.pdf(new Page.PdfOptions().setPageRanges("1").setPath(Path.of("./src/main/java/concepts/page/_pdf/page.pdf")));
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