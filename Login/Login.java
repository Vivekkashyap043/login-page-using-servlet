import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Retrieve user credentials from request parameters
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // Query database to verify user credentials
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gpt?autoReconnect=true&useSSL=false", "root", "vicky");
            PreparedStatement ps = con.prepareStatement("select * from student where name=? and password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            String n = "vivek";
            if (rs.next()) {
                PrintWriter out = response.getWriter();
                n = rs.getString(1);
                out.println("Hello,  "+n+" you Login Successfully!");
            } else {
                PrintWriter out = response.getWriter();
                out.println("Login Failed: Invalid Username or Password");
            }
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Login Failed: " + ex.getMessage());
        }
    }
}
