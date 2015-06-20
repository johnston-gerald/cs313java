package discussion;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Discussion")
public class Discussion extends HttpServlet{
    private static final long serialVersionUID = 1L;

    // JDBC driver
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static String db_url;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        try {
            String server = "localhost";
            String port = "3306";
            String db = "login";
            String db_user = "gerrygj";
            String db_password = "pa55word";
            
            if(System.getenv("OPENSHIFT_MYSQL_DB_HOST") != null){
                server = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                db_user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                db_password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }
            
            //database URL
            db_url="jdbc:mysql://" + server + ":" + port + "/" + db;
            
            // Set response content type
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String title = "Database Result";
            String docType =
                    "<!doctype html>\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n");
            
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Execute SQL query
            Connection conn = DriverManager.getConnection(db_url,db_user,db_password);
            String sql = "SELECT COUNT(*) count FROM login";
            Statement stmt = conn.prepareStatement(sql);
                
            // Extract data from result set
            ResultSet rs = stmt.executeQuery(sql);
            
            // Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int count  = rs.getInt("count");
//                       int age = rs.getInt("age");
//                       String first = rs.getString("first");
//                       String last = rs.getString("last");
                
                //Display values
                out.println("Count: " + count + "<br>");
            }
            out.println("</body></html>");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Discussion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} 