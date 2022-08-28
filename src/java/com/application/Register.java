/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

/**
 *
 * @author Devab
 */
@WebServlet("/Register")
public class Register extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String condition = req.getParameter("condition");
        System.out.println(condition);
        if ("checked".equals(condition)) {
            out.println("<h2> Name :" + name + "<h2>");
            out.println("<h2> Password :" + password + "<h2>");
            out.println("<h2> Email :" + email + "<h2>");
            out.println("<h2> Gender :" + gender + "<h2>");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                String URL = "jdbc:mysql://localhost:3306/vit";
                String USERNAME = "root";
                String PASSWORD = "Devabhiram@2003";

                Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                String INSERT_QUERY = "INSERT INTO registration VALUES(?, ?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(INSERT_QUERY);

                ps.setString(1, name);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setString(4, gender);
                ps.setString(5, condition);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    out.println("User registered successfully!");
                    RequestDispatcher rd = req.getRequestDispatcher("login.html");
                    rd.forward(req, res);
                } else {
                    out.println("Could Not Register User");
                    RequestDispatcher rd = req.getRequestDispatcher("index.html");
                    rd.forward(req, res);
                }
            } catch (Exception e) {
                out.println("Whoops An Error Occured: " + e);
            }
        } else {
            out.println("<h2>You have not accepted terms and conditions</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            rd.include(req, res);
        }
    }
}
