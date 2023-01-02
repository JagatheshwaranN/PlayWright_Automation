package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class GetByLocators {
	
	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://bookcart.azurewebsites.net/");
		page.getByText("Login").click();
		page.getByLabel("Username").type("AutoTest");
		page.getByLabel("Password").type("Test@123");
		page.locator("//button[@color='primary']").click();
		page.getByPlaceholder("Search books or authors").type("The Hate U Give");
		Locator suggestion = page.getByRole(AriaRole.OPTION);
		suggestion.getByText("The Hate U Give").click();
		page.getByAltText("Book cover image").click();
		//page.getByTestId(null);
		//page.getByTitle(null);
		page.waitForTimeout(3000);
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		
	}

}
