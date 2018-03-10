// Saved as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckOutServlet extends HttpServlet {  // JDK 6 and above only

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
         out.println("<html><head><title>Checkout</title></head><body>");
         String userName;

         HttpSession session = request.getSession(false);
         if (session == null) {
            out.println("<h3>You have not <a href='login.html'>Login</a>!</h3>");
            return;
         } else {
            synchronized (session) {
               userName = (String) session.getAttribute("username");
               out.println("<h3>Hello! " + userName + "</h3>");
               out.println("<p><a href='logout'>Logout</a></p>");
            }
          }
         // Step 3: Execute a SQL SELECT query
         String sqlStr = "SELECT * FROM Plan_Date WHERE itemID = "
               + request.getParameter("itemID");

         // Print an HTML page as output of query
         ResultSet rset = stmt.executeQuery(sqlStr);
         rset.next();
         out.println("<p>item ID:"+rset.getInt("itemID"));
         Date startDate = rset.getDate("startDate");
         Date endDate = rset.getDate("endDate");
         int price = rset.getInt("price");
         String sqlStr2 = "SELECT * FROM Plan WHERE planID = " + rset.getInt("planID");
         ResultSet rset2 = stmt.executeQuery(sqlStr2);
         rset2.next();

         out.println("<h3>Confirm purchasing "+rset2.getString("planTitle")+"</h3>");

         out.println("<p>Departure: "+startDate+" from "+rset2.getString("startCity")+"</p>");
         out.println("<p>Return: "+endDate+"</p>");
         out.println("<h2>Price: "+price+"</h2>");
         out.println("<form method='post' action='purchase'>");
         out.println("<input type='hidden' name='itemID' value=" + request.getParameter("itemID") + " />");
         out.println("<p>Number of travellers: <input type='number' name='numOfPeople' /></p>");
         out.println("<input type='submit' value='purchase' />");
         out.println("</form>");

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