package excercises;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TwentiethScriptWithDownloadFile {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();

		page.navigate("https://chromedriver.storage.googleapis.com/index.html?path=102.0.5005.61/");
		Download downloadFile = page.waitForDownload(() -> {
			page.click("a:text('chromedriver_win32.zip')");
		});

		boolean flag = downloadFile.failure() == null ? false : true;
		if (flag == false) {
			System.out.println("No interruption in download");
		} else {
			System.out.println("Download has interruption");
		}

		System.out.println(downloadFile.url());

		System.out.println(downloadFile.page().title());

		String path = downloadFile.path().toString();
		System.out.println("Download File Path ==> " + path);

		downloadFile.saveAs(Paths.get("ChromeDriver.Zip"));
		System.out.println(downloadFile.suggestedFilename());

		browser.close();
		playwright.close();
	}

}
