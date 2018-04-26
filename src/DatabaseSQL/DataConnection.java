package DatabaseSQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnection {
	
	private static Connection con; 
	private static String Driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/flight_application";
	
	private static String username = "root";
	private static String password = "tiger";
	
	
	public static void  connect() {
		try {
			

			Class.forName(Driver);

			Connection con = DriverManager.getConnection(url, username,
					password);
			System.out.println("connection Successful");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: No connection");
		}
		

	}
	
	public static Connection getConnection() {
		return con; 
	}
}
