<%@include file="header.jsp" %>
<div class="p-5 shadow-lg p-3 mb-5 bg-body rounded mt-5" style="width: 50%; margin: auto">
    <div class="pb-4 text-center d-flex justify-content-center">
        <form:form method="post" modelAttribute="categoryDto">
            <label>Category name:
                <form:input type="text" class="form-control" id="formGroupExampleInput" pattern="[a-zA-Z ]+" title="The name can only contain letters" path="categoryName" cssStyle="width: 500px"/>
            </label>
            <div class="p-3 d-none">
                <form:errors class="alert alert-danger" role="alert" path="categoryName"/>
            </div>
            <label>Description:
                <form:input type="text" class="form-control" id="formGroupExampleInput"  pattern="[a-zA-Z ]+" title="Description can only contain letters" path="description" cssStyle="width: 500px"/>
            </label>
            <div class="p-3 d-none">
                <form:errors class="alert alert-danger" role="alert" path="description"/>
            </div>
            <label>Value:
                <form:input type="text" class="form-control" id="formGroupExampleInput" pattern="(^[1-9]{1,1}[0-9]+\.[0-9]{1,2})||(^[1-9]\d+)||([1-9]+)||(0\.[0-9]{1,2})" title="The value must be a positive number" path="actualValue" cssStyle="width: 500px"/>
            </label>
            <div class="p-3 d-none">
                <form:errors class="alert alert-danger" role="alert" path="actualValue"/>
            </div>
            <form:button class="btn btn-primary" type="submit">Add</form:button>
        </form:form>
    </div>
</div>
<%@include file="footer.jsp" %>