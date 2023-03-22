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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Origin
 */
public class createQuiz extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String quiztitle = request.getParameter("quiztitle");
        int questions = Integer.parseInt(request.getParameter("noq"));
        int choices = Integer.parseInt(request.getParameter("noc"));
        String sub = request.getParameter("subject");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/quizup";
            String uname="root";
            String password="root";
            String query =("insert into quizlist values('"+quiztitle+"', '"+sub+"', '"+questions+"', '"+choices+"')");
                
            Connection conn=DriverManager.getConnection(url,uname,password);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(query);
            
            String addon = "cho1 varchar(50)";
            for(int i= 2; i <= choices; i++){
                addon+= ", ";
                addon += "cho"+i+" varchar(50) ";
            }
                
            String query2 =("create table "+quiztitle+"(question varchar(225), "+addon+", correctOption varchar(50), dis varchar(225));");
            Statement stmt2=conn.createStatement();
            stmt2.executeUpdate(query2);

            }
            catch(Exception e){
                out.print("<html>"
                        + "<body>"
                        + "<h1>Try again</h1>"
                        + "error: "+ e
                        + "</body>"
                        + "</html>");
            }
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>create Quiz</title>");
            out.println("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
"  <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
"  <link href=\"https://fonts.googleapis.com/css2?family=Montserrat&family=Ubuntu:wght@300&display=swap\" rel=\"stylesheet\">\n" +
"  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css\" integrity=\"sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l\" crossorigin=\"anonymous\">\n" +
"  <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n" +
"  <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\" integrity=\"sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN\" crossorigin=\"anonymous\"></script>\n" +
"  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js\" integrity=\"sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF\" crossorigin=\"anonymous\"></script>");
            out.print("<style>"
                    + ".hideit{"
                    + "visibility: hidden;"
                    + "}"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n" +
"    <a class=\"navbar-brand\" href=\"\">QuizUp</a>\n" +
"    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"      <span class=\"navbar-toggler-icon\"></span>\n" +
"    </button>\n" +
"  </nav>");
            out.println("<div style='margin: 20px;'>");
            out.println("<form action='saveQuizData' method='POST'>"); //here is wher the form starts
            out.println("<div style= 'margin: 35px;'>");
            out.println("<h3> <strong> "+ quiztitle +" </strong> <h3>");
            
            for(int i =1; i<= questions; i++){
                out.println("<h5> <strong> Question "+ i +": </strong> </h5>");
                out.println("<textarea type='text' name='que"+ i +"' rows='1' cols='100' style = 'border-top-width: 0px;\n" +
                    " border-left-width: 0px;\n" +
                    " border-right-width: 0px;'></textarea> <br>"); 
                
                for(int j =1; j<= choices; j++){
                    out.println("<p><em>option "+ j +":</em>  </p>");
                    out.println("<textarea type='text' name='"+i+"cho"+ j +"' rows=1 cols='60' style = 'border-top-width: 0px;\n" +
                    " border-left-width: 0px;\n" +
                    " border-right-width: 0px;'></textarea>");
                }
                
                
                out.println("<p><em>right answer:</em></p>");
                out.println("<select name='answer"+ i +"'>");
                for(int k=1; k<= choices; k++){
                    out.println("<option value='"+k+"'>option "+ k +"</option>");
                }
                
                out.println("</select>");
                out.println("<br><br>");
                
                out.print("<p><em>description:</em><p>");
                out.print("<textarea name='dis"+i+"' rows=\"3\" cols=\"50\"></textarea>");
            }
            out.println("</div>");
            out.println("<br>");
            out.println("<input type='submit' name='create' class='btn btn-dark'>");
            out.print("<input type='text' name='tableName' value="+quiztitle+" class='hideit'>");
            out.print("<input type='text' name='NoOfQue' value="+questions+" class='hideit'>");
            out.print("<input type='text' name='NoOfCho' value="+choices+" class='hideit'>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
    }
}