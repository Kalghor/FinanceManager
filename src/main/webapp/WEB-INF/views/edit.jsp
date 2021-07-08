<%@include file="header.jsp" %>
<div>
    <form:form method="post" modelAttribute="categoryDto" action="/app/edit">
        <form:hidden path="id" />
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
<%@include file="footer.jsp" %>