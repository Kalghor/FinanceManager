<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 22.06.2021
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<form method="post" action="/register">
    <div>
        <label>Username:
            <input type="text" name="username"/>
        </label>
    </div>
    <div>
        <label>Password:
            <input type="password" name="password"/>
        </label>
    </div>
    <div>
        <label>First name:
            <input type="text" name="firstName"/>
        </label>
    </div>
    <div>
        <label>Last name:
            <input type="text" name="lastName"/>
        </label>
    </div>
    <button type="submit">Register</button>
</form>
</body>
</html>