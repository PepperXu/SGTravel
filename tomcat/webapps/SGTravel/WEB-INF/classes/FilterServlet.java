// Saved as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
 @WebServlet("/searchmvp")
public class SearchMultiValueParamServlet extends HttpServlet {  // JDK 6 and above only
 
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
            "jdbc:mysql://localhost:3306/SGTravel", "billy ", "1234");  // <<== Check
 
         // Step 2: Create a "Statement" object inside the "Connection"
         stmt = conn.createStatement();
 
         // Step 3: Execute a SQL SELECT query
         String[] travelPlans = request.getParameterValues("c");  // Returns an array

          if (travelPlans == null) {
            out.println("<h2>Please go back and search again.</h2>");
            return; // Exit doGet()
         } 

         String sqlStr = "SELECT * FROM Plan WHERE city = "
               + "'" + request.getParameter("city") + "'" + "AND startDate ="+ "'" + request.getParameter("startDate") + "'"
               + " AND remaining_seat > 0 ";

          sqlStr += "'" + travelPlans[0] + "'";  // First city

          for (int i = 1; i < travelPlans.length; ++i) {
          sqlStr += ", '" + travelPlans[i] + "'";  // Subsequent cities need a leading commas
       }

 
         // Print an HTML page as output of query
         out.println("<html><head><title>Search Results</title></head><body>");
         out.println("<h2>Thank you for your search.</h2>");
         ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server
 
         // Step 4: Process the query result
         int count = 0;
         while(rset.next()) {
            // Print a paragraph <p>...</p> for each row
            out.println( "<head> Travel Plan Recommendation</head>"
                  +"<br/>"+"<h5>planID:</h5> "+ rset.getInt("<h5>planID</h5>")
                  + "<br/>" + rset.getString("<h2>planTitle</h2>")
                  + "<br/>" + "<h5>Country:</h5> "+ rset.getString("<h5>country</h5>")
                  + "<br/>"+ "<h5>Price: $</h5>" + rset.getInt("<h5>price</h5>") + "per person"
                  + "<br/>" +"<h5>Duration:</h5>" + rset.getInt("<h5>duration</h5>")
                   );
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
public void doPost (HttpServletRequest request, HttpServletResponse response)
                   throws ServletException, IOException {
   doGet(request, response);  // Re-direct POST request to doGet()
}
}

