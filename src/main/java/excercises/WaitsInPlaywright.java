package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.WaitUntilState;

public class WaitsInPlaywright {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium()
				.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
		BrowserContext browserContext = browser.newContext();
		browserContext.setDefaultTimeout(5000);
		Page page = browserContext.newPage();

		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login",
				new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
		page.fill("input[name='username']", "Admin");
		page.fill("input[name='password']", "admin123");
		page.click(".oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button");
		page.locator("img[alt='client brand banner']")
				.waitFor(new WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.isVisible("img[alt='client brand banner']");
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();

	}

}
