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
        <div id="container">
            <form id="form4">
                <table id="newspaper-a" summary="Employee Pay Sheet">
                    <thead>
                        <tr>
                    <center><th scope="col" colspan="4"><b>Tours you have selected</b></th></center>
                    </tr>
                    <tr>
                        <th>Price</th>
                        <th>Urgent</th>
                        <th>Discount</th>
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
                                    <c:out value="${tour.type}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <th colspan="4">Your balance is $<c:out value="${balance}"/></th>
                        </tr>
                        <tr>
                            <th colspan="4"><a href="GeneralController?command=addTourToCustomer">Add another tour</a></th>
                        </tr>
                        <tr>
                            <th colspan="4"><a href="CustomerView.jsp">Refresh</a></th>
                        </tr>
                        <tr>
                            <th colspan="4"><c:out value="${message}"/></th>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>

