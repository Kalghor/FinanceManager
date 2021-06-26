<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div style="margin: 10px">
        Account balance: ${accountBalance} </br>
        <a href="http://localhost:8080/app/changeBalance">
            <button>change account balance</button>
        </a>
    </div>
    <c:forEach var="category" items="${categories}">
        <div style="border-bottom-color: black; border-style: solid; padding: 20px; display: inline-block; margin: 10px">
                ${category.categoryName} </br>
                ${category.actualValue} </br>
            <a href="http://localhost:8080/app/showDetails/${category.categoryName}">
                <button>Show details</button>
            </a>
        </div>
    </c:forEach>
    <div>
        <a href="http://localhost:8080/app/addExpense">
            <button>Add expense</button>
        </a><br><br>
        <a href="http://localhost:8080/logout">
            <button>Logout</button>
        </a>
    </div>
</div>
</body>
</html>
