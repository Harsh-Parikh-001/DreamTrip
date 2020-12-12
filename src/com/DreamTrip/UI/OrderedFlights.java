package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class OrderedFlights
{
    public static int no = 21;
    ResultSet rs;
    public static Vector<String>  flight_id = new Vector<>();
    public static Vector<String>  airline_name = new Vector<>();
    public static Vector<String>  origin = new Vector<>();
    public static Vector<String>  dest = new Vector<>();
    public static Vector<String>  arrival_time = new Vector<>();
    public static Vector<String>  departure_time = new Vector<>();
    public static Vector<String>  seat_no = new Vector<>();
    public static Vector<String>  final_price = new Vector<>();
    OrderedFlights() throws SQLException
    {
        flight_id.clear();
        airline_name.clear();
        origin.clear();
        dest.clear();
        arrival_time.clear();
        departure_time.clear();
        seat_no.clear();
        final_price.clear();
        ConnectionClass dba = new ConnectionClass();
        try
        {
            rs = dba.getAvailFlights(LoginPage.customer_id);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        int i = 0;
        while (rs.next() && i != 21)
        {
            flight_id.add(i, rs.getString("flight_id"));
            airline_name.add(i, rs.getString("Airlines"));
            origin.add(i, rs.getString("Origin"));
            dest.add(i, rs.getString("Destination"));
            arrival_time.add(rs.getString("Arrival_time"));
            departure_time.add(rs.getString("Departure_time"));
            seat_no.add(rs.getString("seat_no"));
            final_price.add(rs.getString("price"));
            i += 1;
        }
    }
}
