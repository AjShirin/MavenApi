package mavApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class FetchAndRead {
	static void Fetch_ReadTable() throws IOException, InterruptedException {

		// Creating the connection using Oracle DB
		// Note: url syntax is standard, so do grasp
		String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=MavenApi;encrypt=true;trustServerCertificate=true";

		// Username and password to access DB
		// Custom initialization
		String USER = "sa";
		String PASS = "root";

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();) {
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// End of Fetch_ReadTable Function
} // End of Class FetchAndRead
