package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.*;

public class Assertions {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();

		page.navigate("https://letcode.in/dropdowns");

		page.locator("#superheros").selectOption(new String[] { "aq", "bt" });

		PlaywrightAssertions.assertThat(page.locator("#superheros")).hasValues(new String[] { "aq", "bt" });

		page.navigate("https://letcode.in/edit");

		PlaywrightAssertions.assertThat(page).hasURL("https://letcode.in/edit");

		PlaywrightAssertions.assertThat(page).hasTitle("Interact with Input fields");

		page.locator("#fullName").click();

		PlaywrightAssertions.assertThat(page.locator("#fullName")).isFocused();

		PlaywrightAssertions.assertThat(page.locator(".button.is-primary.is-rounded.is-small").first())
				.containsText("Sign up");

		PlaywrightAssertions.assertThat(page.locator("#fullName")).hasAttribute("placeholder",
				"Enter first & last name");

		PlaywrightAssertions.assertThat(page.locator("#fullName")).hasClass("input is-focused");

		PlaywrightAssertions.assertThat(page.locator("#fullName")).hasId("fullName");

		PlaywrightAssertions.assertThat(page.locator("#fullName")).hasCSS("border-color", "rgb(250, 124, 145)");

		PlaywrightAssertions.assertThat(page.locator(".button.is-primary.is-rounded.is-small").last())
				.hasText("Log in");

		PlaywrightAssertions.assertThat(page.locator("#join")).hasValue("I am good");

		PlaywrightAssertions.assertThat(page.locator("#join")).isEditable();

		PlaywrightAssertions.assertThat(page.locator("#fullName")).isEnabled();

		PlaywrightAssertions.assertThat(page.locator("#noEdit")).isDisabled();

		PlaywrightAssertions.assertThat(page.locator("#getMe")).isVisible();

		page.navigate("https://letcode.in/radio");

		PlaywrightAssertions.assertThat(page.locator("#notfoo")).isChecked();

		page.navigate("https://www.w3schools.com/html/html_lists.asp");

		PlaywrightAssertions.assertThat(page.locator("ol>li")).hasCount(4);

		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}

}
