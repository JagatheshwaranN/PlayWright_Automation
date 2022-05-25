package excercises;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SeventeenthScriptOnAutomaticLogin {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://admin-demo.nopcommerce.com/login");
		page.fill("#Email", "admin@yourstore.com");
		page.fill("#Password", "admin");
		page.click(".button-1.login-button");
		browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("orangeHRMlogin.json")));
		browser.close();
		playwright.close();

	}

}
