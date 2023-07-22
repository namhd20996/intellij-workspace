<%--
  Created by IntelliJ IDEA.
  User: Duy Nam
  Date: 6/30/2023
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <%--%>
<%--        String message = request.getAttribute("message")+"";--%>
<%--    %>--%>
<form action="<c:url value="/bai-3-4"/> " method="POST">
    <input type="text" name="message"/>
    <button type="submit">Submit</button>

<%--    <h1><%=message%></h1>--%>

    <h1>${message}</h1>
</form>
</body>
</html>
