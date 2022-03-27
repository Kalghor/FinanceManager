<%@include file="header.jsp" %>
<div class="p-5 shadow-lg p-3 mb-5 bg-body rounded mt-5" style="width: 50%; margin: auto">
    <div class="pb-4 text-center d-flex justify-content-center">
        <form:form method="post" modelAttribute="categoryDto">
            <label>Category name:
                <form:input type="text" class="form-control" id="formGroupExampleInput" path="categoryName" cssStyle="width: 500px"/>
            </label>
            <div class="p-3" cssStyle="width: 350px; height: 10px">
                <form:errors class="alert alert-danger" role="alert" path="categoryName"/>
            </div>
            <label>Description:
                <form:input type="text" class="form-control" id="formGroupExampleInput"  path="description" cssStyle="width: 500px"/>
            </label>
            <div class="p-3" cssStyle="width: 350px; height: 10px">
                <form:errors class="alert alert-danger" role="alert" path="description"/>
            </div>
            <label>Value:
                <form:input type="text" class="form-control" id="formGroupExampleInput"  path="actualValue" cssStyle="width: 500px"/>
            </label>
            <div class="p-3" cssStyle="width: 350px; height: 10px">
                <form:errors class="alert alert-danger" role="alert" path="actualValue"/>
            </div>

            <div class="row form-group">
                <label for="date" class="">Start date: </label>
                <div class="col-xl-8">
                    <div class="input-group date" id="datepicker">
                        <form:input type="text" class="form-control" id="formGroupExampleInput" path="schedulingDate" cssStyle="width: 200px" />
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

                        <div class="row form-group d-none">
                            <label for="date" class="">Stop date: </label>
                            <div class="col-xl-8">
                                <div class="input-group date" id="datepicker2">
                                    <form:input type="text" class="form-control" id="formGroupExampleInput" path="schedulingStopDate" cssStyle="width: 200px" />
                                    <span class="input-group-append">
                                        <span class="input-group-text bg-white d-block">
                                            <i class="fa fa-calendar"></i>
                                        </span>
                                    </span>
                                </div>
                            </div>
                        </div>
            <form:button class="btn btn-primary" type="submit">Add</form:button>
        </form:form>
    </div>
</div>

<%@include file="footer.jsp" %>