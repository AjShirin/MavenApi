package mavApi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateApiTable {

	static void createTable() throws IOException, InterruptedException {
		// Creating the connection using Oracle DB
		// Note: url syntax is standard, so do grasp
		String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=MavenApi;encrypt=true;trustServerCertificate=true";

		// Username and password to access DB
		// Custom initialization
		String USER = "sa";
		String PASS = "root";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();) {
			// Create Table in SQL
			String createTabelSQL = "CREATE TABLE MavenApiT " + "(Id INTEGER PRIMARY KEY IDENTITY(1,1), "
					+ " web_pages VARCHAR(50), " + " state_province VARCHAR(50), " + " alpha_two_code VARCHAR(50), "
					+ " name VARCHAR(50), " + "country VARCHAR(50), " + " domains VARCHAR(50))";

			stmt.executeUpdate(createTabelSQL);
			System.out.println("API Table Created Successfully in Database ... :)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// close createTable Function
}// End of Class CreateApiTable