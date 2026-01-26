<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Tickets</title>
</head>
<body>
<h1>Купленныу билеты:</h1>
<ul>
    <c:forEach var="ticket" items="${requestScope.tickets}">
        <li>${ticket.seatNumber()}</li>
    </c:forEach>
</ul>
</body>
</html>