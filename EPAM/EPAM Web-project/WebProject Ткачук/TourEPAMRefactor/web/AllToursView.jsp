<%@page import="models.Tour"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <style>
            <!--
            @import url("view/css/RoundedCornerTable/style.css");
            @import url("view/css/Button/logout.css");
            @import url("view/css/Button/style.css");
            html, body
            {
                height: 100%;
                background: url(view/css/pics/background17.jpeg) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            -->
        </style>
    </head>
    <body>
        <h2>All Tours</h2>
        <form method="post" action="GeneralController">
            <table class="zebra">
                <thead>
                    <tr>
                        <th>Price</th>
                        <th>Urgent</th>
                        <th>Discount</th>
                        <th>Type</th>
                        <th>Description</th>
                        <th>Modify</th>
                    </tr>
                </thead>
                <%
                    if (request.getAttribute("allTours") != null) {
                        pageContext.setAttribute("tours", (LinkedList<Tour>) request.getAttribute("allTours"));
                    }
                %>
                <c:forEach items="${tours}" var="tour">
                    <tr>
                        <td>
                            <c:out value="${tour.price}"/>
                        </td>
                        <td>
                            <c:out value="${tour.urgent}"/>
                        </td>
                        <td>
                            <c:out value="${tour.discount}"/>
                        </td>
                        <td>
                            <c:out value="${tour.type}"/>
                        </td>
                        <td>
                            <c:out value="${tour.description}"/>
                        </td>
                        <td>
                            <input type="checkbox" name="<c:out value="tour${tour.id}"/>" value="<c:out value="${tour.id}"/>"/>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="3">
                        <input type="button" onclick="location.href='GeneralController?command=createTour'" value="CreateTour" class="button4"/>
                    </th>
                    <th colspan="3">
                        <input type="submit" value="Submit" class="button4" />
                    </th>
                </tr>
            </table>
            <input type="hidden" name="command" value="modifyTours"/>
        </form>
    </body>
</html>
