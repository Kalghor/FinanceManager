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
                    <form action="/app/delete" method="POST">
                        <input type="hidden" name="id" value="${r.id}" />
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tfoot>
    </table>
    <form action="/app/deleteAll" method="POST">
        <input type="hidden" name="categoryName" value="${categoryName}" />
        <input type="submit" value="Delete All">
    </form>
<%--    <a href='<c:url value="/app/deleteAll/${categoryName}"/>'>DeleteAll</a>--%>
</div>
</body>
</html>