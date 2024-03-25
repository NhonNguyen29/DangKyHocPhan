/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author nguyen tran hoa
 */
public class Server {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=DACK;encrypt=true;trustServerCertificate=true";
    private static final String DB_USERNAME = "hoa284";
    private static final String DB_PASSWORD = "123456@";

    public static void start() {
     
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ServerSocket sv = new ServerSocket(2023);
            System.out.printf("Server is connecting...");

            Socket socket = sv.accept();
            System.out.printf("Client has connected");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String ten = in.readUTF();
            String pass = in.readUTF();

            if (authenticate(ten, pass)) {
                frm_Main form = new frm_Main();
                form.setVisible(true);
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
        public static boolean authenticate(String ten, String pass) {
            Connection connection = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                String SQLQuery = "SELECT * FROM SinhVien WHERE SV_ID = ? AND Password = ?";
                stm = connection.prepareStatement(SQLQuery);
                stm.setString(1, ten);
                stm.setString(2, pass);
                rs = stm.executeQuery();

                boolean authenticated = rs.next();

                        if (!authenticated) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
          
        }

                return authenticated;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stm != null) {
                        stm.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

    public static void main(String[] args) {

        start();
    }
}
