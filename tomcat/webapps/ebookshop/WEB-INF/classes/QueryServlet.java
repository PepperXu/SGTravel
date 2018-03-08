import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class QueryServlet extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			Connection conn = null;
			Statement stmt = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebookshop", "myuser", "0720");

				stmt = conn.createStatement();

				String[] authors = request.getParameterValues("author");
				if (authors == null) {
   					out.println("<h2>Please go back and select an author</h2>");
   					return; // Exit doGet()
				}

				String sqlStr = "SELECT * FROM books WHERE author IN (";
				sqlStr += "'" + authors[0] + "'";
				for (int i = 1; i < authors.length; ++i) {
					sqlStr += ", '" + authors[i] + "'";
				}
				sqlStr += ") AND qty > 0 ORDER BY author ASC, title ASC";

				out.println("<html><head><title>Query Results</title></head><body>");
				out.println("<h2>Thank you for your query.</h2>");
				out.println("<p>You query is: " + sqlStr + "</p>");
				ResultSet rset = stmt.executeQuery(sqlStr);

				int count = 0;
				while(rset.next()){
					out.println("<p>" + rset.getString("author")
						+ ", " + rset.getString("title")
						+ ", $" + rset.getDouble("price") + "</p>");
					++count;
				}
				out.println("<p>==== " + count + " records found ====</p>");
				out.println("</body></html>");

			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} finally{
				out.close();
				try{
					if(stmt!=null) stmt.close();
					if(conn!=null) conn.close();

				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
}