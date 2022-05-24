package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SixteenthScriptWithDynamicWebTableHandle {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();

		page.navigate("https://datatables.net/extensions/select/examples/initialisation/checkbox.html");

		Locator row = page.locator("table#example tr");
		row.locator(":scope", new Locator.LocatorOptions().setHasText("Angelica Ramos")).locator(".select-checkbox")
				.click();
		row.locator(":scope").allInnerTexts().forEach(e -> System.out.println(e));

		page.navigate("https://www.primefaces.org/primeng/table");

		browser.close();
		playwright.close();
	}

}
