<%@page import="models.Tour"%>
<%@page import="java.util.LinkedList"%>
<%@page import="models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="customtag" uri="/WEB-INF/tlds/taginput.tld"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <style type="text/css">
            <!--
            @import url("view/css/Table/style.css");
            @import url("view/css/Button/style.css");
            html, body
            {
                height: 100%;
                background: url(view/css/pics/background12.jpg) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
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
        <form action="GeneralController" method="post">	
            <table id="box-table-b" summary="Employee Pay Sheet">
                <thead>
                    <tr>
                <center><th scope="col" colspan="6"><b><customtag:display input="Avaliable tours"/></b></th></center>
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
                <input type="submit" value="Submit" class="button4" />
            </p>
        </form>
    </body>
</html>

