package excercises;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Inspector {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();

			// Open new page
			Page page = context.newPage();

			// Go to https://www.google.com/
			page.navigate("https://www.google.com/");

			// On fly debug
			// page.pause();

			// Click [aria-label="Search"]
			page.locator("[aria-label=\"Search\"]").click();

			// Fill [aria-label="Search"]
			page.locator("[aria-label=\"Search\"]").fill("playwright");

			// Press Enter
			// page.waitForNavigation(new
			// Page.WaitForNavigationOptions().setUrl("https://www.google.com/search?q=playwright&source=hp&ei=yDl7YrfJCYqgmAWwjqWAAQ&iflsig=AJiK0e8AAAAAYntH2P5_jGhLGe2UDwtBB--dmgY_IZsl&ved=0ahUKEwi3zbmzzNb3AhUKEKYKHTBHCRAQ4dUDCAc&uact=5&oq=playwright&gs_lcp=Cgdnd3Mtd2l6EAMyCAgAEIAEELEDMggIABCABBCxAzIFCAAQgAQyCAgAEIAEELEDMgsIABCABBCxAxCDATIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOg4ILhCPARDqAhCMAxDlAjoOCAAQjwEQ6gIQjAMQ5QI6CwguEIAEELEDEIMBOgQIABADOgUILhCABDoICAAQgAQQyQM6BQgAEJIDOgoIABCxAxCDARAKOgsILhCABBDHARCvAToHCAAQgAQQClCyEFjmO2D4R2gCcAB4AIABaYgBtweSAQQxMC4xmAEAoAEBsAEK&sclient=gws-wiz"),
			// () ->
			page.waitForNavigation(() -> {
				page.locator("[aria-label=\"Search\"]").press("Enter");
			});
			// assertThat(page).hasURL("https://www.google.com/search?q=playwright&source=hp&ei=yDl7YrfJCYqgmAWwjqWAAQ&iflsig=AJiK0e8AAAAAYntH2P5_jGhLGe2UDwtBB--dmgY_IZsl&ved=0ahUKEwi3zbmzzNb3AhUKEKYKHTBHCRAQ4dUDCAc&uact=5&oq=playwright&gs_lcp=Cgdnd3Mtd2l6EAMyCAgAEIAEELEDMggIABCABBCxAzIFCAAQgAQyCAgAEIAEELEDMgsIABCABBCxAxCDATIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOg4ILhCPARDqAhCMAxDlAjoOCAAQjwEQ6gIQjAMQ5QI6CwguEIAEELEDEIMBOgQIABADOgUILhCABDoICAAQgAQQyQM6BQgAEJIDOgoIABCxAxCDARAKOgsILhCABBDHARCvAToHCAAQgAQQClCyEFjmO2D4R2gCcAB4AIABaYgBtweSAQQxMC4xmAEAoAEBsAEK&sclient=gws-wiz");

			// Click text=Playwright: Fast and reliable end-to-end testing for modern ...
			page.locator("text=Playwright: Fast and reliable end-to-end testing for modern ...").click();
			assertThat(page).hasURL("https://playwright.dev/");
		}
	}
}
