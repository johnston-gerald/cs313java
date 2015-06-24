<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Movie Search</title> 
</head> 
<body> 
  
    <form method="POST" action="../MovieSearch">
        Title: <input type="text" name="title"><br><br>
        <input type="submit" value="Search" />
    </form><br>
    
    <c:forEach var="movie" items="${movieList}">
        ${movie.title}<br>
        ${movie.year}<br>
        ${movie.imdbID}<br>
        ${movie.type}<br><br>
    </c:forEach>
    
</body> 
</html>