package mavApi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class PrintAPIRecord {
	public static void PrintRecordFromTable() throws IOException, InterruptedException { //TODO: Your function will never throw these errors.

		// Creating the connection using Oracle DB
		// Note: url syntax is standard, so do grasp
		String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=MavenApi;encrypt=true;trustServerCertificate=true";//TODO: Variable names

		// Username and password to access DB
		// Custom initialization
		String USER = "sa"; //TODO: Variable names
		String PASS = "root";//TODO: Variable names

		int count = 0;
		String sql = "SELECT * FROM MavenApiT";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter how many records you want to view?");
			int useInputNumber = sc.nextInt();

			while (rs.next() && count < useInputNumber) {
				Integer ID = rs.getInt(1);
				String webPages = rs.getString(2);
				String stateProvince = rs.getString(3);
				String alphaTwoCode = rs.getString(4);
				String name = rs.getString(5);
				String country = rs.getString(6);
				String domains = rs.getString(7);
				System.out.println("Id :" + ID + "||" + " " + " Web Page :" + webPages + "||" + " "
						+ " State Province :" + stateProvince + "||" + " " + " Alpha Two Code :" + alphaTwoCode + "||"
						+ " " + "Name :" + name + "||" + "\n " + " Country: " + " Domains :" + domains + "||" + " ");
				count++;
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	} // End of PrintRecordFromTable Function

}// End of PrintAPIRecord Class
