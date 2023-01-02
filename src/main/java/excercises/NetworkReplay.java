package excercises;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.RouteFromHAROptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class NetworkReplay {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.routeFromHAR(Paths.get("har/bookcart.har"), new RouteFromHAROptions().setUpdate(false));
		page.navigate("https://bookcart.azurewebsites.net/", new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));		
		page.locator("input[type='search']").type("HP7");
		page.locator("span.mat-option-text").click();
		page.waitForTimeout(5000);
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}

}
