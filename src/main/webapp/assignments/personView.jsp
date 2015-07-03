<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Details</title>
    </head>
    <body>
        <h1>Person Details</h1>
        
        <c:out value="${lastname}" />, <c:out value="${firstname}" />. Born: <c:out value="${birthday}" /><br><br>

        <a href="/Family">Back to People List</a><br><br><hr>
        
        <h2>Parents of ${firstname} ${lastname}</h2>
        <c:choose>
            <c:when test="${!empty parentList}">
                <c:forEach var="person" items="${parentList}">
                    ${person.lastname}, ${person.firstname}. Born: ${person.birthday}<br><br>
                </c:forEach>
            </c:when><c:otherwise>
                None listed.<br><br>
            </c:otherwise>
        </c:choose><hr>
            
        <h2>Children of ${firstname} ${lastname}</h2>
        <c:choose>
            <c:when test="${!empty childrenList}">
                <c:forEach var="person" items="${childrenList}">
                    ${person.lastname}, ${person.firstname}. Born: ${person.birthday}<br><br>
                </c:forEach>
            </c:when><c:otherwise>
                None listed.<br><br>
            </c:otherwise>
        </c:choose><hr>
        
        <h2>All ancestors of ${firstname} ${lastname}</h2>
        <c:choose>
            <c:when test="${!empty ancestorList}">
                <c:forEach var="person" items="${ancestorList}">
                    ${person.lastname}, ${person.firstname}. Born: ${person.birthday}<br><br>
                </c:forEach>
            </c:when><c:otherwise>
                None listed.<br><br>
            </c:otherwise>
        </c:choose><hr>
        
    </body>
</html>
