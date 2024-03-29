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
<div class="p-5 shadow-lg p-3 mb-5 bg-body rounded mt-5" style="width: 50%; margin: auto">
    <div class="pb-4 text-center d-flex justify-content-center">
        <div class="p-2">
            <h2>Account balance: ${accountBalance}</h2>
        </div>
    </div>
    <hr>
    <div class="p-4 text-center d-flex justify-content-center">
        <form:form method="post" modelAttribute="accountDto">
            <div class="mb-3">
                <label>Amount to be added:
                    <form:input type="text" class="form-control" pattern="(^[1-9]{1,1}[0-9]+\.[0-9]{1,2})||(^[1-9]\d+)||([1-9]+)||(0\.[0-9]{1,2})" title="The value must be a positive number" id="formGroupExampleInput" path="amountToAdd"
                                cssStyle="width: 500px"/>
                </label>
                <div class="pt-3">
                    <form:errors class="alert alert-danger d-none" role="alert" path="amountToAdd"/>
                </div>
            </div>
            <div class="mb-3">
                <label>Set the amount on the account
                    <form:input type="text" class="form-control" pattern="(^[1-9]{1,1}[0-9]+\.[0-9]{1,2})||(^[1-9]\d+)||([1-9]+)||(0\.[0-9]{1,2})" title="The value must be a positive number" id="formGroupExampleInput2" path="balance"
                                cssStyle="width: 500px"/>
                </label>
                <div class="pt-3">
                    <form:errors class="alert alert-dangerdanger d-none" role="alert" path="balance"/>
                </div>
            </div>
            <c:if test="${not empty requiredParam}">
                <div class="alert alert-danger pt-3 pb-4" role="alert">
                        ${requiredParam}
                </div>
            </c:if>
            <form:button class="btn btn-primary" type="submit">Save</form:button>
        </form:form>
    </div>
</div>
<%@include file="footer.jsp" %>