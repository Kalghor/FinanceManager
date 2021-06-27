<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<form:form method="post" modelAttribute="user" action="/register">
    <div>
        <label>Username:
            <form:input path="username" />
        </label>
    </div>
    <div>
        <label>Password:
            <form:password path="password" />
        </label>
    </div>
    <div>
        <label>First name:
            <form:input path="firstName" />
        </label>
    </div>
    <div>
        <label>Last name:
            <form:input path="lastName" />
        </label>
    </div>
    <button type="submit">Register</button>
</form:form>
</body>
</html>