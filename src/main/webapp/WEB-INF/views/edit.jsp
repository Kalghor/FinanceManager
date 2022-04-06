<%@include file="header.jsp" %>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid justify-content-center">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link active" style="color: #eae9eb" href="${pageContext.request.contextPath}/app/dashboard">Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" style="color: #eae9eb" href="${pageContext.request.contextPath}/app/scheduledExpenses">Scheduled expenses</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" style="color: #eae9eb" href="${pageContext.request.contextPath}/app/demoScheduledExpense">Demo Scheduled expenses</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" style="color: #eae9eb" href="${pageContext.request.contextPath}/removeDemo">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="p-5 shadow-lg p-3 bg-body rounded" style="width: 50%; margin-top: 120px; margin-right: auto; margin-left: auto">
    <div class="pb-4 text-center d-flex justify-content-center">
        <form:form method="post" modelAttribute="categoryDto" action="/app/edit">
            <form:hidden path="id" />
            <div class="p-2">
                <label>Category name:
                    <form:input type="text" class="form-control" id="formGroupExampleInput" pattern="[a-zA-Z ]{3,20}" title="The name can only contain letters and min length of category name is 3" path="categoryName" cssStyle="width: 500px" required="required"/>
                </label>
                <div class="p-3 d-none">
                    <form:errors class="alert alert-danger" role="alert" path="categoryName"/>
                </div>
            </div>
            <div class="p-2">
                <label>Description:
                    <form:input type="text" class="form-control" id="formGroupExampleInput" pattern="[a-zA-Z ]{3,20}" title="Description can only contain letters and min length of description is 3" path="description" cssStyle="width: 500px" required="required"/>
                </label>
                <div class="p-3 d-none">
                    <form:errors class="alert alert-danger" role="alert" path="description"/>
                </div>
            </div>
            <div class="p-2">
                <label>Value:
                    <form:input type="text" class="form-control" id="formGroupExampleInput" pattern="(^[1-9]{1,1}[0-9]+\.[0-9]{1,2})||(^[1-9]\d+)||([1-9]+)||(0\.[0-9]{1,2})" title="The value must be a positive number" path="actualValue" cssStyle="width: 500px" required="required"/>
                </label>
                <div class="p-3 d-none">
                    <form:errors class="alert alert-danger" role="alert" path="actualValue"/>
                </div>
            </div>
                <div class="mt-2">
                    <form:button class="btn btn-primary" name="oldValue" value="${oldValue}" type="submit">Edit</form:button>
                </div>
        </form:form>
    </div>
</div>
<%@include file="footer.jsp" %>