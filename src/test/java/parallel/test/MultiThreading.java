package parallel.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class MultiThreading extends Thread {

	private final String browserName;

	private MultiThreading(String browserName) {
		this.browserName = browserName;
	}

	public static void main(String[] args) throws InterruptedException {

//		for(String browserName : Arrays.asList("chromium","webkit", "firefox")) {
//			Thread t1 = new MultiThreading(browserName);
//			t1.start();	
//		}
		Thread t1 = new MultiThreading("chromium");
		t1.start();
		Thread t2 = new MultiThreading("webkit");
		t2.start();
		Thread t3 = new MultiThreading("firefox");
		t3.start();
	}

	@Override
	public void run() {

		try (Playwright playwright = Playwright.create()) {
			BrowserType browserType = getBrowserType(playwright, browserName);
			Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();
			page.navigate("https://playwright.dev/");
			page.waitForTimeout(5000);

		}
	}

	private static BrowserType getBrowserType(Playwright playwright, String browserName) {

		switch (browserName) {
		case "chromium":
			return playwright.chromium();
		case "webkit":
			return playwright.webkit();
		case "firefox":
			return playwright.firefox();
		default:
			throw new IllegalArgumentException();
		}
	}

}
