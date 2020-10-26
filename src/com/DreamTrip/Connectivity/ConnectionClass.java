package com.DreamTrip.Connectivity;
import java.sql.*;


public class ConnectionClass
{
    static String dbusername = "root";
    static String dbpassword = "harshmody";
    static String dburl = "jdbc:mysql://localhost:3306/dreamtrip";
    static String dbdriver = "com.mysql.jdbc.Driver";
    static Connection dbcon;
    static void dbConnect() throws SQLException //exception will be generated ,in case the driver is not found
    {
        dbcon = DriverManager.getConnection(dburl, dbusername, dbpassword);//we have creatd these variables earlier
    }
    void dbclose() throws SQLException//will be used for closing database connection
    {
        dbcon.close();
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
