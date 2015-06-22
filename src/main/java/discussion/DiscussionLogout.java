package discussion;

import static discussion.Discussion.session;
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
@WebServlet(name = "DiscussionLogout", urlPatterns = {"/DiscussionLogout"})
public class DiscussionLogout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session.removeAttribute("username");
        
        //invalidate the session if exists
//        session = request.getSession(false);
//        if(session != null){
//            session.invalidate();
//        }
        
        response.sendRedirect("Discussion");
    }
}