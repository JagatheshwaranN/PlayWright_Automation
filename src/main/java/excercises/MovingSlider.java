package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.BoundingBox;

public class MovingSlider {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://groww.in/calculators/sip-calculator");
		Locator slider = page.locator(".cis93SliderThumb.cis93SliderThumb-0 >> nth=0");
		String targetAmount = "50000";
		boolean isCompleted = false;
		if (slider != null) {
			while (!isCompleted) {
				BoundingBox sliderBox = slider.boundingBox();
				if (sliderBox != null) {
					page.mouse().move(sliderBox.x + sliderBox.width / 2, sliderBox.y + sliderBox.height / 2);
					page.mouse().down();
					page.mouse().move(sliderBox.x + 15, sliderBox.y + sliderBox.height / 2);
					page.mouse().up();
					String amount = page.locator("#MONTHLY_INVESTMENT").inputValue();
					if (amount.equals(targetAmount)) {
						isCompleted = true;
					}
				}
			}
		}
		page.waitForTimeout(2000);
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}

}
