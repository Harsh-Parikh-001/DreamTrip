package com.DreamTrip.Connectivity;
import java.sql.*;


public class ConnectionClass
{
    static String dbusername = "root";
    static String dbpassword = "harshmody";
    static String dburl = "jdbc:mysql://localhost:3306/dreamtrip";
    static Connection dbcon;
    static void dbConnect() throws SQLException //exception will be generated ,in case the driver is not found
    {
        dbcon = DriverManager.getConnection(dburl, dbusername, dbpassword);//we have creatd these variables earlier
    }
    void dbclose() throws SQLException//will be used for closing database connection
    {
        dbcon.close();
    }
    public static int insertCustomer(String name, String phone, String email, String address, String psswd, String key) throws SQLException
    {
        int x;
        dbConnect();
        String sql = "INSERT INTO dreamtrip.customer(Cust_name, Contact, Email,Address,pass_word,encrip_key) VALUES  (?,?,?,?,?,?)";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        pstmt.setString(0, name);
        pstmt.setString(1, phone);
        pstmt.setString(2, email);
        pstmt.setString(3, address);
        pstmt.setString(4, psswd);
        pstmt.setString(5, key);
        x =  pstmt.executeUpdate();
        return(x);
    }
    public static ResultSet getFlights() throws ClassNotFoundException, SQLException
    {
        dbConnect();
        String sql = "SELECT * FROM flight;";
        PreparedStatement pstmt = dbcon.prepareStatement(sql); //javas built in class
        ResultSet rst = pstmt.executeQuery();
        ResultSetMetaData rsmd = rst.getMetaData();
        while (rst.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rst.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
        return (rst);
    }
}
