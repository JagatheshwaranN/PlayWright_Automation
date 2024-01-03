package concepts.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeBrowserTest {

    Playwright playwright;

    Browser browser;

    Page page;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        page = browser.newPage();
    }

    @Test
    public void testChromeBrowser() {
        String expectedTitle = "Google";
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
