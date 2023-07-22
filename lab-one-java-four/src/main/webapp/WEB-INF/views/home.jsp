<%--
  Created by IntelliJ IDEA.
  User: Duy Nam
  Date: 6/30/2023
  Time: 8:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<form action="<c:url value="/home-page"/> " method="POST">
    <input type="text" name="name"/>
    <button type="submit">Submit</button>
</form>
<a class="btn btn-primary" href="<c:url value="/bai-2" />">Bai - 2</a>
<a class="btn btn-warn" href="<c:url value="/bai-3-4" />">Bai - 3 - 4</a>
<a href="<c:url value="/bai-5" />">Bai - 5</a>
</body>
</html>
