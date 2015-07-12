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

@WebServlet(name = "GoogleLogin", urlPatterns = {"/GoogleLogin"})
public class GoogleLogin extends HttpServlet {

    static HttpSession session;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login_user = "";
        
        login_user = request.getParameter("id");
//        login_user = "fudged bad login";    //for testing purposes
        
        try {
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
            
            count = 1;  //fudged good login for testing purposes
            session = request.getSession();
            if(count == 1){
                session.setAttribute("user", login_user);
                session.setAttribute("isValid", "gvalid");
                session.setAttribute("badLogin", "");
                
                //reset variables
                login_user = "";
            } else {
                session.setAttribute("isValid", "invalid");
                session.setAttribute("badLogin", "Your account has not been authorized. Please sign in with an authorized account.");
            }
            
            if ("logout".equals(request.getParameter("logout"))) {
                session.setAttribute("isValid", "invalid");
                session.setAttribute("badLogin", "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoogleLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
