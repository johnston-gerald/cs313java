package google;

import databaseConfig.DatabaseConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerrygj
 */
@WebServlet(name = "GoogleLogin", urlPatterns = {"/GoogleLogin"})
public class GoogleLogin extends HttpServlet {

    static HttpSession session;
//    private static String login_user = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login_user = "";
        
        if (request.getParameter("logout") == "logout") {
            session.setAttribute("isValid", "invalid");
        }
        
        login_user = request.getParameter("id");
        
        try {
//            String login_message = "";
            int count = 0;
            
            DatabaseConfig login_db = new DatabaseConfig("authenticate");
            Connection conn = login_db.Config();
        
            // Execute SQL query
            String sql = "SELECT COUNT(*) count FROM user "
                    + "WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, login_user);

            // Extract data from result set
            ResultSet rs = pstmt.executeQuery();

            // Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                count  = rs.getInt("count");
            }
            conn.close();   //close connection
            
            session = request.getSession();
            if(count == 1){
//                session = request.getSession();
                session.setAttribute("user", login_user);
                session.setAttribute("isValid", "valid");
                
                //reset variables
                login_user = "";
            } else {
                session.setAttribute("isValid", "invalid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoogleLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
//        response.sendRedirect("/loginApp/index.jsp");
//        request.getRequestDispatcher("/loginApp/index.jsp").forward(request, response);
    }
}
