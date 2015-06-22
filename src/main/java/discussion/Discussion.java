package discussion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet("/Discussion")
public class Discussion extends HttpServlet{
    private static final long serialVersionUID = 1L;

    static HttpSession session;
    
    String login_user = "";
    String login_password = "";
    String login_message = "";
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        try {
            int count = 0;
            
            DatabaseConfig login_db = new DatabaseConfig("login");
            Connection conn = login_db.Config();
        
            // Execute SQL query
            String sql = "SELECT COUNT(*) count FROM login "
                    + "WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, login_user);
            pstmt.setString(2, login_password);

            // Extract data from result set
            ResultSet rs = pstmt.executeQuery();

            // Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                count  = rs.getInt("count");
//                       int age = rs.getInt("age");
//                       String first = rs.getString("first");
//                       String last = rs.getString("last");
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
                    response.sendRedirect("Comments");
//                    request.getRequestDispatcher("/assignments/discussionView.jsp").forward(request, response);
                }
                else {  //unsuccessful login
                    request.setAttribute("login_message", login_message);
                    request.getRequestDispatcher("/assignments/discussionLogin.jsp").forward(request, response);
                }
            } else {    //no session yet
                request.setAttribute("login_message", login_message);
                request.getRequestDispatcher("/assignments/discussionLogin.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Discussion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        login_user = request.getParameter("username");
        login_password = request.getParameter("password");
        
        login_message = "Incorrect username or password.";
        
        response.sendRedirect("Discussion");
    }
} 