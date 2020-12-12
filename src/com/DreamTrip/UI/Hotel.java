package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Hotel
{
	public static int no = 21;
	public static String[] hotel_rooms;
	ResultSet rs;
	public static String[] hotel_name = new String[no];
	public static String[] hotel_image = new String[no];
	public static String[] hotel_rating = new String[no];
	public static String[] cost_per_night = new String[no];
	public static String[] no_of_purchases = new String[no];
	public static String[] hotel_location = new  String[no];
	public static String[] area = new  String[no];
	public static String[] hotel_id = new String[no];


	Hotel () throws SQLException
	{
		ConnectionClass dba = new ConnectionClass();
		try
		{
			rs = dba.getHotel();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		int i = 0;
		while(rs.next() && i != 21)
		{
			hotel_id[i] = rs.getString("Hotel_id");
			hotel_name[i] = rs.getString("Hotel_name");
			hotel_image[i] = rs.getString("Hotel_img");
			hotel_rating[i] = rs.getString("Rating");
			cost_per_night[i] = rs.getString("Fare_per_night");
			no_of_purchases[i] = rs.getString("No_of_purchase");
			hotel_location[i] = rs.getString("Location");
			area[i] = rs.getString("Hotel_desc");
			i += 1;
		}
	}
}
