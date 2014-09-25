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
            @import url("./Lists/style.css");
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
            <div id="list3">
                <b>What do you want to do, <c:out value="${login}"/>?</b>
                <ul>
                    <li>
                        <a href="GeneralController?command=createTour">Create new Tour</a>
                    </li>
                    <li>
                        <a href="GeneralController?command=allTours">All Tours</a>
                    </li>
                </ul>
                <b><c:out value="${message}"/></b>
            </div>
        </form>
    </body>
</html>

