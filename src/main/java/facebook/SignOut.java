package facebook;

import static facebook.CallBack.session;
import facebook4j.Facebook;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SignOut", urlPatterns = {"/SignOut"})
public class SignOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session.setAttribute("isValid", "invalid");
        session.setAttribute("badLogin", "");
        
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String accessToken = "";
        try {
            accessToken = facebook.getOAuthAccessToken().getToken();
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
        //Sign out of Facebook
        StringBuffer next = request.getRequestURL();
        int index = next.lastIndexOf("/");
        next.replace(index+1, next.length(), "");
        response.sendRedirect("http://www.facebook.com/logout.php?next=" + next.toString() + "/loginApp" + "&access_token=" + accessToken);
        
//        PrintWriter out = response.getWriter();
//        out.println("http://www.facebook.com/logout.php?next=" + next.toString() + "&access_token=" + accessToken);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
