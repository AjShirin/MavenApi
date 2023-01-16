package mavApi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UpdateAPIRecord {
	static void updateRecord() throws IOException, InterruptedException {
		// Creating the connection using Oracle DB
		// Note: url syntax is standard, so do grasp
		String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=MavenApi;encrypt=true;trustServerCertificate=true";

		// Username and password to access DB
		// Custom initialization
		String USER = "sa";
		String PASS = "root";

		String updateSQL = "UPDATE MavenApiT SET web_pages = ?,state_province = ?, alpha_two_code = ?, name = ?, country = ?, domains = ? WHERE ID = ?";

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the ID Number you want to Update :");
		int userInputID = sc.nextInt();
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(updateSQL);

			pstmt.setString(1, "JackMars.com");
			pstmt.setString(2, "True");
			pstmt.setString(3, "OM");
			pstmt.setString(4, "William Henry Bill Gates");
			pstmt.setString(5, "Oman");
			pstmt.setString(6, "ZulikaHenry.edu");
			pstmt.setInt(7, userInputID);
			pstmt.executeUpdate();

			String byID = "SELECT * FROM MavenApiT WHERE id = ?";

			PreparedStatement pstmt2 = con.prepareStatement(byID);
			pstmt2.setInt(1, userInputID);
			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				//Integer ID = rs.getInt(1);
				String webPages = rs.getString(2);
				String stateProvince = rs.getString(3);
				String alphaTwoCode = rs.getString(4);
				String name = rs.getString(5);
				String country = rs.getString(6);
				String domains = rs.getString(7);

				System.out.println("Record Updated Successfully :) !!");

				System.out.println("Id :" + userInputID + "||" + " " + " Web Page :" + webPages + "||" + " "
						+ " State Province :" + stateProvince + "||" + " " + " Alpha Two Code :" + alphaTwoCode + "||"
						+ " " + "Name :" + name + "||" + "\n " + " Country: " + " Domains :" + domains + "||" + " ");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	} // End of updateHotelById Function
}// End of UpdateRecord Class
