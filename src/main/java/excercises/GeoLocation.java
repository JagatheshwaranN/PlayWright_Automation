package excercises;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.util.Arrays;

public class GeoLocation {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setLocale("en-Us")
				.setGeolocation(11.00904, 76.95963).setPermissions(Arrays.asList("geolocation")));
		Page page = browserContext.newPage();
		page.navigate("https://www.openstreetmap.org/");
		page.waitForLoadState();
		page.click("a[aria-label='Show My Location']");
		page.waitForTimeout(10000);
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("geolocation.png")));
		page.close();
		browserContext.close();
		playwright.close();
	}

}
