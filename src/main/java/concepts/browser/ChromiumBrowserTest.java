package concepts.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.*;

public class ChromiumBrowserTest {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        page = browser.newPage();
    }

    @Test
    public void testChromiumBrowser() {
        String expectedTitle = "Google";
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        page = browser.newPage();
        page.navigate("https://www.google.com/");
        String actualTitle = page.title();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterTest
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }

}
