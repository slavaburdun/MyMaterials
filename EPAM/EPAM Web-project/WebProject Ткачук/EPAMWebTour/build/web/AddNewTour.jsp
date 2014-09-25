<%@page import="models.Tour"%>
<%@page import="java.util.LinkedList"%>
<%@page import="models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="WEB-INF/tlds/custom.tld"%>

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
    <ex:Title title="AddNewTour"/>
    <body>
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            LinkedList<Tour> freeTours = (LinkedList<Tour>) request.getAttribute("freeTours");
            pageContext.setAttribute("freeTours", freeTours);
            pageContext.setAttribute("balance", customer.getBalance());
        %>
        <div id="container">
            <form id="form4" action="GeneralController" method="post">	
                <table id="newspaper-a" summary="Employee Pay Sheet">
                    <thead>
                        <tr>
                    <center><th scope="col" colspan="6"><b>Avaliable tours</b></th></center>
                    </tr>
                    <tr>
                        <th>Price</th>
                        <th>Urgent</th>
                        <th>Discount</th>
                        <th>Type</th>
                        <th>Description</th>
                        <th>To add</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${freeTours}" var="tour">
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
                                    <input type="checkbox" name="tour<c:out value="${tour.id}"/>" value="<c:out value="${tour.id}"/>">
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <th colspan="6">Your balance is $<c:out value="${balance}"/></th>
                        </tr>
                    </tbody>
                </table>
                <input type="hidden" name="command" value="buy">
                <p class="submit">
                    <button type="submit">Send</button>
                </p>
            </form>
        </div>
    </body>
</html>

