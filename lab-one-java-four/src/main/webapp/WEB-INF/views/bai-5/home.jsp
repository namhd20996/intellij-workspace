<%--
  Created by IntelliJ IDEA.
  User: Duy Nam
  Date: 6/30/2023
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value="/bai-5"/> " method="POST">
  <input type="number" name="length"/>
  <input type="number" name="width"/>
  <button type="submit">Submit</button>
</form>
 Diện tích: ${acreage}
 Chu vi: ${perimeter}
</body>
</html>
