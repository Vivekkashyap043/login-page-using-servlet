import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gpt", "root", "vicky");
            PreparedStatement ps = con.prepareStatement("select name from student where name=? and password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            PrintWriter out = response.getWriter();
            if(rs.next()) 
                out.println("Hello,  "+rs.getString(1)+" you Login Successfully!");
            else
                out.println("Invalid Username or Password!");
        } 
        catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println(e);
        }
    }
}
