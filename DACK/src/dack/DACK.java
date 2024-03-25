/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dack;

/**
 *
 * @author nguyen tran hoa
 */
public class DACK {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
    // Khởi tạo đối tượng Server
    Server server = new Server();
    
    // Khởi chạy máy chủ
    server.start();
    
    // Hiển thị form đăng nhập
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new frm_Login().setVisible(true);
        }
    });
}
    
}
