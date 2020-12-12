package com.DreamTrip.UI;
import com.DreamTrip.Connectivity.ConnectionClass;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Flights
{
	ResultSet rs;
	public static int no = 18;
	public static String[] airlines_name = new String[no];
	public static String[] flight_number = new String[no];
	public static String[] flight_image = new String[no];
	public static String[] flight_origin = new String[no];
	public static String[] flight_destination = new String[no];
	public static String[] flight_fare = new String[no];
	public static String[] no_of_seats = new String[no];
	public static String[] arrival_time = new String[no];
	public static String[] departure_time = new String[no];

	Flights() throws SQLException
	{
		try
		{
			rs = ConnectionClass.getFlights();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		int i = 0;
		while (rs.next() && i <= 18)
		{
			flight_number[i] = rs.getString("Flight_number");
			airlines_name[i] = rs.getString("Airlines");
			flight_image[i] = rs.getString("Flight_img");
			flight_origin[i] = rs.getString("Origin");
			flight_destination[i] = rs.getString("Destination");
			flight_fare[i] = rs.getString("Fare");
			no_of_seats[i] = rs.getString("No_of_available_seats");
			arrival_time[i] = rs.getString("Arrival_time");
			departure_time[i] = rs.getString("Departure_time");
			i += 1;
		}

	}
}
