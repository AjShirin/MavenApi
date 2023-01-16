package mavApi;

import java.util.Scanner;

public class MainApi {

	static void menue() {
		System.out.println("_______________________________________________________________");
		System.out.println("Welcome!! Please Select one of the following options :) :      |");
		System.out.println("1. Create Table in database                                    |");
		System.out.println("2. Fetch API and Read                                          |");
		System.out.println("3. Insert API to database table                                |");
		System.out.println("4. Update Table Record                                         |");
		System.out.println("5. Delete Table Record                                         |");
		System.out.println("6. Print Record by user input                                  |");
		System.out.println("7. Exit System                                                 |");
		System.out.println("_______________________________________________________________|");
	}

	public static void main(String[] args) throws Throwable {

		Scanner sc = new Scanner(System.in);

		menue();
		System.out.println("Write the number of the option you want to choose:");
		do {

			int Menue = sc.nextInt();
			switch (Menue) {

			// Since The function is Static no need to create new object
			// Create Table in database
			case 1:
				CreateApiTable.createTable();

				menue();
				break;
				
			// Fetch API and Read 
			case 2:
				FetchAndRead.Fetch_ReadTable();

				menue();
				break;
				
			// Insert API to database table
			case 3:
				InsertApi.insertData();

				menue();
				break;

			case 4:
				UpdateAPIRecord.updateRecord();

				menue();
				break;

			case 5:

				menue();
				break;

			case 6:

				menue();
				break;

			// Exit the System
			case 7:
				System.out.println("Exiting The System Bye See you Again :)!...");
				System.exit(0);

			}

		} while (true);

	}
}