package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SelectElementThatContainsOtherElement {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.flipkart.com/");
		Locator footerSection = page.locator(
				"div._2Brcj4:has(a[href='https://www.flipkartcareers.com/?otracker=undefined_footer_navlinks'])");
		footerSection.allInnerTexts().forEach(e -> System.out.println(e));

		browser.close();
		playwright.close();
	}

}
