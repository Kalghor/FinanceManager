<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <table>
        <thead style="border-style: solid">
        <tr>
            <th>Description</th>
            <th>Value</th>
            <th>Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <tfoot>
        <c:forEach var="r" items="${rows}">
            <tr>
                <td>${r.description}</td>
                <td>${r.actualValue}</td>
                <td>${r.localDate}</td>
                <td>
                    <a href='<c:url value="/app/edit/${r.id}"/>'>Edit</a>
                    <a href='<c:url value="/app/delete/${r.id}"/>'>Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tfoot>
    </table>
    <a href='<c:url value="/app/deleteAll/${categoryName}"/>'>DeleteAll</a>

</div>
</body>
</html>