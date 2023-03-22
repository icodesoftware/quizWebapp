/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Origin
 */
public class NewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/quizup";
                String uname="root";
                String password="root";
                String query1=("select * from teacher where tname='"+ name +"' and lname='"+ pass +"'");
                String query2=("select * from learner where lname='"+ name +"' and lpass='"+ pass +"'");
                
                Connection conn=DriverManager.getConnection(url,uname,password);
                Statement stmt1=conn.createStatement();
                ResultSet rs1 =stmt1.executeQuery(query1);
                
                if(rs1.next()){
                    response.sendRedirect("index.html");
                }
                else {
                    Statement stmt2=conn.createStatement();
                    ResultSet rs2 =stmt2.executeQuery(query2);
                    if(rs2.next()){
                        response.sendRedirect("stuIndex.html");
                    }
                    else{
                        response.sendRedirect("login.html");
                    }
                }
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
    }
}
