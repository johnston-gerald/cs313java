package google;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.annotation.WebServlet;

//@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String callbackURL = null;
    	String permissions = null;
        String permissionString = "";
        String permissionArray[];
        
        try {
            StringBuffer callbackURLbuffer = request.getRequestURL();
            int index = callbackURLbuffer.lastIndexOf("/");
            callbackURLbuffer.replace(index, callbackURLbuffer.length(), "").append("/oauth2callback");   //default: .append("/callback")
            callbackURL = URLEncoder.encode(callbackURLbuffer.toString(), "UTF-8");
            
            String googlePlusClientId = getServletContext().getInitParameter("googlePlusClientId");
            
            permissions = getServletContext().getInitParameter("additionalPermissions");
            permissionArray = permissions.split(",");
            for(String p: permissionArray) {
            	permissionString += URLEncoder.encode(p,"UTF-8") + "+";
            }
            
            System.out.println(permissions);
            
            String authURL = "https://accounts.google.com/o/oauth2/auth?scope=" + permissionString + "&state=profile" + 
            		"&redirect_uri=" + callbackURL + "&response_type=code" + "&client_id=" + googlePlusClientId;
            System.out.println("authURL: " + authURL);
            
            response.sendRedirect(authURL);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
