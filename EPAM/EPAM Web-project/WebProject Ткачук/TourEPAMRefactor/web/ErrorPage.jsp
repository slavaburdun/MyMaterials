<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <%
        String message = (String) request.getAttribute("message");
        pageContext.setAttribute("message", message);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link href="view/css/ErrorPage/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <center><h2>This is 404, baby!<span></span></h2></center>
    <center><h3>Error <c:out value="${message}"/></h3></center>
    <img src="view/css/ErrorPage/images/404.jpg" alt="" style="position: absolute; left: 50%; top: 50%; margin-left: -285px; margin-top: -190px;">
    <div id="list3">
        <ul>
            <li>
                <a href="./index.jsp">Authorization Page</a>
            </li>
        </ul>
    </div>
</body>
</html>
