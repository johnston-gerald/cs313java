package facebookDemo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facebook4j.Facebook; 
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

/**
 *
 * @author gerrygj
 */
@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("913876445325753", "22b295234e71828d3ed5051720e78b9f");
        String accessTokenString = "947c349b9e93918f532bf2c12ecc5cc1";
        AccessToken at = new AccessToken(accessTokenString);
        // Set access token.
        facebook.setOAuthAccessToken(at);
        
        facebook.setOAuthPermissions("email,user_friends");
        
        request.getSession().setAttribute("facebook", facebook);

        StringBuffer requestUrl = request.getRequestURL();
        int lastSlashIndex = requestUrl.lastIndexOf("/");

        String callBackUrl = requestUrl.substring(0, lastSlashIndex) + "/CallBack";

        String facebookUrl = facebook.getOAuthAuthorizationURL(callBackUrl);

        response.sendRedirect(facebookUrl); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
