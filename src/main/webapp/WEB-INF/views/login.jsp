<%@include file="header-start.jsp" %>
<div class="p-5 shadow-lg p-3 bg-body rounded" style="width: 50%; margin: auto; margin-top: 200px; margin-right: auto; margin-left: auto">
    <form:form class="row g-3 needs-validation d-flex justify-content-center" method="post" modelAttribute="user"
               action="/login">
        <div class="row">
            <div class="col-xl-6 col-md-6 position-relative">
                <label for="validationTooltipUsername" class="form-label">Email</label>
                <div class="input-group has-validation">
                    <span class="input-group-text" id="validationTooltipUsernamePrepend">@</span>
                    <form:input path="username" type="text" class="form-control" id="validationTooltipUsername"
                                aria-describedby="validationTooltipUsernamePrepend" required="true"/>
                    <div class="invalid-tooltip">
                        <form:errors path="username"/>
                    </div>
                </div>
            </div>
            <div class="col-xl-6 col-md-6 position-relative">
                <label for="inputPassword5" class="form-label">Password</label>
                <form:password path="password" class="form-control" id="inputPassword5" required="true"/>
                <form:errors cssClass="invalid-tooltip" path="password"/>
            </div>
        </div>
        <div class="row">
            <div class="col-auto p-3 align-content-end">
                <button class="btn btn-primary" type="submit">Login</button>
            </div>
        </div>

    </form:form>
</div>
<%@include file="footer.jsp" %>