package facebook;

import databaseConfig.DatabaseConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facebook4j.Facebook; 
import facebook4j.FacebookException;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CallBack", urlPatterns = {"/CallBack"})
public class CallBack extends HttpServlet {
    
    static HttpSession session;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");
        String oauthCode = request.getParameter("code");
        String login_user = "";
        String login_message = "";

        try {
            facebook.getOAuthAccessToken(oauthCode);
            
            //https://developers.facebook.com/docs/php/GraphObject/4.0.0#user-instance-methods
            login_user = facebook.getId();
//            login_user = "fudged bad login";    //for testing purposes
            
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
                session.setAttribute("user", login_user);
                session.setAttribute("isValid", "fvalid");
                session.setAttribute("badLogin", "");
                
                //reset variables
                login_user = "";
            } else {
                session.setAttribute("isValid", "invalid");
                session.setAttribute("badLogin", "Your account has not been authorized. Please sign in with an authorized account.");
            }
            
            request.getRequestDispatcher("/loginApp/index.jsp").forward(request, response);
            
        } catch (SQLException | FacebookException | IllegalStateException ex) {
            Logger.getLogger(CallBack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
