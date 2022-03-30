<%@include file="header.jsp" %>
<div class="p-5 shadow-lg p-3 bg-body rounded" style="width: 50%; margin-top: 100px; margin-right: auto; margin-left: auto">
    <div class="pb-4 text-center d-flex justify-content-center">
        <form:form method="post" modelAttribute="categoryDto">
            <div class="p-2">
                <label>Category name:
                    <form:input type="text" class="form-control" pattern="[a-zA-Z ]{3,20}" title="The name can only contain letters and min length of category name is 3" id="formGroupExampleInput" path="categoryName" cssStyle="width: 500px" required="required"/>
                </label>
                <div class="p-3 d-none" cssStyle="width: 350px; height: 10px">
                    <form:errors class="alert alert-danger" role="alert" path="categoryName"/>
                </div>
            </div>
            <div class="p-2">
                <label>Description:
                    <form:input type="text" class="form-control" id="formGroupExampleInput" pattern="[a-zA-Z ]{3,20}" title="Description can only contain letters and min length of description is 3" path="description" cssStyle="width: 500px" required="required"/>
                </label>
                <div class="p-3 d-none" cssStyle="width: 350px; height: 10px">
                    <form:errors class="alert alert-danger" role="alert" path="description"/>
                </div>
            </div>
            <div class="p-2">
                <label>Value:
                    <form:input type="text" class="form-control" id="formGroupExampleInput" pattern="(^[1-9]{1,1}[0-9]+\.[0-9]{1,2})||(^[1-9]\d+)||([1-9]+)||(0\.[0-9]{1,2})" title="The value must be a positive number" path="actualValue" cssStyle="width: 500px" required="required"/>
                </label>
                <div class="p-3 d-none" cssStyle="width: 350px; height: 10px">
                    <form:errors class="alert alert-danger" role="alert" path="actualValue"/>
                </div>
            </div>
            <div class="p-2">
                <div class="row form-group">
                    <label for="date" class="">Start date: </label>
                    <div class="col-xl-8">
                        <div class="input-group date" id="datepicker">
                            <form:input type="text" class="form-control" id="formGroupExampleInput" pattern="^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$" title="You must enter a valid date" path="schedulingDate" cssStyle="width: 200px" required="required"/>
                            <span class="input-group-append">
                                <span class="input-group-text bg-white d-block">
                                    <i class="fa fa-calendar"></i>
                                </span>
                            </span>
                        </div>
                    </div>
                    <div class="col-xl-4">
                        <Label>Every month
                            <form:checkbox path="monthly" value="true"/>
                        </Label>
                    </div>
                </div>
            </div>
            <form:button class="btn btn-primary mt-2" type="submit">Add</form:button>
        </form:form>
    </div>
</div>

<%@include file="footer.jsp" %>