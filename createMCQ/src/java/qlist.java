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

public class qlist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        out.print("<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"  <meta charset=\"utf-8\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
"  <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
"  <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
"  <link href=\"https://fonts.googleapis.com/css2?family=Montserrat&family=Ubuntu:wght@300&display=swap\" rel=\"stylesheet\">\n" +
"  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css\" integrity=\"sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l\" crossorigin=\"anonymous\">\n" +
"  <link rel=\"stylesheet\" href=\"CSS/style.css\">\n" +
"  <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n" +
"  <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\" integrity=\"sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN\" crossorigin=\"anonymous\"></script>\n" +
"  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js\" integrity=\"sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF\" crossorigin=\"anonymous\"></script>\n" +
"  <!-- font awesome -->\n" +
"  <script src=\"https://kit.fontawesome.com/1c9ad4b785.js\" crossorigin=\"anonymous\"></script>\n" +
"  <title>created successfully</title>\n" +
"      </head>\n" +
"\n" +
"<body>\n" +
"  <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n" +
"    <a class=\"navbar-brand\" href=\"\">QuizUp</a>\n" +
"    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"      <span class=\"navbar-toggler-icon\"></span>\n" +
"    </button>\n" +
"    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
"      <ul class=\"navbar-nav ml-auto\">\n" +
"        <li class=\"nav-item\">\n" +
"          <a class=\"nav-link\" href=\"stuIndex.html\">Home</a>\n" +
"        </li>\n" +
"        <li class=\"nav-item\">\n" +
"          <a class=\"nav-link\" href=\".html\">Classroom</a>\n" +
"        </li>\n" +
"        <li class=\"nav-item\">\n" +
"          <a class=\"nav-link\" href=\"\">Q&A</a>\n" +
"        </li>\n" +
"      </ul>\n" +
"    </div>\n" +
"  </nav>\n"+
"    \n" +
"    <div style=\"text-align: center\">\n" +
"        <h2 style=\"color: #708090; margin-top: 20px;\">Quizzes</h2>\n" +
"    </div>\n" + //div2
"    <div>\n");
        
        try{
                Class.forName("com.mysql.jdbc.Driver");
                String url ="jdbc:mysql://localhost:3306/quizup";
                String uname ="root";
                String pass = "root";
                String query = "select question from questlist";
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                while(rs.next()){
                String name = rs.getString("question");
                out.print("<ul>");
                out.print("<li style='text-align: center'><form action='answerQu' method='post'> <h5>"+name+"</h5> <input type='text' value='"+name+"' style='visibility: hidden' name='qname' ><input class='btn btn-dark' type='submit' value='answer'/></form></li>");
                out.print("</ul>");
                }
                
            }
            catch(Exception e){
                out.print("<h1>"+e+"</h1>");
            }
        
        out.print(

"    </div>\n" +
"\n" +
"</body>\n" +
"</html>");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
