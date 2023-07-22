<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Insert title here</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <style type="text/css">
        .red {
            color: red;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css">
</head>
<body>
<div class="container">
    <main class="form-signin w-100 m-auto">
    <h1>${message}</h1>
        <form class="text-center" action="<c:url value="/login"/>" method="POST">
            <img class="mb-4" src="<c:url value="/css/Duynam%20(4).png"/>" alt="" width="280px">
            <h1 class="h3 mb-3 fw-normal">ĐĂNG NHẬP</h1>
            <div class="text-center">
                <span class="red"></span>
            </div>
            <div class="text-center">
                <span class="red"></span>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="username"
                       placeholder="Username" name="username"> <label
                    for="username">Username</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="password"
                       placeholder="Mật khẩu" name="password"> <label
                    for="password">Password</label>
            </div>

            <div class="checkbox mb-3">
                <label> <input type="checkbox" value="remember-me">
                    Remember me?
                </label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">
                Login
            </button>
            <a href="">Register?</a>
            <p class="mt-5 mb-3 text-muted">&copy; 2018–2025</p>
        </form>
    </main>
</div>
</body>
</html>