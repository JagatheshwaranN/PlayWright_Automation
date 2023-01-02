package excercises;

import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator.LocatorOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Has_HasText {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("file:///D:/Environment_Collection/Cypress_Env/SupportFiles/Select.html");
		page.locator(".filter-option").click();
		page.locator(".dropdown-menu")
				.locator("li", new LocatorOptions().setHas(page.locator("a span")).setHasText(Pattern.compile("^Playwright$")))
				.click();		
		page.waitForTimeout(5000);
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();

	}

}
