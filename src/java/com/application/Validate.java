/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application;
import java.sql.*;

/**
 *
 * @author Devab
 */
public class Validate {
    public static boolean isValidUser(String name, String password) {
        boolean userExists = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://localhost:3306/vit";
            String USER_NAME = "root";
            String PASSWORD = "Devabhiram@2003";
            Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            String SQL = "SELECT * FROM registration WHERE name=? AND password=?";
            
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            userExists = rs.next();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return userExists;
    }
}
