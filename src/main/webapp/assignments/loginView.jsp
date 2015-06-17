<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:setDataSource var="dbconfig" driver="com.mysql.jdbc.Driver"
    url="${db.url}"
    user="${db.user}"  password="${db.password}"/>

<sql:query dataSource="${dbconfig}" var="result">
    SELECT username, password from login;
</sql:query>    
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>DB Test</title>
</head>
<body>
    <h2>Results</h2>

    <c:forEach var="row" items="${result.rows}">
        Username: ${row.username}<br>
        Password: ${row.password}<br>
    </c:forEach>
</body>
</html>