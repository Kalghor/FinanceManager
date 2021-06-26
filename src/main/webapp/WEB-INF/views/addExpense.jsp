<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form:form method="post" modelAttribute="categoryDto">
        <label>Category name:
            <form:input path="categoryName"/>
        </label><form:errors path="categoryName"/><br/>
        <label>Description:
            <form:input path="description" />
        </label><form:errors path="description"/><br/>
        <label>Value:
            <form:input path="actualValue" />
        </label><form:errors path="actualValue"/><br/>
        <form:button type="submit">Next</form:button>
    </form:form>
</div>
</body>
</html>