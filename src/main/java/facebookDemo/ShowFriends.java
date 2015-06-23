package facebookDemo;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.ResponseList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gerrygj
 */
@WebServlet(name = "ShowFriends", urlPatterns = {"/ShowFriends"})
public class ShowFriends extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Facebook facebook = (Facebook)request.getSession().getAttribute("facebook");

        PrintWriter out = response.getWriter();

        try { 
            out.write("Your name is: " + facebook.getName() + "\n\n"); 
            ResponseList<Friend> list = facebook.getFriends(); 
            for (Friend friend : list) { 
               out.write(friend.getName() + "\n"); 
            }

        } catch (IllegalStateException | FacebookException e) { 
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
