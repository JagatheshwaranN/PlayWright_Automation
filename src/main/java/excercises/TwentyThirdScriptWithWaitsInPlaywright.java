package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class TwentyThirdScriptWithWaitsInPlaywright {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium()
				.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();

		page.navigate("https://opensource-demo.orangehrmlive.com/index.php/auth/login",
				new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
		page.fill("#txtUsername", "Admin");
		page.fill("#txtPassword", "admin123");
		page.click("#btnLogin");
		page.isVisible("#div_graph_display_emp_distribution");
		
		Page page1 = browserContext.newPage();
		page1.navigate("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		page1.click(".home");
		page1.locator("#editorial_image_legend").waitFor();
		page1.isVisible("#editorial_image_legend");
		
		browser.close();
		playwright.close();
		
	}

}
