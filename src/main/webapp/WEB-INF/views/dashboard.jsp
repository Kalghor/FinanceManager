<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 22.06.2021
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>
        Account balance: ${accountBalance}
    </div>
    <c:forEach var="category" items="${categories}">
        <div style="border-bottom-color: black; border-style: solid; padding: 20px; display: inline-block">
                ${category.categoryName} </br>
                ${category.actualValue}
        </div>
    </c:forEach>
</div>


</body>
</html>
