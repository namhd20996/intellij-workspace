<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Duy Nam
  Date: 5/24/2023
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="col-sm-6 offset-sm-3 mt-5">
        <h1>Send mail</h1>
        <form action="<c:url value="/home-page"/>" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="">Fullname</label>
                <input type="text" class="form-control" name="fullname">
            </div>
            <div class="form-group">
                <label for="">Email</label>
                <input type="email" class="form-control" name="email">
            </div>
            <div class="form-group">
                <label for="">Subject</label>
                <input type="text" class="form-control" name="subject">
            </div>
            <div class="form-group">
                <label for="">Content</label>
                <textarea name="content" id="" cols="30" rows="4" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label for="">File</label>
                <input type="file" class="form-control" name="attachment">
            </div>
            <button type="submit" class="btn btn-info mt-2 form-control">Send?</button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>
</html>
