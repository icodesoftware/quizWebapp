/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Origin
 */
public class saveQuizData extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String tableName = request.getParameter("tableName");
        int noOfQ = Integer.parseInt(request.getParameter("NoOfQue"));
        int noOfCho = Integer.parseInt(request.getParameter("NoOfCho"));
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/quizup";
            String uname="root";
            String password="root";
            String opt = "?";
            for(int i = 2; i<=noOfCho; i++){
                opt += ", ";
                opt += "?";
            }
            String query =("insert into "+tableName+" values(?, "+opt+", ?, ?);");
                
            Connection conn=DriverManager.getConnection(url,uname,password);
            PreparedStatement stmt=conn.prepareStatement(query);
            
            
            for(int i= 1; i <= noOfQ; i++){
                String ques = request.getParameter("que"+i);
                stmt.setString(1, ques);
                
                for(int j = 1; j<=noOfCho;j++){
                    String choic = request.getParameter(i+"cho"+j);
                    stmt.setString(j+1, choic);
                }
                
                int ans = Integer.parseInt(request.getParameter("answer"+i));
                stmt.setInt(noOfCho+2, ans);
                String dis = request.getParameter("dis"+i);
                stmt.setString(noOfCho+3, dis);
                stmt.executeUpdate();
            }
            response.sendRedirect("quizSuccess.html");

            }
            catch(Exception e){
                response.sendRedirect("quizFail.html");
            }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
