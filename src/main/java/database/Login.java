package database;

import database.model.Database;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gerrygj
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() { 
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            
            String url = "jdbc:mysql://" + server + ":" + port + "/" + db;
            Database database = new Database(url, db_user, db_password);
            request.setAttribute("db", database);

            request.getRequestDispatcher("/assignments/loginView.jsp").forward(request, response);
        }
    
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            request.getSession().setAttribute("username", request.getParameter("username"));
            request.getSession().setAttribute("password", request.getParameter("password"));
            
            request.getSession().setAttribute("login_message", "Incorrect username or password.");
            
            response.sendRedirect("Login");
        }
}
