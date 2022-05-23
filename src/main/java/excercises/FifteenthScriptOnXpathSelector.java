package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FifteenthScriptOnXpathSelector {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.amazon.in/");
		page.locator("xpath=//input[@id='twotabsearchtextbox']").fill("mobiles");

		page.navigate("https://selectorshub.com/xpath-practice-page/");
		page.locator("//a[text()='John.Smith']/parent::td/preceding-sibling::td/input[@type='checkbox']").click();
		Locator checkboxes = page.locator("//table[@id='resultTable']//input[@type='checkbox']");
		for (int i = 0; i < checkboxes.count(); i++) {
			checkboxes.nth(i).click();
		}
		browser.close();
		playwright.close();
	}

}
