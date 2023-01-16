package mavApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class FetchAndRead {
	static void Fetch_ReadTable() throws IOException, InterruptedException {
		
		// Creating the connection using Oracle DB
		// Note: url syntax is standard, so do grasp
		String url = "jdbc:sqlserver://localhost:1433;databaseName=MavenApi;encrypt=true;trustServerCertificate=true";

		// Username and password to access DB
		// Custom initialization
		String user = "sa";
		String pass = "root";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://universities.hipolabs.com/search?country=United+States")).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String jsonString = response.body();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jasonParserNew = new JsonParser();
		JsonElement jasonElementNew = jasonParserNew.parse(jsonString);
		String jsonData = gson.toJson(jasonElementNew); // prettyJson
		System.out.println(jsonData);
		
	}// End of Fetch_ReadTable Function
}
