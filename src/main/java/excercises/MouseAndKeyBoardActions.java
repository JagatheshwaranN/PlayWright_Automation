package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page.ClickOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class MouseAndKeyBoardActions {

	static Playwright playwright;

	public static Page before() {

		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}

	public static void mouseAndKeyboardAction() {
		Page page = before();
		page.navigate(
				"https://vwo.com/free-trial/?utm_medium=website&utm_source=login-page&utm_campaign=mof_eg_loginpage");
		page.mouse().move(100, 100);
		page.click("#page-v1-step1-email");
		page.keyboard().type("name@yourcompany.com");
		page.keyboard().press("Enter");
		page.close();
		playwright.close();
	}

	public static void clickAndHold() {
		Page page = before();
		page.navigate("https://letcode.in/buttons");
		page.click("button:has-text('Button Hold!')", new ClickOptions().setDelay(3000));
		page.locator("'Button has been long pressed'").isVisible();
		page.close();
		playwright.close();
	}

	public static void main(String[] args) {

		clickAndHold();
		mouseAndKeyboardAction();

	}

}
