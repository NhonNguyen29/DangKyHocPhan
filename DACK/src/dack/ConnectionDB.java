/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dack;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
/**
 *
 * @author nguyen tran hoa
 */




public class ConnectionDB {
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=DACK;user=hoa284;password=123456@;encrypt=true;trustServerCertificate=true";
    private static final String USERNAME = "hoa284";
    private static final String PASSWORD = "123456@";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver not found.");
        }
    }
    public static ResultSet SELECT(String SQLQuery)
    {  
         ResultSet rs = null;
        try{
             Connection connection = getConnection();
             Statement stm = connection.createStatement();
             rs = stm.executeQuery(SQLQuery);
             
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    public int getSV_ID(String ten, String pass) {
    try {
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement stmt = conn.prepareStatement("SELECT SV_ID FROM SinhVien WHERE TenTK = ? AND Password = ?");
        stmt.setString(1, ten);
        stmt.setString(2, pass);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("SV_ID");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu không tìm thấy SV_ID
}
}

   

