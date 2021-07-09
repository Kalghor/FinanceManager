<%@include file="header-start.jsp" %>
<div class="p-5 shadow-lg p-3 mb-5 bg-body rounded mt-5" style="width: 50%; margin: auto">
    <form:form class="row g-3 needs-validation" method="post" modelAttribute="user" action="/register">
        <div class="row">
            <div class="col-xl-6 col-md-6 position-relative">
                <label for="validationTooltipUsername" class="form-label">Username</label>
                <div class="input-group has-validation">
                    <span class="input-group-text" id="validationTooltipUsernamePrepend">@</span>
                    <form:input path="username" type="text" class="form-control" id="validationTooltipUsername"
                                aria-describedby="validationTooltipUsernamePrepend" required="true"/>
                    <div class="pt-3">
                        <form:errors class="alert alert-danger" role="alert" path="username"/>
                    </div>
                </div>
            </div>
            <div class="col-xl-6 col-md-6 position-relative">
                <label for="inputPassword5" class="form-label">Password</label>
                <form:password path="password" class="form-control" id="inputPassword5" required="true"/>
                <div class="pt-3 pb-4">
                    <form:errors class="alert alert-danger" role="alert" path="password"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-6 col-md-6">
                <label for="validationCustom01" class="form-label">First name</label>
                <form:input path="firstName" type="text" class="form-control" id="validationCustom01" required="true"/>
                <div class="pt-3 pb-4">
                    <form:errors class="alert alert-danger" role="alert" path="firstName"/>
                </div>
            </div>
            <div class="col-xl-6 col-md-6">
                <label for="validationCustom02" class="form-label">Last name</label>
                <form:input path="lastName" type="text" class="form-control" id="validationCustom02" required="true"/>
                <div class="pt-3">
                    <form:errors class="alert alert-danger" role="alert" path="lastName"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-auto p-3 align-content-end">
                <button class="btn btn-primary" type="submit">Register</button>
            </div>
        </div>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<%@include file="footer.jsp" %>