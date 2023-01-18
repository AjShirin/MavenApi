
package mavApi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement; //TODO: Remove unused imports
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteAPIRecord {

	static void deleteRecordById() throws IOException, InterruptedException { //TODO: Your code can never throw these two exceptions
		// Creating the connection using Oracle DB
		// Note: url syntax is standard, so do grasp
		String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=MavenApi;encrypt=true;trustServerCertificate=true"; //TODO: Variable names

		// Username and password to access DB
		// Custom initialization
		String USER = "sa"; //TODO: Variable names
		String PASS = "root"; //TODO: Variable names
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement st = con.createStatement();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the ID Number you want to Delete :");
			int userInputID = sc.nextInt();
			String deleteRecordSQL = "DELETE FROM MavenApiT WHERE id = '" + userInputID + "'";
			System.out.println("Record :" + userInputID + " " + "deleted Successfully!! :)");
			ResultSet rs = st.executeQuery(deleteRecordSQL); //TODO: Is this optimal to use?

		} catch (Exception ex) {
			System.err.println(ex);
		}
	}// End of deleteRecordById Function

}// End of ClassDeleteAPIRecord