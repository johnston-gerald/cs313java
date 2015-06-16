<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scripture List</title> 
</head> 
<body>

    <div> 
        Scriptures:<br><br>
<!--
        <c:forEach var="scripture" items="${scriptures}"> 
            ${scripture}<br> 
        </c:forEach>
-->

        <c:forEach var="scripture" items="${scriptures}">
            <strong>${scripture.book}</strong> ${scripture.chapter}:${scripture.verse}<br>
        </c:forEach>
    </div><br>

    <a href='assignments/newScripture.html'>Add New Scripture</a><br><br>
</body> 
</html>