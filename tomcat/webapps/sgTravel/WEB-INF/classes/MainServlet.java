import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      try {
         out.println("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><title>Yet Another Travel Advisor!</title><meta content='width=device-width, initial-scale=1.0' name='viewport'> <meta content='' name='keywords'> <meta content='' name='description'><!-- For-Mobile-Apps --><meta name='viewport' content='width=device-width, initial-scale=1' /><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><meta name='keywords' content='Travel Packages Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design' /><script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script><!-- //For-Mobile-Apps --><!-- Style --><link rel='stylesheet' href='css/style.css' type='text/css' media='all' /><link href='css/font-awesome.css' rel='stylesheet'><!-- Default-JavaScript-File --> <script type='text/javascript' src='js/jquery.min.js'></script> <!-- Bootstrap CSS File --> <link href='lib/bootstrap/css/bootstrap.min.css' rel='stylesheet'> <!-- Libraries CSS Files --> <link href='lib/font-awesome/css/font-awesome.min.css' rel='stylesheet'> <link href='lib/animate/animate.min.css' rel='stylesheet'> <link href='lib/ionicons/css/ionicons.min.css' rel='stylesheet'> <link href='lib/owlcarousel/assets/owl.carousel.min.css' rel='stylesheet'> <link href='lib/lightbox/css/lightbox.min.css' rel='stylesheet'><!-- Web-Fonts --><link href='//fonts.googleapis.com/css?family=Raleway:400,500,600,700,800' rel='stylesheet' type='text/css'><link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'> <link href='https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700' rel='stylesheet'><!-- //Web-Fonts --></head><body> <!--========================== Header ============================--> <header id='header'> <div class='container-fluid'> <div id='logo' class='pull-left'> <h1><a href='index' class='scrollto'>Yet Another Trip Advisor</a></h1> <!-- Uncomment below if you prefer to use an image logo --> <!-- <a href='#intro'><img src='img/logo.png' alt='' title='' /></a>--> </div> <nav id='nav-menu-container'> <ul class='nav-menu'> <li class='menu-active'><a href='#search'>Search</a></li> <li class='menu-has-children'><a href='service.html'>Service</a> <ul> <li><a href='service.html'>Travel</a></li> <li><a href='#hotel'>Hotels</a></li> <li><a href='#flight'>Flights</a></li> </ul> </li> <li><a href='hotdeals.html'>hotdeals Us</a></li> <li><a href='#contact'>Contact</a></li> </ul> </nav><!-- #nav-menu-container --> </div> </header><!-- #header --><!--================= Empty Section =================--><section><div class='top'></div></section><!--========================== Login in/ Register ============================--><section class='loginregister'><div class='container'><div class='login'>");
            String userName;

         HttpSession session = request.getSession(false);
         if (session == null) {
            out.println("<ul><li><a href='register.html'> Create Account </a></li><li><a href='login.html'>Login</a></li></ul>");
         } else {
            synchronized (session) {
               userName = (String) session.getAttribute("username");
               out.println("<ul><li><a href='index'>Hello! " + userName + "</a></li><li><a href='logout'>Logout</a></li></ul>");
            }
          }


         out.println("</div></div></section><!--========================== Search Section ============================--><section id = 'search' class='section-bg wow fadeInUp'><div class='container'><div class='section-header'> <h3>Explore the world</h3></div><div class='tab'><div id='horizontalTab' style='display: block; width: 100%; margin: 0px;'><script src='js/easyResponsiveTabs.js' type='text/javascript'></script><script type='text/javascript'>$(document).ready(function () {$('#horizontalTab').easyResponsiveTabs({type: 'default',width: 'auto',fit: true,closed: 'accordion',activate: function(event) {var $tab = $(this);var $info = $('#tabInfo');var $name = $('span', $info);$name.text($tab.text());$info.show();}});$('#verticalTab').easyResponsiveTabs({type: 'vertical',width: 'auto',fit: true});});</script><div class='tabs'><div class='tab-left'><ul class='resp-tabs-list'><li class='resp-tab-item'><i class='fa fa-suitcase' aria-hidden='true'></i>Vacation</li><li class='resp-tab-item'><i class='fa fa-university' aria-hidden='true'></i>Hotels</li><li class='resp-tab-item'><i class='fa fa-plane' aria-hidden='true'></i>Flights</li><!--<li class='resp-tab-item'><i class='fa fa-car' aria-hidden='true'></i>Cars</li><li class='resp-tab-item'><i class='fa fa-ship' aria-hidden='true'></i>Cruise</li> --></ul></div><div class='tab-right'><div class='resp-tabs-container'> <!---Travel Section --><div class='tab-1 resp-tab-content'><div class='w3l-sign-in'><form method='get' class='agile_form' action='searchmvp'><div class='list_agileits_w3layouts'><div class='section_class_agileits sec-left'> <select type='text' name= 'startCity'> <option value ='0' >Departure City: All</option> <option value='Singapore'>Singapore</option> <option value='Beijing'>Beijing</option> <option value='Shanghai'>Shanghai</option> <option value='New York'>NewYork</option> <option value='London'>Londom</option> <option value='Sydney'>Sydney</option> <option value='Paris'>Paris</option> <option value='Tokyo'>Tokyo</option> </select></div><div class='section_class_agileits sec-right'> <select type='text' name= 'city'> <option value = '0'>Destination City: All</option> <option value = 'Beijing'>Beijing</option> <option value = 'Shanghai'>Shanghai</option> <option value = 'London'>London</option> <option value = 'New York'>New York</option> </select></div><div class='clear'></div></div><input placeholder='Start date' class='date' id='datepicker1' type='text' name='startDate' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><input placeholder='End date' class='date' id='datepicker2' type='text' name='endDate' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><div class='list_agileits_w3layouts'><div class='section_class_agileits sec-left'> <!--select><option value='0'>Number of traveller</option><option value='1'>0</option><option value='2'>1</option><option value='3'>2</option><option value='4'>3 or 3+</option> </select--> <p> Number of travellers &nbsp &nbsp<input type = 'number' min = '0' name = 'numTravellers' /> </p></div><div class='clear'></div></div><div class='submit'> <input type='submit' value='search'></div></form></div></div><!---Hotel Section --><div class='tab-1 resp-tab-content'><div class='register agileits'><form action='#' method='post' class='agile_form' action='searchmvp'><div class='list_agileits_w3layouts'><div class='section_class_agileits sec-left'> <select><option value = '0'>Destination City</option> <option value = 'Shanghai'>Shanghai</option> <option value = 'London'>London</option> <option value = 'New York'>New York</option> </select></div><div class='clear'></div></div><input placeholder='Check in date' class='date' id='datepicker3' type='text' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><input placeholder='Check out date' class='date' id='datepicker4' type='text' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><div class='section_class_agileits sec-right'> <select><option value='0'>Rooms</option><option value='1'>Single Room</option><option value='3'>Double Room</option><option value='2'>Suit Room</option> </select></div><div class='list_agileits_w3layouts'><div class='section_class_agileits sec-left'> <!--select> <option value='0'>Number of traveller</option> <option value='1'>0</option> <option value='2'>1</option> <option value='3'>2</option> <option value='4'>3 or 3+</option> </select--> <p> Number of travellers &nbsp &nbsp<input type = 'number' min = '0' name = 'numTravellers' /></div><div class='clear'></div></div><div class='submit'> <input type='submit' value='search'></div></form></div></div> <!---Flights Section --><div class='tab-1 resp-tab-content gallery-images'><div class='wthree-subscribe'><form action='#' method='post' class='agile_form' action='searchmvp'><div class='list_agileits_w3layouts'><div class='section_class_agileits sec-left'> <select> <option value = '0'>Departure City</option> <option value='Singapore'>Singapore</option> <option value='Beijing'>Beijing</option> <option value='Shanghai'>Shanghai</option> <option value='New York'>NewYork</option> <option value='London'>Londom</option> <option value='Sydney'>Sydney</option> <option value='Paris'>Paris</option> <option value='Tokyo'>Tokyo</option> </select></div><div class='section_class_agileits sec-right'> <select> <option value = '0'>Destination City</option> <option value = 'Shanghai'>Shanghai</option> <option value = 'London'>London</option> <option value = 'New York'>New York</option> </select></div><div class='clear'></div></div><input placeholder='Departure date' class='date' id='datepicker5' type='text' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><input placeholder='Return date' class='date' id='datepicker6' type='text' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><div class='list_agileits_w3layouts'><div class='section_class_agileits sec-left'> <select><option value='0'>Guests</option><option value='1'>0</option><option value='2'>1</option><option value='3'>2</option><option value='4'>3 or 3+</option> </select></div></div><div class='submit'> <input type='submit' value='search'></div></form></div></div><div class='tab-1 resp-tab-content'><div class='agileinfo-recover'><form action='#' method='post' class='agile_form' action='searchmvp'><input type='text' placeholder='Your Name' name='name' class='name agileits' required=''/><input type='text' placeholder='Picking up' name='name' class='name agileits' required=''/><input type='text' placeholder='Dropping off' name='name' class='name agileits' required=''/><input placeholder='Pick-up date' class='date' id='datepicker5' type='text' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><input placeholder='Drop-off date' class='date' id='datepicker6' type='text' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><div class='list_agileits_w3layouts'><div class='section_class_agileits sec-left'> <select><option value='0'>Guests</option><option value='1'>0</option><option value='2'>1</option><option value='3'> 2</option><option value='4'>3 or 3+</option> </select></div></div><div class='submit'> <input type='submit' value='search'></div></form></div></div><div class='tab-1 resp-tab-content'><div class='agile-send-mail'><form action='#' method='post' class='agile_form' action='searchmvp'><input type='text' placeholder='Your Name' name='name' class='name agileits' required=''/><input type='text' placeholder='Going to' name='name' class='name agileits' required=''/><input placeholder='Select date' class='date' id='datepicker7' type='text' value='' onfocus='this.value = '';' onblur='if (this.value == '') {this.value = '';}' required=''/><div class='list_agileits_w3layouts'><div class='section_class_agileits sec-left'> <select><option value='0'>Guests</option><option value='1'>0</option><option value='2'>1</option><option value='3'> 2</option><option value='4'>3 or 3+</option> </select></div></div><div class='submit'> <input type='submit' value='search'></div></form></div></div></div></div><div class='clear'></div></div></div></div></div></section><!--========================== Hot Deals ============================--><section id='hotdeals'> <div class='container'> <header class='section-header'> <h3>Hot Deals</h3> <p></p> </header> <div class='row hotdeals-cols'> <div class='col-md-4 wow fadeInUp'> <div class='hotdeals-col'> <div class='img'> <img src='images/travel/thai.jpg' alt='' class='img-fluid'> </div> <h2 class='title'><a href='#'>5 Days Thailand Tour</a></h2> <p> Visit Cities: Bangkok, Chiang Mai, Pattaya <br/> Departure Cities: London <br/> Cities: Singapore <br/> Duration: 5 days <br/> Price: $850 <br/> <p> </div> </div> <div class='col-md-4 wow fadeInUp' data-wow-delay='0.1s'> <div class='hotdeals-col'> <div class='img'> <img src='images/travel/shanghai.jpg' alt='' class='img-fluid'> </div> <h2 class='title'><a href='#'>4 Days China Tour</a></h2> <p> Visit Cities: Beijing <br/> Departure Cities: Singapore <br/> Duration: 4 days <br/> Price: #900 <br/> </p> </div> </div> <div class='col-md-4 wow fadeInUp' data-wow-delay='0.2s'> <div class='hotdeals-col'> <div class='img'> <img src='images/travel/usa.jpg' alt='' class='img-fluid'> </div> <h2 class='title'><a href='#'>15 Days United States Tour</a></h2> <p> Visit Cities: Los Angeles, San Francisco, Las Vegas, Yellowstone Park <br/> Departure Cities: Shanghai<br/> Duration: 15 days <br/> Price: $2000 <br/> </p> </div> </div> </div> </div> </section><!-- #hotdeals --><!--========================== Footer ============================--><div class='footer'><p> IM2073 Course Project: Yet Another Trip Advisor.<br/>Presented by Xu Peisen and Huang Xinwei <br/><br/></p></div><!--start-date-piker--><link rel='stylesheet' href='css/jquery-ui.css' /><script src='js/jquery-ui.js'></script><script>$(function() {$( '#datepicker,#datepicker1,#datepicker2,#datepicker3,#datepicker4,#datepicker5,#datepicker6,#datepicker7' ).datepicker();});</script><!-- 97-rgba(0, 0, 0, 0.75)/End-date-piker --></body></html>");
      } finally {
         out.close();
      }
   }
}