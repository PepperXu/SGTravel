// Saved as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class PlanOrderServlet extends HttpServlet {  // JDK 6 and above only
 
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
            "jdbc:mysql://localhost:3306/ebookshop", "myuser", "xxxx");  // <<== Check
 
         // Step 2: Create a "Statement" object inside the "Connection"
         stmt = conn.createStatement();
 
        // Step 3 & 4: Execute a SQL SELECT query and Process the query result
out.println("<html><head><title>Order Results</title></head><body>");
 
// Retrieve the books' id. Can order more than one books.
String[] ids = request.getParameterValues("id");
if (ids != null) {
   String sqlStr;
   int count;
 
   // Process each of the books
   for (int i = 0; i < ids.length; ++i) {
      // Update the qty of the table books
      sqlStr = "UPDATE books SET qty = qty - 1 WHERE id = " + ids[i];
      out.println("<p>" + sqlStr + "</p>");  // for debugging
      count = stmt.executeUpdate(sqlStr);
      out.println("<p>" + count + " record updated.</p>");
 
      // Create a transaction record
      sqlStr = "INSERT INTO order_records (id, qty_ordered) VALUES ("
            + ids[i] + ", 1)";
      out.println("<p>" + sqlStr + "</p>");  // for debugging
      count = stmt.executeUpdate(sqlStr);
      out.println("<p>" + count + " record inserted.</p>");
      out.println("<h3>Your order for book id=" + ids[i]
            + " has been confirmed.</h3>");
   }
   out.println("<h3>Thank you.<h3>");
} else { // No book selected
   out.println("<h3>Please go back and select a book...</h3>");
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
}
