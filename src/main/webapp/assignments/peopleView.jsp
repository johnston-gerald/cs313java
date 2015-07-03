<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Family History</title>
    </head>
    <body>
        <h1>People List</h1>
        
        <c:forEach var="person" items="${personList}">
            <a href="/PersonDetails?person_id=${person.person_id}">${person.lastname}, ${person.firstname}</a>. Born: ${person.birthday}<br><br>
        </c:forEach>
    </body>
</html>
