package discussion;

import static discussion.Discussion.session;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerrygj
 */
@WebServlet(name = "Comments", urlPatterns = {"/Comments"})
public class Comments extends HttpServlet {
    
    DatabaseConfig login_db = new DatabaseConfig("login");
    Connection conn = login_db.Config();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
        
        try {
//            int count = 0;
//            String comment = "";
//            String date = "";
//            String username = "";
            
            DatabaseConfig login_db = new DatabaseConfig("login");
            Connection conn = login_db.Config();
        
            // Execute SQL query
            String sql = "SELECT comment, comment_date, comment_username FROM comment "
                    + "ORDER BY comment_date DESC";
            PreparedStatement select = conn.prepareStatement(sql);

            // Extract data from result set
            ResultSet rs = select.executeQuery();

            // Extract data from result set
            while(rs.next()){
                CommentModel comment = new CommentModel();
                //Retrieve by column name
                comment.setComment(rs.getString("comment"));
                comment.setDate(rs.getTimestamp("comment_date").toString());
                comment.setUsername(rs.getString("comment_username"));
                
                commentList.add(comment);
//                comment = rs.getString("comment");
//                date = rs.getTimestamp("comment_date").toString();
//                username = rs.getString("comment_username");
//                count  = rs.getInt("count");
//                       int age = rs.getInt("age");
//                       String first = rs.getString("first");
//                       String last = rs.getString("last");
            }
            
            conn.close();   //close connection
            
        } catch (SQLException ex) {
            Logger.getLogger(Discussion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        if(session != null){    //successful login
            if(session.getAttribute("username") != null){
                request.setAttribute("commentList", commentList);
                request.getRequestDispatcher("/assignments/discussionView.jsp").forward(request, response);
            }
            else {  //unsuccessful login
                response.sendRedirect("Discussion");
            }
        } else {    //no session yet
            response.sendRedirect("Discussion");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String comment = request.getParameter("comment");
            String username = session.getAttribute("username").toString();
            
            String sql = "INSERT INTO comment (comment, comment_date, comment_username) "
                       + "VALUES (?, NOW(), ?)";
            
            PreparedStatement insert = conn.prepareStatement(sql);
            insert.setString(1, comment);
            insert.setString(2, username);
            
            insert.execute();
//            conn.close();   //close connection
        } catch (SQLException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("Comments");
    }
}
