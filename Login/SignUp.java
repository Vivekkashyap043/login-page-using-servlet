import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignUp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve user data from request parameters
        String name = request.getParameter("username");
        String pin = request.getParameter("pin");
        String college = request.getParameter("college");
        String password= request.getParameter("password");

        // Insert user data into database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gpt?autoReconnect=true&useSSL=false", "root", "vicky");
            PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, pin);
            ps.setString(3, college);
            ps.setString(4, password);
            ps.executeUpdate();

            PrintWriter out = response.getWriter();
            out.println("Registration Successful!");
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Registration Failed: " + ex.getMessage());
        }
    }
}
