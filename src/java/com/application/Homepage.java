/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application;
import jakarta.servlet.*;
import java.io.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/Homepage")
public class Homepage extends HttpServlet {
     public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException,IOException {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();
         out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title> LoggedIn </title>");
         out.println("</head>");
         out.println("<body>");
         
         Cookie [] cookies = req.getCookies();
         boolean cookiesExist = false;
         String name = "";
         if(cookies == null){
             return;
         } else {
             for(Cookie c:cookies) {
                 String cookieName = c.getName();
                 if("user_name".equals(cookieName)) {
                     cookiesExist = true;
                     name = c.getValue();
                 }
             }
         }
         if(cookiesExist) {
             out.println("<h1>Hello " + name + " Welcome You Have Logged in Nicely!</h1>");

         }
         else {
             out.println("<h1>You are a new user please register</h1>");
             RequestDispatcher rd = req.getRequestDispatcher("index.html");
             rd.include(req, res);
         }
         
         out.println("</body>");
         out.println("</html>");
     }
}
