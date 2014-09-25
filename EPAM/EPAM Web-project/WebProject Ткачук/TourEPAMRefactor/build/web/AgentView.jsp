<%@page import="models.TourAgent"%>
<%@page import="models.Customer"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <style type="text/css">
            <!--
            @import url("view/css/Menu/style.css");
            html, body
            {
                height: 100%;
                background: url(view/css/pics/background13.jpg) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            -->
        </style>
    </head>
    <body>
        <%
            TourAgent agent = (TourAgent) session.getAttribute("agent");
            pageContext.setAttribute("login", agent.getLogin());
            if (request.getAttribute("message") != null) {
                pageContext.setAttribute("message", request.getAttribute("message"));
            }
        %>
        <form method="post" action="GeneralController">
            <div id="navbox">
                <ul class="nav">
                    <li>
                        <a href="GeneralController?command=createTour">Create new Tour</a>
                    </li>
                    <li>
                        <a href="GeneralController?command=allTours">All Tours</a>
                    </li>
                    <li>
                        <a href="GeneralController?command=logout&role=agent">Logout</a>
                    </li>
                </ul>
                <b><c:out value="${message}"/></b>
            </div>
        </form>
    </body>
</html>

