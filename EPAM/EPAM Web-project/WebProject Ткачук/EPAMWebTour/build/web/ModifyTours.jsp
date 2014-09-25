<%@page import="models.Tour"%>
<%@page import="java.util.LinkedList"%>
<%@page import="models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            <!--
            @import url("./Tables/style.css");
            -->
        </style>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    </head>
    <body>
        <%
            if (request.getAttribute("selectedTours") != null) {
                pageContext.setAttribute("tours", (LinkedList<Tour>) request.getAttribute("selectedTours"));
            }
        %>
        <div id="container">
            <form id="form4" action="GeneralController" method="post">	
                <table id="newspaper-a" summary="Employee Pay Sheet">
                    <thead>
                        <tr>
                    <center><th scope="col" colspan="2"><b>Insert new values</b></th></center>
                    </tr>
                    <tr>
                        <th>Urgent</th>
                        <th>Discount</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${tours}" var="tour">
                            <tr>
                        <input type="hidden" name="tourId<c:out value="${tour.id}"/>" value="<c:out value="${tour.id}"/>"/>
                        <td>
                            <input type="text" name ="urgent<c:out value="${tour.id}"/>" value="<c:out value="${tour.urgent}"/>"/>
                        </td>
                        <td>
                            <input type="text" name ="discount<c:out value="${tour.id}"/>" value="<c:out value="${tour.discount}"/>"/>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input type="hidden" name="command" value="commitModify">
                <p class="submit">
                    <button type="submit">Send</button>
                </p>
            </form>
        </div>
    </body>
</html>

