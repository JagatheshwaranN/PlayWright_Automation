package excercises;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FifthScriptWithLocators {

	public static void main(String ar[]) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/");

		// Single Locator
		Locator userName = page.locator("#txtUsername");
		userName.click();
		userName.fill("Admin");

		page.navigate("https://academy.naveenautomationlabs.com/");
		Locator loginButton = page.locator("text=Login").first();
		loginButton.click();

		// Multiple Locators
		page.navigate("https://www.orangehrm.com/hris-hr-software-demo/");
		Locator countryOptions = page.locator("select#Form_submitForm_Country option");
		System.out.println(countryOptions.count());

		System.out.println("***************************************************");

		for (int i = 0; i < countryOptions.count(); i++) {
			String text = countryOptions.nth(i).textContent();
			System.out.println(text);
		}

		System.out.println("***************************************************");

		List<String> optionTextList = countryOptions.allTextContents();
		for (String text : optionTextList) {
			System.out.println(text);
		}

		System.out.println("***************************************************");
		optionTextList.forEach(ele -> System.out.println(ele));

		browser.close();
		playwright.close();
	}

}
