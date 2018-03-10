// Saved as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PlanDetailsServlet extends HttpServlet {  // JDK 6 and above only

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
         out.println("<html><head><title>Plan Details</title></head><body>");
         String userName;

         HttpSession session = request.getSession(false);
         if (session == null) {
            out.println("<h3>You have not <a href='login.html'>Login</a>!</h3>");
         } else {
            synchronized (session) {
               userName = (String) session.getAttribute("username");
               out.println("<h3>Hello! " + userName + "</h3>");
               out.println("<p><a href='logout'>Logout</a></p>");
            }
          }
         // Step 3: Execute a SQL SELECT query
         String sqlStr = "SELECT * FROM Plan WHERE planID = "
               + request.getParameter("planID");

         // Print an HTML page as output of query
         ResultSet rset = stmt.executeQuery(sqlStr);
         rset.next();
         out.println("<h2>" + rset.getString("planTitle") + "</h2>");
            // Print a paragraph <p>...</p> for each row
         out.println("<p>Starting From: " + rset.getString("startCity") + "</p>");

         String sqlStr2 = "SELECT * FROM Plan_Date WHERE planID = "
               + request.getParameter("planID");

         ResultSet rset2 = stmt.executeQuery(sqlStr2);
         while(rset2.next()){
            out.println("<p>Duration: "+rset2.getDate("startDate")+" - "+rset2.getDate("endDate")+"</p>");
            out.println("<p>$"+rset2.getInt("price")+"</p>");
            out.println("<p>"+rset2.getInt("remaining_seat")+" seats remaining. </p>");
         }

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