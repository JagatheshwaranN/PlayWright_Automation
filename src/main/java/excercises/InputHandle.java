package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class InputHandle {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();

		page.navigate("https://letcode.in/edit");

		page.type("#fullName", "Playwright");

		Locator append = page.locator("#join");
		append.focus();
		page.keyboard().press("End");
		append.type(" human");

		String textValFromInput = page.getAttribute("input[id='getMe']", "value");
		System.out.println(textValFromInput);

		page.fill("#clearMe", "");

		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}

}
