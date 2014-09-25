<%@page import="models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            <!--
            @import url("view/css/Table/style.css");
            @import url("view/css/Button/logout.css");
            html, body
            {
                height: 100%;
                background: url(view/css/pics/background10.jpeg) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            -->
        </style>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    </head>
    <body>
        <form>
            <table id="box-table-b">
                <thead>
                    <tr>
                        <th scope="col" colspan="5"><b>Tours you have selected</b></th>
                    </tr>
                    <tr>
                        <th>Price</th>
                        <th>Urgent</th>
                        <th>Discount</th>
                        <th>Description</th>
                        <th>Type</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Customer customer = (Customer) session.getAttribute("customer");
                        pageContext.setAttribute("tours", customer.getTours());
                        pageContext.setAttribute("balance", customer.getBalance());
                        if (request.getAttribute("message") != null) {
                            pageContext.setAttribute("message", request.getAttribute("message"));
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
                                <c:out value="${tour.description}"/>
                            </td>
                            <td>
                                <c:out value="${tour.type}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="5">Your balance is $<c:out value="${balance}"/></th>
                    </tr>
                    <tr>
                        <th colspan="3"><a href="GeneralController?command=addTourToCustomer" class="a_demo_one">Add another tour</a></th>
                        <th colspan="2"><a href="GeneralController" class="a_demo_one">Refresh</a></th>
                    </tr>
                    <tr>
                        <th colspan="5"><a href="GeneralController?command=logout&role=customer" class="a_demo_one">Logout</a>
                    </tr>
                    <tr>
                        <th colspan="5"><c:out value="${message}"/></th>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>

