<%--
  Created by IntelliJ IDEA.
  User: Duy Nam
  Date: 8/3/2023
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 offset-sm-3">
            <h1>Manager Book</h1>
            <h5>${message}</h5>
            <form action="<c:url value="/book/v1/add"/> " method="post">
                <div class="form-group">
                    <label for="id">Book code</label>
                    <input id="id" type="text" value="${book.id}" name="id" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="name">Book name</label>
                    <input id="name" value="${book.name}" type="text" name="name" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="price">Price</label>
                    <input id="price" value="${book.price}" type="number" name="price" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="year">Year</label>
                    <input id="year" value="${book.year}" type="number" name="year" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="category">Category</label>
                    <input id="category" value="${book.category}" type="text" name="category" class="form-control"/>
                </div>
                <div class="mt-2">
                    <button type="submit" class="btn btn-info">Add</button>
                    <button type="submit" formaction="<c:url value="/book/v1/update" />" class="btn btn-primary">Update</button>
                    <button type="submit" formaction="<c:url value="/book/v1/delete" />" class="btn btn-danger">Delete</button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                    <label>
                        <input placeholder="Filter by category" type="text" class="" name="categoryFind"/>
                        <button formaction="<c:url value="/book/v1/find" />" class="btn btn-info">Find</button>
                    </label>
                </div>
            </form>
        </div>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Year</th>
            <th scope="col">Category</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${books}">
            <tr>
                <th scope="row">${item.id}</th>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.year}</td>
                <td>${item.category}</td>
                <td><a href="<c:url value="/book/v1/edit?id=${item.id}" />">Edit</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>
</html>
