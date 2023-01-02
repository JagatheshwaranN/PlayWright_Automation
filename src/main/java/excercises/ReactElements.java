package excercises;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ReactElements {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.netflix.com/in/");

		Locator searchbar = page.locator("_react=Anonymous[name='email'] >> input").first();
		searchbar.click();
		searchbar.fill("Movies");

		Locator footerContent = page.locator("_react=UIMarkup[data-uia='data-uia-footer-label']");
		List<String> footerText = footerContent.allTextContents();
		footerText.stream().forEach(ele -> System.out.println(ele));

		browser.close();
		playwright.close();

	}

}
