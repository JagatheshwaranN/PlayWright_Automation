package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.SelectOptionOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

public class DropDownSelection {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://letcode.in/dropdowns");
		
		//Single dropdown selection
		Locator fruits = page.locator("#fruits");
		fruits.selectOption("2");
		fruits.selectOption(new SelectOption().setValue("1"));
		fruits.selectOption(new SelectOption().setIndex(2));
		fruits.selectOption(new SelectOption().setLabel("Banana"));
		
		// Multiple dropdown selection
		Locator movies = page.locator("#superheros");
		movies.selectOption(new String[] {"aq", "bt"});
		
		// Single dropdown selection with select option condition
		Locator languages = page.locator("#lang");
		languages.selectOption(new SelectOption().setValue("java"), new SelectOptionOptions().setTimeout(2000));
		
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		
	}

}
