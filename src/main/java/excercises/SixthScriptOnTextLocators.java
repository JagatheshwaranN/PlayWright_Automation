package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SixthScriptOnTextLocators {

	public static void main(String ar[]) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://www.orangehrm.com/contact-sales/");
		page.locator("text=Contact Sales").click();
		Locator links = page.locator("text=Privacy Policy");
		System.out.println("Links count " + links.count());
		for (int i = 0; i < links.count(); i++) {
			String text = links.nth(i).textContent();
			if (text.contains("Service Privacy Policy")) {
				links.nth(i).click();
				break;
			}
		}

		page.navigate("https://demo.opencart.com/index.php?route=account/login");

		String header = page.locator("div.well h2:has-text('New Customer')").textContent();
		System.out.println(header);

		String logo = page.locator("'Your Store'").textContent();
		System.out.println(logo);

		browser.close();
		playwright.close();
	}

}
