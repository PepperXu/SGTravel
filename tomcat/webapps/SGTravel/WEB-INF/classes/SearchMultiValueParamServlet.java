// Saved as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

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
            "jdbc:mysql://localhost:3306/SGTravel", "billy", "1234");  // <<== Check

         // Step 2: Create a "Statement" object inside the "Connection"
         stmt = conn.createStatement();
         String city = request.getParameter("city");
         if (city == null) {
            out.println("<h2>Please go back and select an city</h2>");
            return; // Exit doGet()
        }
         // Step 3: Execute a SQL SELECT query
         //String[] travelPlans = request.getParameterValues("plan");  // Returns an array
//
         // if (travelPlans == null) {
         //   out.println("<h2>Please go back and search again.</h2>");
         //   return; // Exit doGet()
         //}

         String date = request.getParameter("date");
         String[] parts = date.split(" - ");
//
         java.util.Date sDate = new SimpleDateFormat("MM/dd/yyyy").parse(parts[0]);
         java.util.Date eDate = new SimpleDateFormat("MM/dd/yyyy").parse(parts[1]);

         java.sql.Date startDate = new java.sql.Date(sDate.getTime());
         java.sql.Date endDate = new java.sql.Date(eDate.getTime());

         String sqlStr = "SELECT Plan.planID, Plan.planTitle, Plan.country, Plan.price, Plan.duration FROM Plan, Plan_City, City, Plan_Date WHERE City.city = "
               + "'" + city + "'" + " AND Plan_City.cityID = City.cityID AND Plan_City.planID = Plan.planID AND Plan.planID = Plan_Date.planID AND Plan_Date.startDate >= "
               + "'" + startDate + "'" + " AND Plan_Date.endDate <= " + "'" + endDate + "'";



          //sqlStr += "'" + travelPlans[0] + "'";  // First city
//
          //for (int i = 1; i < travelPlans.length; ++i) {
          //sqlStr += ", '" + travelPlans[i] + "'";  // Subsequent cities need a leading commas
       //}


         // Print an HTML page as output of query
         out.println("<html><head><title>Search Results</title></head><body>");
         out.println("<h2>Thank you for your search.</h2>");
         out.println("<p>You query is: " + sqlStr + "</p>");
         out.println("<p>Start Date: " + startDate + " End Date: " + endDate + "</p>");
         ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

         // Step 4: Process the query result
         int count = 0;
         while(rset.next()) {
            // Print a paragraph <p>...</p> for each row
            out.println( "<h5>planID:"+ rset.getInt("planID") + "</h5> "
                  + "<br/>" + "<h5>planTitle:"+rset.getString("planTitle") + "</h5>"
                  + "<br/>" + "<h5>Country: "+ rset.getString("country") + "</h5>"
                  + "<br/>"+ "<h5>Price: $" + rset.getInt("price") + "per person. </h5>"
                  + "<br/>" +"<h5>Duration:" + rset.getInt("duration") + "</h5>"
                   );
            ++count;
         }
         out.println("<p>==== " + count + " records found ====</p>");
         out.println("</body></html>");

      }
      catch (ParseException e) {
        //Handle exception here, most of the time you will just log it.
          e.printStackTrace();
      }
      catch (SQLException ex) {
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

