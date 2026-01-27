<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="name">Name:
        <input type="text" name="name" id="name">
    </label><br>
    <label for="birthday">Birthday:
        <input type="date" name="birthday" id="birthday">
    </label><br>
    <label for="password">Password:
        <input type="password" name="password" id="password">
    </label><br>
    <label for="email">Password:
        <input type="text" name="email" id="email" value="example@email.com">
    </label><br>
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option label="${role}">${role}</option>
            <br>
        </c:forEach>
    </select><br>
    <c:forEach var="gender" items="${requestScope.genders}">
        <input type="radio" name="gender" VALUE="${gender}">${gender}
        <br>
    </c:forEach>
    <br>
    <input type="submit" value="Send">
</form>

<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
            <br>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
