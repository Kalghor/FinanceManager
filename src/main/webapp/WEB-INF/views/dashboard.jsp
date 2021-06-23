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

<c:forEach var="category" items="${categories}">
    <div>
            ${category.categoryName} </br>
            ${category.actualValue}
    </div>
</c:forEach>

</body>
</html>
