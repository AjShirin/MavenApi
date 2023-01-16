package mavApi;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class InsertApi {
	static void insertData() throws IOException, InterruptedException {
		// Creating the connection using Oracle DB
		// Note: url syntax is standard, so do grasp
		String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=MavenApi;encrypt=true;trustServerCertificate=true";

		// Username and password to access DB
		// Custom initialization
		String USER = "sa";
		String PASS = "root";

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

		Attributes[] variable = gson.fromJson(jsonData, Attributes[].class);

		for (Attributes api : variable) {

			String webPages = api.getWeb_pages()[0];
			String stateProvince = api.getState_province();
			String alphaTwoCode = api.getAlpha_two_code();
			String name = api.getName();
			String country = api.getCountry();
			String domains = api.getDomains()[0];

			// Inserting data using SQL query
			String sqlInsert = "insert into MavenApiT(web_pages,state_province, alpha_two_code,name, country,domains)"
					+ " values('" + webPages + "' ,'" + stateProvince + "', '" + alphaTwoCode + "','" + name + "' ,' "
					+ country + "','" + domains + "')";

			// Connection class object
			Connection con = null;

			// Try block to check for exceptions
			try {

				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				DriverManager.registerDriver(driver);
				con = DriverManager.getConnection(DB_URL, USER, PASS);

				// Creating a statement
				Statement st = con.createStatement();

				// Executing query
				int s = st.executeUpdate(sqlInsert);
				if (s >= 1)
					System.out.println("Inserted successfully : " + sqlInsert);
				else
					System.out.println("Insertion failed");

				// Closing the connections
				con.close();
			}

			// Catch block to handle exceptions
			catch (Exception ex) {
				// Display message when exceptions occurs
				System.err.println(ex);
			}

		}
	}// End of Function insertData
} // End of Class InsertApi