import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getQuestions extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String question = request.getParameter("question");
            try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/quizup";
            String uname="root";
            String password="root";
            String query =("create table "+question+" (answers varchar(225));");
            String query1 =("insert into questlist values('"+question+"');");
                
            Connection conn=DriverManager.getConnection(url,uname,password);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(query);
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
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
