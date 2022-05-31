package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TwentyFourthScriptOnMouseAndKeyBoardActions {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate(
				"https://vwo.com/free-trial/?utm_medium=website&utm_source=login-page&utm_campaign=mof_eg_loginpage");
		page.mouse().move(100, 100);
		page.click("#page-v1-step1-email");
		page.keyboard().type("name@yourcompany.com");
		page.keyboard().press("Enter");
		browser.close();
		playwright.close();
		
	}

}
