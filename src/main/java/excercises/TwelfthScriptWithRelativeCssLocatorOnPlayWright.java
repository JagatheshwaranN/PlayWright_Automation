package excercises;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TwelfthScriptWithRelativeCssLocatorOnPlayWright {

	public static void main(String ar[]) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://selectorshub.com/xpath-practice-page/");

		page.locator("input[type='checkbox']:left-of(:text('John.Smith'))").first().click();
		String userrole = page.locator("td:right-of(:text('John.Smith'))").first().textContent();
		System.out.println(userrole);
		String userabove = page.locator("a:above(:text('John.Smith'))").first().textContent();
		System.out.println(userabove);
		String userbelow = page.locator("a:below(:text('John.Smith'))").first().textContent();
		System.out.println(userbelow);

		Locator nearbyElements = page.locator("td:near(:text('John.Smith'),100)");
		List<String> nearElements = nearbyElements.allTextContents();
		for (String element : nearElements) {
			System.out.println(element);
		}

		browser.close();
		playwright.close();
	}

}
