import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.UUID;

public class PurchaseServlet extends HttpServlet {  // JDK 6 and above only

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
         out.println("<html><head><title>Purchase Status</title></head><body>");
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



         int itemID = Integer.parseInt(request.getParameter("itemID"));
         int numOfTravellers = Integer.parseInt(request.getParameter("numTravellers"));

         String sqlStr = "SELECT * FROM Plan_Date WHERE itemID = " + itemID + " AND remaining_seat >= " + numOfTravellers;
         ResultSet rset = stmt.executeQuery(sqlStr);
         if(rset.next()){

            String uniqueID = UUID.randomUUID().toString();

            sqlStr = "INSERT INTO Plan_Users VALUES ('"+uniqueID+"', "+itemID+", "+rset.getInt("planID")+", '"+userName+"', "+rset.getInt("price")+", "+numOfTravellers+")";
            int count = stmt.executeUpdate(sqlStr);
            sqlStr = "UPDATE Plan_Date SET remaining_seat = remaining_seat - "+numOfTravellers+" WHERE itemID = "+itemID;
            count = stmt.executeUpdate(sqlStr);
            out.println("<h2>Thanks for purchasing!</h2>");
            out.println("<p><a href='index'>Back to main</a></p>");


         }else{
            out.println("<h2>Not Enough Vacancies!</h2>");
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