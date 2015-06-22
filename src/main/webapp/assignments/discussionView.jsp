<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Discussion</title>
</head>
<body>
    <h1>Welcome to the discussion, <c:out value="${username}" /></h1>
    <form method="POST" action="../DiscussionLogout">
        <input type="submit" value="Logout" />
    </form>
    
    <form method="POST" action="../Comments"><br>
        Comment:<br>
        <textarea name="comment" rows="10" cols="50"></textarea><br>
        <input type="submit" name="submit" value="Comment">
    </form><br><hr><br>
    
    <c:forEach var="comment" items="${commentList}">
        ${comment.username} (${comment.date})<br><br>
        ${comment.comment}<br><br><hr><br>
    </c:forEach>
</body>
</html>