
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      try {
         out.println("<html><head><title>Logout</title></head><body>");
         out.println("<h2>Logout</h2>");
         HttpSession session = request.getSession(false);
         if (session == null) {
            out.println("<h3>You have not login!</h3>");
         } else {
            session.invalidate();
            out.println("<p>Bye!</p>");
            out.println("<p><a href='index'>Back to Main</a></p>");
         }
         out.println("</body></html>");
      } finally {
         out.close();
      }
   }
}