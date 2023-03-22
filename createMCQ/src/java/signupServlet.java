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
public class signupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String confo = request.getParameter("password2");
        String mail = request.getParameter("email");
        String type = request.getParameter("type");
        
        try{
                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/quizup";
                String uname="root";
                String password="root";
                String query1=("insert into teacher values('"+name+"', '"+pass+"', '"+mail+"');");
                String query2=("insert into learner values('"+name+"', '"+pass+"', '"+mail+"');");
                
                Connection conn=DriverManager.getConnection(url,uname,password);
                
                if(pass.equals(confo)){
                    if (type.equals("teacher")){
                        Statement stmt1=conn.createStatement();
                        stmt1.executeUpdate(query1);
                        response.sendRedirect("index.html");
                    }
                    else if(type.equals("learner")){
                        Statement stmt2=conn.createStatement();
                        stmt2.executeUpdate(query2);
                        response.sendRedirect("stuIndex.html");
                    }
                }
                else{
                    out.print("<html>"
                + "<body>"
                + "<h1>see!</h1>"
                + "</body>"
                + "</html>");
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
