<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Please Login</h1>
        <form method="POST" action="../Discussion">
            Username: <input type="text" name="username" /><br>
            Password: <input type="password" name="password" /><br>
            <br>
            <input type="submit" value="Login" />
        </form>
        <br><c:out value="${login_message}" />
    </body>
</html>
