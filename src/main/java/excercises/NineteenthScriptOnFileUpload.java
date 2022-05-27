package excercises;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FilePayload;

public class NineteenthScriptOnFileUpload {

	public static void main(String[] args) throws InterruptedException {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();

		page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");
		page.setInputFiles("input#filesToUpload", Paths.get("orangeHRMlogin.json"));
		Thread.sleep(2000);
		page.setInputFiles("input#filesToUpload", new Path[0]);
		Thread.sleep(3000);
		page.setInputFiles("input#filesToUpload",
				new Path[] { Paths.get("orangeHRMlogin.json"), Paths.get("trace-view.zip") });
		Thread.sleep(2000);
		page.setInputFiles("input#filesToUpload", new Path[0]);
		Thread.sleep(3000);
		page.setInputFiles("input#filesToUpload",
				new FilePayload("demo.txt", "text/plain", "This is playwright demo".getBytes(StandardCharsets.UTF_8)));
		Thread.sleep(2000);
		page.setInputFiles("input#filesToUpload", new Path[0]);

		// page.navigate("https://cgi-lib.berkeley.edu/ex/fup.html");

		browser.close();
		playwright.close();

	}

}
