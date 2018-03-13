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
         out.println("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><title>Yet Another Travel Advisor!</title><meta content='width=device-width, initial-scale=1.0' name='viewport'> <meta content='' name='keywords'> <meta content='' name='description'><!-- For-Mobile-Apps --><meta name='viewport' content='width=device-width, initial-scale=1' /><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><meta name='keywords' content='Travel Packages Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design' /><script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script><!-- //For-Mobile-Apps --><!-- Style --><link rel='stylesheet' href='css/style.css' type='text/css' media='all' /><link href='css/font-awesome.css' rel='stylesheet'><!-- Default-JavaScript-File --> <script type='text/javascript' src='js/jquery.min.js'></script> <!-- Bootstrap CSS File --> <link href='lib/bootstrap/css/bootstrap.min.css' rel='stylesheet'> <!-- Libraries CSS Files --> <link href='lib/font-awesome/css/font-awesome.min.css' rel='stylesheet'> <link href='lib/animate/animate.min.css' rel='stylesheet'> <link href='lib/ionicons/css/ionicons.min.css' rel='stylesheet'> <link href='lib/owlcarousel/assets/owl.carousel.min.css' rel='stylesheet'> <link href='lib/lightbox/css/lightbox.min.css' rel='stylesheet'><!-- Web-Fonts --><link href='//fonts.googleapis.com/css?family=Raleway:400,500,600,700,800' rel='stylesheet' type='text/css'><link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'> <link href='https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700' rel='stylesheet'><!-- //Web-Fonts --></head><body> <!--========================== Header ============================--> <header id='header'> <div class='container-fluid'> <div id='logo' class='pull-left'> <h1><a href='index' class='scrollto'>Yet Another Trip Advisor</a></h1> <!-- Uncomment below if you prefer to use an image logo --> <!-- <a href='#intro'><img src='img/logo.png' alt='' title='' /></a>--> </div> <nav id='nav-menu-container'> <ul class='nav-menu'> <li class='menu-active'><a href='index.html'>Search</a></li> <li class='menu-has-children'><a href='service.html'>Service</a> <ul> <li><a href='service.html'>Travel</a></li> <li><a href='#hotel'>Hotels</a></li> <li><a href='#flight'>Flights</a></li> </ul> </li> <li><a href='about.html'>About Us</a></li> <li><a href='contact.html'>Contact</a></li> </ul> </nav><!-- #nav-menu-container --> </div> </header><!-- #header --><!--=================Empty Session ============================--><section><div class='top'></div></section><!--========================== Login in/ Register ============================--><section class='loginregister'><div class='container'><div class='login'>");
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
          out.println("</div></div></section><!--========================== Plan Details ============================--><section id = 'plandetail'> <div class = 'container'> <header class='section-header'> <br/><br/> <h3 class='section-title'>Plan Details</h3> </header> <div class='inforpannel'>");
          int itemID = Integer.parseInt(request.getParameter("itemID"));
         // Step 3: Execute a SQL SELECT query


         String sqlStr = "SELECT * FROM Plan_Date, Plan WHERE itemID = "
               + itemID + " AND Plan_Date.planID = Plan.planID";



         // Print an HTML page as output of query
         ResultSet rset = stmt.executeQuery(sqlStr);
         rset.next();
         int planID = rset.getInt("planID");
         out.println("<h4 align='center'> PLAN TITLE: " + rset.getString("planTitle") + "</h4>");
            // Print a paragraph <p>...</p> for each row
         out.println("<p align='center'>starting from: " + rset.getString("startCity"));
         out.println("<br/>Plan ID: "+planID);
         out.println("<br/>Date: " + rset.getDate("startDate")+" - "+rset.getDate("endDate"));
         out.println("<br/>price: $"+rset.getInt("price"));
         out.println("<br/>Remaining Seats: "+rset.getInt("remaining_seat")+"</p>");
         out.println("</div><img src='"+rset.getString("img_path")+"' alt='' class='img-fluid'></img></div></section>");


         out.println("<section id = 'flightdetail'> <div class = 'container'> <header class='section-header'> <br/><br/> <h3 class='section-title'>Flight Details</h3> </header> <br/>");

         String sqlStr0 = "SELECT * FROM Flight, Plan_Date WHERE Plan_Date.itemID = " + itemID + " AND Flight.flightID = Plan_Date.flightID_1";
         rset = stmt.executeQuery(sqlStr0);
         rset.next();
         out.println("<div class='planeleft'><h4 align='center'>Outbound Flight</h4>"
            + "<p>From "+rset.getString("departureCity")+" To "+rset.getString("arrivalCity")+" </p>"
            + "<p> Time: "+rset.getString("departureTime")+"-"+rset.getString("arrivalTime")+"  </p>"
            + "<p> Flight Number: "+rset.getString("flightNumber")+"</p>"
            + "<p> Airline: "+rset.getString("airline")+"</p>"
            + "</div>");

         String sqlStr1 = "SELECT * FROM Flight, Plan_Date WHERE Flight.flightID = Plan_Date.flightID_2 AND Plan_Date.itemID = " + request.getParameter("itemID");
         rset = stmt.executeQuery(sqlStr1);
         rset.next();
         out.println("<div class='planeright'><h4 align='center'>Return Flight</h4><p>From "+rset.getString("departureCity")+" To "+rset.getString("arrivalCity")+" </p>"
            + "<p> Time: "+rset.getString("departureTime")+"-"+rset.getString("arrivalTime")+"  </p>"
            +"<p> Flight Number: "+rset.getString("flightNumber")+"</p>"
            +"<p> Airline: "+rset.getString("airline")+"</p>"
          +"</div>");

         out.println("</div></section>");

         out.println("<div class = 'container'><form method='post' action='checkout'>");
         out.println("<input type='hidden' name='itemID' value=" + itemID+ " />");
         out.println("<div class = 'button' align='center'><input type='submit' value='Join'></div>");
         out.println("</form></div>");


         String sqlStr2 = "SELECT * FROM Plan_Date WHERE planID = "
               + planID;
         out.println(" <section id = 'available'> <div class = 'container'> <header class='section-header'> <br/><br/> <h3 class='section-title'>ALL AVAILABLE DATES FOR THE PLAN</h3> <br/> <br/> <div class='row hotdeals-cols'> ");
         rset = stmt.executeQuery(sqlStr2);
         while(rset.next()){
            out.println("<div class='col-lg-4 col-md-6 portfolio-item filter-web wow fadeInUp' data-wow-delay='0.1s'> <div class = 'availabledate'> <div class='portfolio-wrap'> <p>");
            out.println("<br/> Duriation: "+rset.getDate("startDate")+" - "+rset.getDate("endDate"));
            out.println("<br/> price: $"+rset.getInt("price"));
            out.println("<br/> Remaining Seats: "+rset.getInt("remaining_seat")+"</p>");
            out.println("<form method='post' action='detail'>");
            out.println("<input type='hidden' name='itemID' value=" + rset.getInt("itemID") + " />");
            out.println("<div class = 'button' align='center'><input type='submit' value='Check'></div>");
            out.println("</form>");
            out.println("</div></div></div>");
         }

         out.println("</div></div></session><!--========================== Footer ============================--> <div class='footer'> <p> IM2073 Course Project: Yet Another Trip Advisor.<br/>Presented by Xu Peisen and Huang Xinwei <br/><br/></p> </div></body></html>");
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