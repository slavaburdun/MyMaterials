<%@page import="models.Tour"%>
<%@page import="java.util.LinkedList"%>
<%@page import="models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <style type="text/css">
            <!--
            @import url("./Tables/style.css");
            -->
        </style>
    </head>
    <body>
        <form id="form4" method="post" action="GeneralController">
            <table id="newspaper-a">
                <thead>
                    <tr>
                        <th scope="col" colspan="6"><b>All tours</b></th>
                    </tr>
                    <tr>
                        <th>Price</th>
                        <th>Urgent</th>
                        <th>Discount</th>
                        <th>Type</th>
                        <th>Description</th>
                        <th>Modify</th>
                    </tr>
                </thead>
                <tbody>
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
                        <th colspan="6"><a href="GeneralController?command=createTour">Create new Tour</a></th>
                    </tr>
                </tbody>
            </table>
            <p class="submit">
                <button type="submit">Send</button>
            </p>
            <input type="hidden" name="command" value="modifyTours"/>
        </form>
    </body>
</html>

