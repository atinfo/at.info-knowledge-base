package core.tests.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.openqa.selenium.remote.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Getrequest {
	@Test
	@Parameters({ "url" })
	public void simpleTestLogInToGmail(String url) throws InterruptedException,
			ClientProtocolException, IOException, JSONException {

		// Create json
		String json = "{\"user1\": {\"name\": \"Jamse\",\"email\": \"main_window@mail.com\",\"age\": 50,}"
				+ ",\"user2\": {\"name\": \"Bond\",\"email\":\"aston@mail.com\",\"age\": 49,},"
				+ "      \"user3\": {\"name\": \"Weider\"\"email\": \"starwars@mail.com\",\"age\": 500,}}";

		String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\"> <soapenv:Header/>"
				+ "<soapenv:Body>"
				+ "<gs:getCountryRequest>"
				+ "<gs:name>Spain</gs:name>"
				+ "</gs:getCountryRequest>"
				+ "</soapenv:Body>" + "</soapenv:Envelope>";

		// Get json to api
		String response = getListUsers();

		// Compare result
//		Assert.assertTrue(response.replace(" ", "").equals(
//				json.replace(" ", "")));
//		System.out.println("DEBUG1:" + response);

	}

	public static String getListUsers() throws IOException {
		String url = "https://api.rest-test-app.com/users/list-users";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.addRequestProperty("Authorization", "Basic key");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String list = response.toString();
		System.out.println(list);
		return list;
	}

	

}
