<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:setDataSource var="dbconfig" driver="com.mysql.jdbc.Driver"
    url="${db.url}"
    user="${db.user}"  password="${db.password}"/>

<sql:query dataSource="${dbconfig}" var="result">
    SELECT COUNT(*) count FROM login
    WHERE username = '${username}' AND password = '${password}';
</sql:query>    
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>DB Test</title>
</head>
<body>

    <c:forEach var="row" items="${result.rows}">
        <c:choose>
            <c:when test="${row.count == 1}">
                <h2>Welcome</h2>
                Logged in as <strong><c:out value="${username}" /></strong><br><br>
                <form method="POST" action="../Logout">
                    <input type="submit" value="Logout" />
                </form>
            </c:when>
            <c:otherwise>
                <h2>Please Login</h2>
                <form method="POST" action="../Login">
                    Username: <input type="text" name="username" /><br>
                    Password: <input type="password" name="password" /><br>
                    <br>
                    <input type="submit" value="Login" />
                </form>
                <br><c:out value="${login_message}" />
            </c:otherwise>
        </c:choose>
    </c:forEach>

</body>
</html>