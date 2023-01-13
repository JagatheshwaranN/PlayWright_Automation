package excercises;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Route;

public class MockAPI {

	static Playwright playwright;

	public static Page before() {

		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}

	public static void mockAPIResponse() {
		Page page = before();
		page.route("https://reqres.in/api/users", route -> {

			route.fulfill(new Route.FulfillOptions().setBody(
					"{\"data\":{\"id\":1001,\"email\":\"playwright@test.com\",\"first_name\":\"Play\",\"last_name\":\"Wright\",\"avatar\":\"https://reqres.in/img/faces/1-image.jpg\"}}"));
		});
		page.navigate("https://reqres.in/api/users");
	}

	public static void modifyAPIResponse() {
		Page page = before();
		page.route("https://dog.ceo/api/breeds/list/all", route -> {

			APIResponse repsonse = page.request().fetch(route.request());
			JsonObject json = new Gson().fromJson(repsonse.text(), JsonObject.class);
			JsonObject message = json.getAsJsonObject("message");
			message.add("playwight", new JsonArray());
			route.fulfill(new Route.FulfillOptions().setResponse(repsonse).setBody(json.toString()));
		});
		page.navigate("https://dog.ceo/api/breeds/list/all");
	}

	public static void main(String[] args) {
		mockAPIResponse();
		modifyAPIResponse();
	}
}
