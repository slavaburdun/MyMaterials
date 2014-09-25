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
            @import url("view/css/RoundedCornerTable/style.css");
            @import url("view/css/Button/logout.css");
            @import url("view/css/Button/style.css");
            html, body
            {
                height: 100%;
                background: url(view/css/pics/background15.jpeg) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                margin-left: auto;
                margin-right: auto;
                width: 50em;
                text-align: center;
            }
            input[type=text] {
                border: 1px solid #000;
                padding: 4px 12px;
                border-radius: 8px;
                -webkit-appearance: none
            }
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
        <form action="GeneralController" method="post">	
            <table class="zebra" summary="Employee Pay Sheet">
                <thead>
                    <tr>
                        <th><center>Urgent</center></th>
                <th><center>Discount</center></th>
                </tr>
                </thead>
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
            </table>
            <input type="hidden" name="command" value="commitModify"/>
            <center><input type="submit" value="Submit" class="button4" /></center>
        </form>
    </body>
</html>

