<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style type="text/css" rel="stylesheet">
        <%@include file="../../css/bootstrap.min.css" %>
    </style>
    <style type="text/css" rel="stylesheet">
        <%@include file="../../css/style.css" %>
    </style>
</head>
<body>
<div class="my-5">
    <div class="container">
        <div class="p-5 shadow-lg p-3 mb-5 bg-body rounded" style="width: 50%; margin: auto">
            <h2 class="p-2 d-flex justify-content-center">Welcome on main website :)</h2>

            <div class="d-flex justify-content-center">
                <div class="col-auto m-auto">
                    <a href="http://localhost:8080/login">
                        <button type="button" class="btn btn-primary">Login</button>
                    </a>
                </div>
                <div class="col-auto m-auto">
                    <a href="http://localhost:8080/register">
                        <button type="button" class="btn btn-primary">Register</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>