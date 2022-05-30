package excercises;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TwentyFirstScriptOnBrowserMaximize {

	public static void main(String[] args) {

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) dimension.getWidth();
		int height = (int) dimension.getHeight();

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser
				.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
		Page page = browserContext.newPage();
		page.navigate("https://whatismyviewport.com/");

		browser.close();
		playwright.close();

	}

}
