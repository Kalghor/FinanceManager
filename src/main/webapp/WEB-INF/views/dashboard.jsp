<%@include file="header.jsp" %>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid justify-content-center">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link active" style="color: #7fd56f" href="${pageContext.request.contextPath}/app/dashboard">Dashboard</a>
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
        <div class="p-3">
            <a href="http://localhost:8080/app/changeBalance">
                <button type="button" class="btn btn-primary btn-sm">Change account balance</button>
            </a>
        </div>
    </div>
    <hr>
    <div class="d-flex justify-content-center">
        <c:forEach var="category" items="${categories}">
            <div class="rounded border border-success align-items-center"
                 style="padding: 20px; display: inline-block; margin: 10px">
                    ${category.categoryName} </br>
                    ${category.actualValue} zl </br>
                <a href="http://localhost:8080/app/showDetails/${category.categoryName}">
                    <button type="button" class="btn btn-primary btn-sm">Show details</button>
                </a>
            </div>
        </c:forEach>
    </div><br><br><br>
    <div class="p-3">
        <a href="http://localhost:8080/app/addExpense">
            <button type="button" class="btn btn-primary btn-sm">Add expense</button>
        </a>
    </div>
</div>
<%@include file="footer.jsp" %>