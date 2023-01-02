package excercises;

import java.util.function.Consumer;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;


public class JSbasedPopups {

	
	public static Page before() {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;		
	}
	
	public static void simpleAlert() {
		Page page = before();
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept();
		});
		String result;
		page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		page.click("//button[text()='Click for JS Alert']");
		result = page.locator("#result").textContent();
		System.out.println(result);
		page.close();

	}
	
	public static void confirmAlert() {
		Page page = before();
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept();
		});
		String result;
		page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		page.click("//button[text()='Click for JS Confirm']");
		result = page.locator("#result").textContent();
		System.out.println(result);
		page.close();
	}
	
	public static void promptAlert() {
		Page page = before();
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			// To get the place holder value from Input
			String defaultValueFromInputPlaceHolder = dialog.defaultValue();
			System.out.println(defaultValueFromInputPlaceHolder);
			dialog.accept("Prompt Alert Test");
		});
		String result;
		page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		page.click("//button[text()='Click for JS Prompt']");
		result = page.locator("#result").textContent();
		System.out.println(result);
		page.close();
	}
	
	public static void waitAlert() {
		Page page = before();
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept();
		});
		page.navigate("https://letcode.in/waits");
		page.waitForPopup(() -> {
			page.locator("#accept").click();
		});
		page.close();
	}
	
	public static void main(String[] args) {
		
		// simpleAlert();
		// confirmAlert();
		// promptAlert();
		// waitAlert();

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		page.navigate("https://letcode.in/alert");
		
		Consumer<Dialog> alert = new Consumer<Dialog>(){
			public void accept(Dialog dialog) {
				String text = dialog.message();
				System.out.println(text);
				dialog.dismiss();
				page.offDialog(this);
			}
		};
		page.onDialog(alert);
		page.locator("#accept").click();
		
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept("Test");
		});
		page.locator("id=prompt").click();
		System.out.println(page.locator("#myName").textContent());

		browser.close();
		playwright.close();

	}

}
