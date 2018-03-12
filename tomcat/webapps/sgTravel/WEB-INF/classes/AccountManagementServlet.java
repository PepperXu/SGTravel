import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AccountManagementServlet extends HttpServlet {  // JDK 6 and above only

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
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
         out.println("<html><head><title>My Account</title></head><body>");
         String userName;

         HttpSession session = request.getSession(false);
         if (session == null) {
            out.println("<h3>You have not <a href='login.html'>Login</a>!</h3>");
            return;
         } else {
            synchronized (session) {
               userName = (String) session.getAttribute("username");
               out.println("<h3>Hello! <a href='account'>" + userName + "</a></h3>");
               out.println("<p><a href='logout'>Logout</a></p>");
            }
         }


         String sqlStr = "SELECT * FROM Plan_Users, Plan WHERE Plan_Users.planID = Plan.planID";
         ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

         // Step 4: Process the query result
         int count = 0;
         while(rset.next()) {
            // Print a paragraph <p>...</p> for each row
            out.println("<p>" + rset.getString("purchaseID")
                  + ", " + rset.getInt("itemID")
                  + ", " + rset.getInt("planID")
                  + ", $" + rset.getInt("price")
                  + ", " + rset.getInt("amount") + " copies. </p>");
            out.println("<p>" + rset.getString("planTitle") + "</p>");
            out.println("<br />");
            ++count;
         }
         out.println("<p>==== " + count + " records found ====</p>");



         out.println("</body></html>");
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
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      doGet(request, response);
   }
}