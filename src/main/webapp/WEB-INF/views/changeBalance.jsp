<%@include file="header.jsp" %>
<div>
    Account balance: ${accountBalance} </br>
    <form:form method="post" modelAttribute="accountDto">
        <label>Amount to be added:
            <form:input path="amountToAdd"/>
        </label><form:errors path="amountToAdd"/><br/>
        <label>Set the amount on the account
            <form:input path="balance"/>
        </label><form:errors path="balance"/><br/>
        <c:if test="${not empty requiredParam}">
            <div class="alert alert-danger pt-3 pb-4" role="alert">
                    ${requiredParam}
            </div>
        </c:if>
        <form:button type="submit">Next</form:button>
    </form:form>
</div>
<%@include file="footer.jsp" %>