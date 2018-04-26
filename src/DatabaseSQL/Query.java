package DatabaseSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Query {

	
	public static   Connection getConnection() throws Exception {
		try {
			String Driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/flight_application";
																	
			String username = "root";
			String password = "tiger";

			Class.forName(Driver);

			Connection conn = DriverManager.getConnection(url, username,
					password);
			System.out.println("connected");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
	
	public static void getFlights() throws Exception {

		Scanner input = new Scanner(System.in);

		Connection con = getConnection();
		/*System.out.println("Insert Select Statement: ");
		String a = input.nextLine(); */
		
		System.out.println("What city do you want to depart from: ");
		String city = input.next();

		PreparedStatement statement = con.prepareStatement("select * from flight where DepartureCity = '" + city + " ' ");

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			System.out.println("Flight #: " + result.getString(1) + " Departure City " + result.getString(2)
					+ " Arrival City " + result.getString(3) + " Departure Day: " 
					+ result.getString(4) + " Arrival Day: " + result.getString(5) + " Departure Time: " 
					+ result.getString(6) + " Arrival Time: " + result.getString(7));

		}
		con.close();
	}
	
	public static void main (String [] args) throws Exception {
		
		getFlights();
	}
	
	
}
