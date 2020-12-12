package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Trains
{
	public static int no = 21;
	ResultSet rs;
	public static String[] train_number = new String[no];
	public static String[] origin = new String[no];
	public static String[] destination = new String[no];
	public static String[] arrival_time = new String[no];
	public static String[] departure_time = new  String[no];
	public static String[] seats_remaining = new String[no];
	public static String[] train_image = new String[no];
	public static String[] cost_to_travel = new String[no];

	Trains () throws SQLException
	{
		ConnectionClass dba = new ConnectionClass();
		try
		{
			rs = dba.getTrains();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		int i = 0;
		while(rs.next() && i != 21)
		{
			train_number[i] = rs.getString("Train_id");
			origin[i] = rs.getString("Origin");
			destination[i] = rs.getString("Destination");
			arrival_time[i] = rs.getString("Arrival_day");
			departure_time[i] = rs.getString("Arrival_time");
			seats_remaining[i] = rs.getString("Seats_remaining");
			train_image[i] = rs.getString("Train_img");
			cost_to_travel[i] = rs.getString("Fare");
			i += 1;
		}
	}
}
