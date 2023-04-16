import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignUp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Retrieve user data from request parameters
        String name = request.getParameter("name");
        String pin = request.getParameter("pin");
        String college = request.getParameter("college");
        String password= request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", "root", "vicky");
            Statement smt = con.createStatement();
            smt.executeUpdate("create database if not exists gpt");
            smt.executeUpdate("use gpt");
            smt.executeUpdate("create table if not exists student(name text, pin text, college text, password text)");
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
