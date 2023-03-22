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

public class quizResult extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
"    \n"+ //div2
"    <div>\n");
        String quizname = request.getParameter("qname");
        out.print("<h1 style='text-align: center; color: #708090; margin-top: 20px;'>"+quizname+" Result</h1>");
        try{
                Class.forName("com.mysql.jdbc.Driver");
                String url ="jdbc:mysql://localhost:3306/quizup";
                String uname ="root";
                String pass = "root";
                String query1 = "select questions from quizlist where quiztitle ='"+quizname+"'";
                Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                Statement st1 = con.createStatement();
                ResultSet rs = st.executeQuery(query1);
                rs.next();
                int noque = rs.getInt("questions");
                
                out.print("<div style='margin-left: 60px;'>");
                out.print("<form action='' method='post'>");
                int score = 0;
                for(int i=1; i <= noque ;i++){
                    ResultSet rs2 = st.executeQuery("select question from "+quizname);
                    rs2.next();
                    String que = rs2.getString("question");
                    ResultSet rs1 = st1.executeQuery("select correctOption from "+quizname+" where question ='"+que+"'");
                    rs1.next();
                    int crt = rs1.getInt("correctOption");
                    ResultSet rss = st.executeQuery("select cho"+crt+" from "+quizname+" where question ='"+que+"'");
                    rss.next();
                    String crt_ans = rss.getString("cho"+crt);
                    String user_ans = request.getParameter("qus"+i);
                    ResultSet rsd = st.executeQuery("select dis from "+quizname+" where question ='"+que+"'");
                    rsd.next();
                    String dis = rsd.getString("dis");
                    out.print("<h4> Question:"+i+" "+que+"</h4><br>");
                    out.print("<h6> Your answer: "+user_ans+"</h6><br>");
                    out.print("<h6> correct answer: "+crt_ans+"</h6><br>");
                    out.print("<h6> Explanation: "+dis+"</h6><br>");
                    out.print("<br><br>");
                    if(user_ans.equals("null")){
                        score +=0;
                    }
                    else{
                        if(user_ans.equals(crt_ans)){
                        score += 1;
                    }
                    }
                }
                ResultSet rsm = st.executeQuery("select questions from quizlist where quiztitle = '"+quizname+"'");
                rsm.next();
                int total = rsm.getInt("questions");
                out.print("<h3>Your Score: "+score+"/"+total+"</h3>");
                out.print("<form action='stuIndex.html'><input type ='submit' value='Go back' class='btn btn-dark'></form>");
                out.print("</div>");
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
