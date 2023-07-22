<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link active"
                                        aria-current="page" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Link</a></li>
                <li class="nav-item dropdown"><a
                        class="nav-link dropdown-toggle" href="#" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false"> Dropdown </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Something else
                            here</a></li>
                    </ul>
                </li>
                <li class="nav-item"><a class="nav-link disabled">Disabled</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor ">
                    <li class="nav-item dropdown dropstart"><a
                            class="nav-link dropdown-toggle" href="#" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">Account</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<c:url value="/login"/> ">Login</a></li>
                            <li><a class="dropdown-item"
                                   href="<c:url value="/logout"/> ">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</nav>
<!-- Navbar end -->


<div class="container">
    <h1>${message}</h1>
    <h1 style="text-align: center;">Quản Lý Thông Tin Người Dùng</h1>
    <form action="<c:url value="/user/create"/> " method="post">
        <div class="mb-3">
            <label for="id" class="form-label">Username</label>
            <input name="id" type="text" class="form-control"
                   id="id" aria-describedby="emailHelp"
                   value="${user.id}">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input name="password" type="password" value="${user.password}" class="form-control"
                   id="password">
        </div>

        <div class="mb-3">
            <label for="fullname" class="form-label">Fullname</label>
            <input name="fullname" type="text" class="form-control"
                   id="fullname" aria-describedby="emailHelp"
                   value="${user.fullname}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input name="email" type="email" class="form-control"
                   id="email" value="${user.email}">
        </div>
        <div class="mb-3">
            <label for="admin" class="form-label">Role</label> <br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="admin"
                       id="admin" value="true" ${user.admin ? 'checked': ''}> <label
                    class="form-check-label" for="admin">Admin</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" ${user.admin ? '': 'checked'} type="radio" name="admin"
                       id="user" value="false"> <label
                    class="form-check-label" for="user">User</label>
            </div>
        </div>
        <label class="form-label"
               style="color: red;"></label>
        <hr>
        <button type="submit" class="btn btn-primary">Create</button>
        <button formaction="<c:url value="/user/update"/> " type="submit" class="btn btn-warning">Update</button>
        <button formaction="<c:url value="/user/delete"/> " type="submit" class="btn btn-danger">Delete</button>
        <button type="reset" class="btn btn-success">Reset</button>
    </form>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Password</th>
            <th scope="col">Fullname</th>
            <th scope="col">Email</th>
            <th scope="col">Role</th>
            <th scope="col">Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${users}">
            <tr>
                <td>${item.id}</td>
                <td>${item.password}</td>
                <td>${item.fullname}</td>
                <td>${item.email}</td>
                <td>${item.admin ? "Admin" : "User"}</td>
                <td>
                    <a class="btn btn-info" href="<c:url value='/user/edit?id=${item.id}'/> ">Edit</a>
                    <a class="btn btn-danger" href="<c:url value='/user/delete?id=${item.id}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>