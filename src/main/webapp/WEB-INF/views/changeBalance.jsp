<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    Account balance: ${accountBalance} </br>
    <form:form method="post" modelAttribute="account">
        <label>Amount to be added:
            <form:input path="amountToAdd"/>
        </label><form:errors path="amountToAdd"/><br/>
        <label>Set the amount on the account
            <form:input path="balance" />
        </label><form:errors path="balance"/><br/>
        <form:button type="submit">Next</form:button>
    </form:form>
</div>
</body>
</html>
