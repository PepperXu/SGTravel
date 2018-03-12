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
         out.println("<html><head><title>Search Results</title></head><body>");
          String userName;

         HttpSession session = request.getSession(false);
         if (session == null) {
            out.println("<h3>You have not <a href='login.html'>Login</a>!</h3>");
         } else {
            synchronized (session) {
               userName = (String) session.getAttribute("username");
               out.println("<h3>Hello! <a href='account'>" + userName + "</a></h3>");
               out.println("<p><a href='logout'>Logout</a></p>");
            }
          }
         // Step 2: Create a "Statement" object inside the "Connection"
         stmt = conn.createStatement();
         String startCity = request.getParameter("startCity");
         String city = request.getParameter("city");
         int numOfTravellers = Integer.parseInt(request.getParameter("numTravellers"));
         if (city == null || startCity == null) {
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

         String date1 = request.getParameter("startDate");
         String date2 = request.getParameter("endDate");

//
         java.util.Date sDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
         java.util.Date eDate = new SimpleDateFormat("MM/dd/yyyy").parse(date2);

         java.sql.Date startDate = new java.sql.Date(sDate.getTime());
         java.sql.Date endDate = new java.sql.Date(eDate.getTime());
         String sqlStr = "SELECT Plan.planID, Plan.planTitle, Plan.country, Plan_Date.price, Plan.duration, Plan_Date.startDate, Plan_Date.endDate, Plan_Date.remaining_seat, Plan_Date.itemID FROM Plan, Plan_City, City, Plan_Date WHERE ";

         if(city != "0"){
               sqlStr += "City.city = "
               + "'" + city + "'" + " AND Plan_City.cityID = City.cityID AND Plan_City.planID = Plan.planID AND ";
         }
         if(startCity != "0"){
                sqlStr += "Plan.startCity = '" + startCity + "' AND ";
         }

            sqlStr += "Plan.planID = Plan_Date.planID AND Plan_Date.startDate >= "
               + "'" + startDate + "'" + " AND Plan_Date.endDate <= " + "'" + endDate + "'" + " AND Plan_Date.remaining_seat >= " + numOfTravellers;




          //sqlStr += "'" + travelPlans[0] + "'";  // First city
//
          //for (int i = 1; i < travelPlans.length; ++i) {
          //sqlStr += ", '" + travelPlans[i] + "'";  // Subsequent cities need a leading commas
       //}


         // Print an HTML page as output of query

         out.println("<h2>Thank you for your search.</h2>");
         out.println("<p>You query is: " + sqlStr + "</p>");
         out.println("<p>Start Date: " + startDate + " End Date: " + endDate + "</p>");
         ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

         // Step 4: Process the query result
         int count = 0;
         while(rset.next()) {
            // Print a paragraph <p>...</p> for each row
            out.println("<form method='post' action='detail'>");
            out.println("<input type='hidden' name='itemID' value=" + rset.getInt("itemID") + " />");
            out.println("<p>planID: "+ rset.getInt("planID") + "</p> ");
            out.println("<input type='submit' value='" + rset.getString("planTitle") + "' />");
            out.println("<p>Duration: "+rset.getDate("startDate")+" - "+rset.getDate("endDate")+"</p>");
            out.println("<p>$"+rset.getInt("price")+"</p>");
            out.println("<p>"+rset.getInt("remaining_seat")+" seats remaining. </p>");
            out.println("</form>");
            out.println("<form method='post' action='checkout'>");
            out.println("<input type='hidden' name='itemID' value=" + rset.getInt("itemID") + " />");
            out.println("<input type='submit' value='join' />");
            out.println("</form>");
            out.println("<br />");
            out.println("<p>===================================</p>");
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
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      doGet(request, response);
   }
}

