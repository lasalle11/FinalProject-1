package DatabaseSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class FlightDB {
	

		
		public void showAllFlights() {
			
			try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement("Select * from flight");
			
			ResultSet result = ps.executeQuery(); 
			
			while (result.next()) {
				System.out.println("Flight #: " + result.getString(1) + " Departure City " + result.getString(2)
						+ " Arrival City " + result.getString(3) + " Departure Day: " 
						+ result.getString(4) + " Arrival Day: " + result.getString(5) + " Departure Time: " 
						+ result.getString(6) + " Arrival Time: " + result.getString(7)); 
		}
			DataConnection.getConnection().close();
		} catch (Exception e){
			System.out.println(e);
		}
		}
			
		
		public static void SearchFlightByCity() throws Exception {

			Scanner input = new Scanner(System.in);

			Connection con = DataConnection.getConnection();
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
		
		
		
		public void addFlight(int flightNum, String DepartureCity, String ArrivalCity, String DepartureDay, String ArrivalDay, String DepartureTime, String ArrivalTime) {
			try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement("insert into flight(FligthNum, DepartureCity, ArrivalDay, DepartureDay, ArrivalDay, DepartureTime, ArrivalTime)" +
			 "values (?,?,?,?,?,?,?)" );
			
			ps.setInt(1, flightNum);
			ps.setString(2, DepartureCity);
			ps.setString(3, ArrivalCity);
			ps.setString(4, DepartureDay);
			ps.setString(5, ArrivalDay);
			ps.setString(6, DepartureTime);
			ps.setString(7, ArrivalTime);
			
			ps.executeUpdate();
			
			DataConnection.getConnection().close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		}
		
		public void deleteFlight(int flightNum) {
			
			try {
				PreparedStatement ps = DataConnection.getConnection().prepareStatement("delete from flight where flightNum = ? ");
				
				ps.setInt(1, flightNum);
				ps.executeUpdate();
				
				DataConnection.getConnection().close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		
		
		
		
	}

