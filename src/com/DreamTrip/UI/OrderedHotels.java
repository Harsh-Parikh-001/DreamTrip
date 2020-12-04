package com.DreamTrip.UI;

import com.DreamTrip.Connectivity.ConnectionClass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderedHotels
{
    public static int no = 21;
    ResultSet rs;
    ResultSet rs2;
    public static String[] hotel_name = new String[no];
    public static String[] total_payment = new String[no];
    public static String[] no_of_days = new String[no];
    public static String[] hotel_id = new String[no];
    public static String[] room_no = new String[no];
//    public static String[] Hotel_Curr_Names = new String[no];
//    public static String[] Hotel_Curr_Id = new String[no];

    OrderedHotels () throws SQLException
    {
        ConnectionClass dba = new ConnectionClass();
        try
        {
            rs = dba.getAvailHotels(LoginPage.customer_id);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        int i = 0;
        while(rs.next() && i != 21)
        {
            hotel_id[i] = rs.getString("hotel_id");
            total_payment[i] = rs.getString("price");
            no_of_days[i] = rs.getString("no_of_days");
            room_no[i] = rs.getString("room_no");
            i += 1;
        }
        no = i;
//        try
//        {
//            rs2 = dba.getHotelNameAndId();
//        }
//        catch (ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        while(rs2.next() && i != 21)
//        {
//            Hotel_Curr_Id[i] = rs2.getString("Hotel_id");
//            Hotel_Curr_Names[i] = rs2.getString("Hotel_name");
//        }
//        for (int j = 0; j < i; j++)
//        {
//            String h = hotel_id[j];
//            int n = 0;
//            while (!h.equals(Hotel_Curr_Id[n]) && n != 21 && rs2.next())
//            {
//                n++;
//            }
//            hotel_name[j] = Hotel_Curr_Names[n];
//            System.out.println(hotel_name[j] + hotel_id[j]);
//        }
    }
}
