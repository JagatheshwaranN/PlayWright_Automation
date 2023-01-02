package excercises;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class MaximizeWindow {
	
	public static void maximizeBrowserUsingArgs() {
		
		Playwright playwright = Playwright.create();
		List<String> option = new ArrayList<String>();
		option.add("--start-maximized");
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(option));		
		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
		Page page = browserContext.newPage();
		page.navigate("https://playwright.dev/java/");
		page.waitForTimeout(5000);
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}
	
	public static void maximizeBrowserUsingViewPort() {
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) dimension.getWidth();
		int height = (int) dimension.getHeight();
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
		Page page = browserContext.newPage();
		page.navigate("https://playwright.dev/java/");
		page.waitForTimeout(5000);
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();		
	}
	
	public static void main (String[] args) {
		
		maximizeBrowserUsingArgs();
		maximizeBrowserUsingViewPort();
	}

}
