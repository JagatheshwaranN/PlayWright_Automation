package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class EighteenthScriptWithJSbasedPopups {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept();
		});
		String result;
		page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		page.click("//button[text()='Click for JS Alert']");
		result = page.locator("#result").textContent();
		System.out.println(result);
		page.click("//button[text()='Click for JS Confirm']");
		result = page.locator("#result").textContent();
		System.out.println(result);
		
		page = browserContext.newPage();
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept("Prompt Alert Test");
		});
		page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		page.click("//button[text()='Click for JS Prompt']");
		result = page.locator("#result").textContent();
		System.out.println(result);
		
		browser.close();
		playwright.close();

	}

}
