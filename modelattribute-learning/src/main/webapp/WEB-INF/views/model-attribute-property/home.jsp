<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Duy Nam
  Date: 4/29/2023
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Name: ${home.name} || Price: ${home.price}
    <a href="<c:url value='/home-page-two'/>">Page 2</a>
    <a href="<c:url value='/home-page-attr'/>">Page Attr</a>
</body>
</html>
