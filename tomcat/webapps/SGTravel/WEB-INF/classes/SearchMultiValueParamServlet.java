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
         out.println("<!DOCTYPE html><html lang='en'><head> <meta charset='utf-8'> <title>Yet Another Travel Advisor!</title> <meta content='width=device-width, initial-scale=1.0' name='viewport'> <meta content='' name='keywords'> <meta content='' name='description'> <!-- For-Mobile-Apps --> <meta name='viewport' content='width=device-width, initial-scale=1' /> <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> <meta name='keywords' content='Travel Packages Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design' /> <script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script> <!-- //For-Mobile-Apps --> <!-- Style --> <link rel='stylesheet' href='css/style.css' type='text/css' media='all' /> <link href='css/font-awesome.css' rel='stylesheet'> <!-- Default-JavaScript-File --> <script type='text/javascript' src='js/jquery.min.js'></script> <!-- Bootstrap CSS File --> <link href='lib/bootstrap/css/bootstrap.min.css' rel='stylesheet'> <!-- Libraries CSS Files --> <link href='lib/font-awesome/css/font-awesome.min.css' rel='stylesheet'> <link href='lib/animate/animate.min.css' rel='stylesheet'> <link href='lib/ionicons/css/ionicons.min.css' rel='stylesheet'> <link href='lib/owlcarousel/assets/owl.carousel.min.css' rel='stylesheet'> <link href='lib/lightbox/css/lightbox.min.css' rel='stylesheet'> <!-- Web-Fonts --> <link href='//fonts.googleapis.com/css?family=Raleway:400,500,600,700,800' rel='stylesheet' type='text/css'> <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'> <link href='https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700' rel='stylesheet'> <!-- //Web-Fonts --></head><body> <!--========================== Header ============================--> <header id='header'> <div class='container-fluid'> <div id='logo' class='pull-left'> <h1><a href='index' class='scrollto'>Yet Another Trip Advisor</a></h1> <!-- Uncomment below if you prefer to use an image logo --> <!-- <a href='#intro'><img src='img/logo.png' alt='' title='' /></a>--> </div> <nav id='nav-menu-container'> <ul class='nav-menu'> <li class='menu-active'><a href='index.html'>Search</a></li> <li class='menu-has-children'><a href='service.html'>Service</a> <ul> <li><a href='service.html'>Travel</a></li> <li><a href='#hotel'>Hotels</a></li> <li><a href='#flight'>Flights</a></li> </ul> </li> <li><a href='about.html'>About Us</a></li> <li><a href='contact.html'>Contact</a></li> </ul> </nav><!-- #nav-menu-container --> </div> </header><!-- #header --><!--================= Empty Session ============================--><section> <div class='top'> </div></section><!--========================== Login in/ Register ============================--><section class='loginregister'> <div class='container'> <div class='login'>");
          String userName;

         HttpSession session = request.getSession(false);
         if (session == null) {
            out.println("<ul><li><a href='register.html'> Create Account </a></li><li><a href='login.html'>Login</a></li></ul>");
         } else {
            synchronized (session) {
               userName = (String) session.getAttribute("username");
               out.println("<ul><li><a href='account'>Hello! " + userName + "</a></li><li><a href='logout'>Logout</a></li></ul>");
            }
          }

          out.println("</div> </div></section><!--========================== search result ============================--> <section id='searchresult'> <div class='container'> <header class='section-header'> <br/> <br/> <h3 class='section-title'>Search Results</h3> </header>");
         // Step 2: Create a "Statement" object inside the "Connection"
         stmt = conn.createStatement();
         String startCity = request.getParameter("startCity");
         String city = request.getParameter("city");
         int numOfTravellers = Integer.parseInt(request.getParameter("numTravellers"));
         if (city == null || startCity == null) {
            out.println("<p align='center'>Please go back and search again. </p></div></section><div class='footer'> <p> IM2073 Course Project: Yet Another Trip Advisor.<br/>Presented by Xu Peisen and Huang Xinwei <br/><br/></p> </div></body></html>");
            return; // Exit doGet()
        }
         // Step 3: Execute a SQL SELECT query
         //String[] travelPlans = request.getParameterValues("plan");  // Returns an array
//
         // if (travelPlans == null) {
         //   out.println("<h2>Please go back and search again.</h2>");
         //   return; // Exit doGet()
         //}
        out.println("<p align='center'>Thank you for your search</p><div class='row hotdeals-cols'>");
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

         ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

         // Step 4: Process the query result
         int count = 0;
         while(rset.next()) {
            // Print a paragraph <p>...</p> for each row

            out.println(" <div class='col-lg-4 col-md-6 portfolio-item filter-web wow fadeInUp' data-wow-delay='0.1s'> <div class='portfolio-wrap'> ");
            out.println("<form method='post' action='detail' name='myform'>");
            out.println("<input type='hidden' name='itemID' value=" + rset.getInt("itemID") + " />");
            out.println("<a href='javascript: submitform()''><img src='images/2.jpg' class='img-fluid' alt=''></a>");
            out.println("<p>Plan ID: "+ rset.getInt("planID"));
            out.println("<br/>Plane Title: " + rset.getString("planTitle"));
            out.println("<br/>Duration: " +rset.getDate("startDate")+" - "+rset.getDate("endDate"));
            out.println("<br/>Price: $" + rset.getInt("price"));
            out.println("<br/> Remaining Seats: "+rset.getInt("remaining_seat")+"</p>");
            out.println("</form>");
            out.println("<script type='text/javascript'>function submitform(){ if(document.myform.onsubmit && !document.myform.onsubmit()) { return; } document.myform.submit();}</script>");
            out.println("<form method='post' action='checkout'>");
            out.println("<input type='hidden' name='itemID' value=" + rset.getInt("itemID") + " />");
            out.println("<div class = 'button' align='center'><input type='submit' value='Join' ></div>");
            out.println("</form>");
            out.println("</div></div>");
            ++count;
         }
         if(count == 0){
            out.println("<p align='center'>No Record found!</p>");
         }
         out.println("</div> </div></section><div class='footer'> <p> IM2073 Course Project: Yet Another Trip Advisor.<br/>Presented by Xu Peisen and Huang Xinwei <br/><br/></p> </div></body></html>");


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

