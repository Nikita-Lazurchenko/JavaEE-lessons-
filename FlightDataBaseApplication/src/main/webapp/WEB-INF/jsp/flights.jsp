<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Полеты</title>
</head>
<body>
<h1>Список полетов:</h1>
<c:if test="${not empty requestScope.flightd}">
<c:forEach var="flight" items="${requestScope.flights}">
    <li><a href="/tickets?id=${flight.id()}">${flight.description()}</a></li>
</c:forEach>
</body>
</c:if>
</html>
