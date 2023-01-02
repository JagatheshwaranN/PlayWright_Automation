package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.HoverOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.MouseButton;

public class MouseOperations {

	static Playwright playwright;

	public static Page before() {

		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}

	public static void doubleClick() {
		Page page = before();
		page.navigate("https://demo.guru99.com/test/simple_context_menu.html");
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept();
		});
		page.locator("button[ondblclick='myFunction()']").dblclick();
		page.close();
		playwright.close();
	}

	public static void rightClick() {
		Page page = before();
		page.navigate("https://demo.guru99.com/test/simple_context_menu.html");
		page.locator(".context-menu-one.btn.btn-neutral")
				.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
		page.locator(".context-menu-item.context-menu-icon.context-menu-icon-copy").isVisible();
		page.close();
		playwright.close();
	}

	public static void mouseOver() {
		Page page = before();
		page.navigate("https://demo.opencart.com/");
		page.locator("(//li[@class='nav-item dropdown'])[1]").hover(new HoverOptions().setForce(false));
		page.locator("//ul[@class='list-unstyled']//li//a[text()='Mac (1)']").isVisible();
		page.close();
		playwright.close();

	}

	public static void main(String[] args) {

		doubleClick();
		rightClick();
		mouseOver();
	}
}
