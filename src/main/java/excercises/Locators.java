package excercises;

import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Locators {

	static Playwright playwright;

	public static void main(String ar[]) {
		singleLocator();
		multipleLocator();
		filter();
		focus();
		getAttrDtls();
		highLight();
	}

	public static void singleLocator() {
		Page page = before();
		page.navigate("https://opensource-demo.orangehrmlive.com/");
		// Id
		Locator userName = page.locator("#txtUsername");
		userName.click();
		userName.fill("Admin");
		// Text
		page.navigate("https://academy.naveenautomationlabs.com/");
		Locator loginButton = page.locator("text=Login").first();
		loginButton.click();
		playwright.close();
	}

	public static void multipleLocator() {
		Page page = before();
		page.navigate("https://opensource-demo.orangehrmlive.com/");
		// CSS
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
		// Java 8 Feature
		optionTextList.forEach(ele -> System.out.println(ele));
		playwright.close();
	}

	public static void filter() {
		Page page = before();
		page.navigate("https://www.techlistic.com/p/demo-selenium-practice.html");
		page.locator("#customers").locator("tr").filter(new Locator.FilterOptions().setHasText("Google"))
				.filter(new Locator.FilterOptions().setHasText("Germany"))
				.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("filterElement.png")));
		playwright.close();
	}

	public static void focus() {
		Page page = before();
		page.navigate("https://letcode.in/edit");
		page.locator("#join").focus(new Locator.FocusOptions().setTimeout(3000));
		playwright.close();
	}

	public static void getAttrDtls() {
		Page page = before();
		page.navigate("https://letcode.in/edit");
		String value = page.locator("#join").getAttribute("value");
		System.out.println("Value Attribute's value is : " + value);
		playwright.close();
	}

	public static void highLight() {
		Page page = before();
		page.navigate("https://letcode.in/edit");
		page.locator("#join").highlight();
		playwright.close();
	}

	public static Page before() {
		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}
}
