package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BasicAuthentication {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser
				.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
		Page page = browserContext.newPage();
		page.navigate("https://the-internet.herokuapp.com/basic_auth");
		
		page.close();
		browser.close();
		browserContext.close();
		playwright.close();

	}
}
