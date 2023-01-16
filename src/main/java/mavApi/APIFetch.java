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

public class APIFetch {

	public static void main(String[] args) throws IOException, InterruptedException {
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

//		String webPages = api.getWeb_pages()[0];
//			Attributes[] variable = gson.fromJson(jsonData, Attributes[].class);
//
//		for (Attributes api : variable) {
//
//			String stateProvince = api.getState_province();
//			String alphaTwoCode = api.getAlpha_two_code();
//			String name = api.getName();
//			String country = api.getCountry();
//			String domains = api.getDomains()[0];
//
//			// Inserting data using SQL query
//			String sqlInsert = "insert into MavenApi(web_pages,state_province, alpha_two_code,name, country,domains)"
//					+ " values('" + webPages + "' ,'" + stateProvince + "', '" + alphaTwoCode + "','" + name + "' ,' "
//					+ country + "','" + domains + "')";
//
//			// Connection class object
//			Connection con = null;
//
//			// Try block to check for exceptions
//			try {
//
//				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//				DriverManager.registerDriver(driver);
//				con = DriverManager.getConnection(url, user, pass);
//				
//				// Creating a statement
//				Statement st = con.createStatement();
//
//				// Executing query
//				int s = st.executeUpdate(sqlInsert);
//				if (s >= 1)
//					System.out.println("inserted successfully : " + sqlInsert);
//				else
//					System.out.println("insertion failed");
//
//				// Closing the connections
//				con.close();
//			}
//
//			// Catch block to handle exceptions
//			catch (Exception ex) {
//				// Display message when exceptions occurs
//				System.err.println(ex);
//			}
//
//		}
	}
}