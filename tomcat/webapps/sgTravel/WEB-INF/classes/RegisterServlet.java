import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {  // JDK 6 and above only

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      Connection conn = null;
      Statement stmt = null;
      try {
         // Step 1: Create a database "Connection" object
         // For MySQL
         Class.forName("com.mysql.jdbc.Driver");  // Needed for JDK9/Tomcat9
         conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/SGTravel", "billy", "1234");  // <<== Check

         // Step 2: Create a "Statement" object inside the "Connection"
         stmt = conn.createStatement();

         String userName = request.getParameter("email");
         String password = request.getParameter("password");
         String confirmPassword = request.getParameter("confirm_password");
         boolean hasUserName = userName != null && ((userName = userName.trim()).length() > 0);
         boolean hasPassword = password != null && ((password = password.trim()).length() > 0);
         boolean samePassword = password == confirmPassword;

         if (!hasUserName) {
            out.println("<h3>Please Enter Your username!</h3>");
            out.println("<p><a href='register.html'>Back to Registration Menu</a></p>");
         } else if (!hasPassword) {
            out.println("<h3>Please Enter Your password!</h3>");
            out.println("<p><a href='register.html'>Back to Registration Menu</a></p>");
         } else if (!samePassword) {
            out.println("<h3>Passwords not the same. Please check your password!</h3>");
            out.println("<p><a href='register.html'>Back to Registration Menu</a></p>");
         }
         else {
         // Step 3: Execute a SQL SELECT query
         	String sqlQ = "Select * FROM Users WHERE STRCMP(username, '"+userName+"') = 0";
         	ResultSet rset = stmt.executeQuery(sqlQ);
         	if(rset.next()){
				out.println("<h3>Username already exists!</h3>");
         	} else {


         		String sqlStr = "INSERT INTO Users VALUES ('" + userName + "'"+ ", password('" + password + "'))";

         		int count = stmt.executeUpdate(sqlStr);

         // Print an HTML page as output of query
         		out.println("<html><head><title>Registration Successful!</title></head><body>");
         		out.println("<h2>Thank you for your registration! Please <a href='login.html'>Log In</a>. </h2>");
         		out.println("</body></html>");
         	}
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
     } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
     } finally {
         out.close();
         try {
            // Step 5: Close the Statement and Connection
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }
}