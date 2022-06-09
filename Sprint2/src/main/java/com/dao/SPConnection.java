package com.dao;

import com.model.Detective;
import jdk.jpackage.internal.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SPConnection {
    private final String CONNECT_PATH = "jdbc:mysql://localhost:3306/";
    private final String USERNAME = "root";
    private final String PASSWORD = "123456";

    public Connection connect() {
        try {
            // Kiểm tra thư viện JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Kết nối tới CSDL
            Connection cnn = DriverManager.getConnection(CONNECT_PATH, USERNAME, PASSWORD);
            //System.out.println("KẾT NỐI THÀNH CÔNG!");
            return cnn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SPConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(SPConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
