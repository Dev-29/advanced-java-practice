/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

/**
 *
 * @author Devab
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException,IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        
        if (Validate.isValidUser(name, password)) {
            Cookie c = new Cookie("user_name",name);
            res.addCookie(c);
            RequestDispatcher rd = req.getRequestDispatcher("/Homepage");
            rd.forward(req, res);
        } else {
            out.println("Invalid Password/Name");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, res);
        } 
    }
}
