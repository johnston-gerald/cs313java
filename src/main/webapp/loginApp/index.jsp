<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="816370568980-s1sd4vb29206bsnp7hhb51ehgdl7lj7f.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <title>Login</title>
    </head>
    <body>
        <h1>Google Login</h1>
        <c:if test="${sessionScope.isValid != 'valid'}">
            
            <p>Please sign in with Google or Facebook:</p>
            <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
            <script>
                function onSignIn(googleUser) {
                    // Useful data for your client-side scripts:
                    var profile = googleUser.getBasicProfile();
                    var id = profile.getId();
                    console.log("ID: " + id); // Don't send this directly to your server!
                    console.log("Name: " + profile.getName());
                    console.log("Image URL: " + profile.getImageUrl());
                    console.log("Email: " + profile.getEmail());

                    // The ID token you need to pass to your backend:
                    var id_token = googleUser.getAuthResponse().id_token;
                    console.log("ID Token: " + id_token);

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', '../GoogleLogin');
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.onload = function() {
                        console.log('Signed in as: ' + xhr.responseText);
                    };
                    xhr.send('id=' + id);
                };
            </script><br>

            <a href="/SignIn"><img src="../loginApp/images/facebook.png" alt="Sign in with Facebook" style="width:120px;height:36px;border:0;"></a>
        
        </c:if>
        
        <c:if test="${sessionScope.isValid == 'valid'}">
        
            <p>Welcome. You are logged in as <c:out value="${user}" /></p><br>
            
            <a href="#" onclick="signOut();">Sign out of Google</a>
            <script>
                function signOut() {
                    var auth2 = gapi.auth2.getAuthInstance();
                    auth2.signOut().then(function () {
                        console.log('User signed out.');

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', '../GoogleLogin');
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.send('logout=logout');
                    });
                }
            </script>
        
        </c:if>
    </body>
</html>