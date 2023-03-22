/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Origin
 */
public class postans extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
            try{
                String a = request.getParameter("ans");
                String q = request.getParameter("qtable");
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/quizup";
            String uname="root";
            String password="root";
            String query1 =("insert into "+q+" values('"+a+"');");
                
            Connection conn=DriverManager.getConnection(url,uname,password);
            Statement stmt2=conn.createStatement();
            stmt2.executeUpdate(query1);
            response.sendRedirect("qposted.html");

            }
            catch(Exception e){
                out.print("<html>"
                        + "<body>"
                        + "<h1>Try again</h1>"
                        + "error: "+ e
                        + "</body>"
                        + "</html>");
            }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
