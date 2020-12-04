package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class OrderedTrains
{
    public static int no = 21;
    ResultSet rs;
    public static Vector<String> train_id = new Vector<>();
    public static Vector<String>  origin = new Vector<>();
    public static Vector<String>  dest = new Vector<>();
    public static Vector<String>  arrival_day = new Vector<>();
    public static Vector<String>  departure_time = new Vector<>();
    public static Vector<String>  seat_no = new Vector<>();
    public static Vector<String>  final_price = new Vector<>();
    OrderedTrains() throws SQLException
    {
        train_id.clear();
        origin.clear();
        dest.clear();
        arrival_day.clear();
        departure_time.clear();
        seat_no.clear();
        final_price.clear();
        ConnectionClass dba = new ConnectionClass();
        try
        {
            rs = dba.getAvailTrain(LoginPage.customer_id);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        int i = 0;
        while (rs.next() && i != 21)
        {
            train_id.add(i, rs.getString("train_id"));
            origin.add(i, rs.getString("Origin"));
            dest.add(i, rs.getString("Destination"));
            arrival_day.add(rs.getString("Arrival_day"));
            departure_time.add(rs.getString("Departure_time"));
            seat_no.add(rs.getString("seat_no"));
            final_price.add(rs.getString("price"));
            i += 1;
        }
    }
}
