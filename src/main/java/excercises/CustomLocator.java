package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CustomLocator {
	
	static Playwright playwright;
		
	public static void main(String[] args) {
		
		String createTagNameEngine = "{\n" +
				  "  // Returns the first element matching given selector in the root's subtree.\n" +
				  "  query(root, selector) {\n" +
				  "    return root.querySelector(selector);\n" +
				  "  },\n" +
				  "\n" +
				  "  // Returns all elements matching given selector in the root's subtree.\n" +
				  "  queryAll(root, selector) {\n" +
				  "    return Array.from(root.querySelectorAll(selector));\n" +
				  "  }\n" +
				  "}";
		
		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		playwright.selectors().register("tag", createTagNameEngine);
		Page page = browserContext.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.locator("input[name='username']").fill("Admin");
		page.locator("input[name='password']").fill("admin123");
		page.locator("tag=button").click();
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		
	}

}
