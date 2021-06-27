<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<form:form method="post" modelAttribute="user" action="/login">
    Username: <form:input path="username"/><br/>
    Password: <form:password path="password"/><br/>
    <button type="submit">Login</button>
</form:form>
</body>
</html>
