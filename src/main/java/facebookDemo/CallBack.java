package facebookDemo;

import databaseConfig.DatabaseConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facebook4j.Facebook; 
import facebook4j.FacebookException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerrygj
 */
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
            PrintWriter out = response.getWriter();
            
            facebook.getOAuthAccessToken(oauthCode);
            
            //https://developers.facebook.com/docs/php/GraphObject/4.0.0#user-instance-methods
            login_user = facebook.getId();
//            login_user = "fudged bad login";    //for testing purposes
            int count = 0;
            
            out.write(login_user + "\n\n");
            
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
            
            if(count == 1){
                session = request.getSession();
                session.setAttribute("username", login_user);
                
                //reset variables
                login_user = "";
                login_message = "";
            }
            
            if(session != null){    //successful login
                if(session.getAttribute("username") != null){
                    request.getRequestDispatcher("/loginApp/welcome.jsp").forward(request, response);
                }
                else {  //unsuccessful login
                    request.getRequestDispatcher("/loginApp/badLogin.jsp").forward(request, response);
                }
            } else {    //no session yet
                request.getRequestDispatcher("/loginApp/badLogin.jsp").forward(request, response);
            }
        } catch (SQLException | FacebookException | IllegalStateException ex) {
            Logger.getLogger(CallBack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
