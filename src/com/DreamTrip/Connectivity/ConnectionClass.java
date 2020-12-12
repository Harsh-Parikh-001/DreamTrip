package com.DreamTrip.Connectivity;
import java.sql.*;


public class ConnectionClass
{
    public static String dbusername = "root";
    public static String dbpassword ="harshmody" ;
    public static String dburl = "jdbc:mysql://localhost:3306/dreamtrip";
    public static String dbdriver = "com.mysql.cj.jdbc.Driver";
    public static Connection dbcon;

    public static void dbConnect() throws ClassNotFoundException, SQLException
    {
        Class.forName(dbdriver);
        dbcon = DriverManager.getConnection(dburl, dbusername, dbpassword);
    }
    public static void dbclose() throws SQLException
    {
        dbcon.close();
    }
    public static int insertCustomer(String name, String phone, String email, String address, String psswd, String key) throws SQLException
    {
        try
        {
            dbConnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        String sql = "INSERT INTO dreamtrip.customer(Cust_name, Contact, Email,Address,pass_word,encrip_key) VALUES  (?,?,?,?,?,?)";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        pstmt.setString(1, name);
        pstmt.setString(2, phone);
        pstmt.setString(3, email);
        pstmt.setString(4, address);
        pstmt.setString(5, psswd);
        pstmt.setString(6, key);
        return(pstmt.executeUpdate());
    }
    public static void insertHotelOrder(String hotel_id, String customer_id, int no_days, String room_no, float price) throws SQLException {
        try
        {
            dbConnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        String sql = "INSERT INTO dreamtrip.hotel_order VALUES  (?,?,?,?,?)";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        pstmt.setString(2, hotel_id);
        pstmt.setString(1, customer_id);
        pstmt.setString(3, String.valueOf(no_days));
        pstmt.setString(4, room_no);
        pstmt.setString(5, String.valueOf(price));
        pstmt.executeUpdate();
    }

    public static void deleteHotelOrder (String hotel_id, String room_no) throws SQLException {
        try
        {
            dbConnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        String sql = "Delete from dreamtrip.avail_hotel where hotel_id = '" + hotel_id+"' AND room_no = '" + room_no + "'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        pstmt.executeUpdate(sql);
    }
    public static void insertFlightOrder(String customer_id, String flight_id, String airline_name, String class_travel, String origin, String dest, String arrival_time, String departure_time, String seat_no, float finalPayment) throws SQLException {
        try
        {
            dbConnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        String sql = "INSERT INTO dreamtrip.flight_order VALUES  (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        pstmt.setString(1, customer_id);
        pstmt.setString(2, flight_id);
        pstmt.setString(3, airline_name);
        pstmt.setString(4, class_travel);
        pstmt.setString(5, origin);
        pstmt.setString(6, dest);
        pstmt.setString(7, arrival_time);
        pstmt.setString(8, departure_time);
        pstmt.setString(9, seat_no);
        pstmt.setString(10, String.valueOf(finalPayment));
        pstmt.executeUpdate();
    }
    public static void deleteFlightOrder (String flight_id, String seat_no) throws SQLException
    {
        try
        {
            dbConnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        String sql = "Delete from dreamtrip.avail_flight where flight_id = '" + flight_id+"' AND no_of_seats = '" + seat_no + "'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        pstmt.executeUpdate(sql);
    }
    public static void insertTrainOrder(String customer_id, String train_id, String origin, String dest, String departure_time, String arrival_day, String seat_no, float finalPayment) throws SQLException {
        try
        {
            dbConnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        String sql = "INSERT INTO dreamtrip.train_order VALUES  (?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        pstmt.setString(1, customer_id);
        pstmt.setString(2, train_id);
        pstmt.setString(3, origin);
        pstmt.setString(4, dest);
        pstmt.setString(5, departure_time);
        pstmt.setString(6, arrival_day);
        pstmt.setString(7, seat_no);
        pstmt.setString(8, String.valueOf(finalPayment));
        pstmt.executeUpdate();
    }
    public static void deleteTrainOrder (String train_id, String seat_no) throws SQLException
    {
        try
        {
            dbConnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        String sql = "Delete from dreamtrip.avail_train where train_id = '" + train_id+"' AND no_of_seats = '" + seat_no + "'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        pstmt.executeUpdate(sql);
    }
    public static ResultSet getCustomer() throws ClassNotFoundException, SQLException {
        dbConnect();
        String sql = "SELECT * FROM customer;";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }

    public static ResultSet getFlights() throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM flight;";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public static ResultSet getTrain() throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM trains;";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public static ResultSet getHotelRoomsWhere(String hotel) throws SQLException, ClassNotFoundException {
        dbConnect();
        String sql = "SELECT * FROM avail_hotel where hotel_id = '"+hotel+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public static ResultSet getFlightSeatsWhere(String flight) throws SQLException, ClassNotFoundException {
        dbConnect();
        String sql = "SELECT * FROM avail_flight where flight_id = '"+flight+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public static ResultSet getTrainSeatsWhere(String train) throws SQLException, ClassNotFoundException {
        dbConnect();
        String sql = "SELECT * FROM avail_train where train_id = '"+train+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public static ResultSet getFlightsWhere(String a) throws SQLException, ClassNotFoundException {
        dbConnect();
        String sql = "SELECT * FROM flight where Flight_number = '"+a+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public static ResultSet getTrainWhere(String a) throws SQLException, ClassNotFoundException {
        dbConnect();
        String sql = "SELECT * FROM trains where Train_id = '"+a+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public ResultSet getHotel() throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM hotel;";  // Query to get the hotels from db
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }

    public ResultSet getTrains() throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM trains";  // Query to get the trains from db
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }

    public static ResultSet getHotelWhere(String a) throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM hotel where hotel_id = '"+a+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }

    public ResultSet getAvailHotels(String a) throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM hotel_order where customer_id = '"+a+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public ResultSet getAvailFlights(String a) throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM flight_order where customer_id = '"+a+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public ResultSet getAvailTrain(String a) throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM train_order where customer_id = '"+a+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        return (rst);
    }
    public static String getHotelNameById(String a) throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT Hotel_name FROM hotel where Hotel_id = '"+a+"'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery();
        String columnValue = "";
        ResultSetMetaData rsmd = rst.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rst.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                columnValue = rst.getString(i);
            }
            System.out.println("");
        }        
        return columnValue;
    }
}
