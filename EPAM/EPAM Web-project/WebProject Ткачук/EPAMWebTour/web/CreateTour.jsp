<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <th scope="col" colspan="2"><b>Add new tour</b></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Price</td>
                        <td><center><input type="text" name="price"/></center></td>
                </tr>
                <tr>
                    <td>Urgent tour</td>
                    <td>
                        <ul>
                            <li>
                                <input type="radio" name="urgent" value="true"/>Yes
                            </li>
                            <li>
                                <input type="radio" name="urgent" value="false"/>No
                            </li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>Discount for regular clients</td>
                    <td><center><input type="text" name="discount"/></center></td>
                </tr>
                <tr>
                    <td>Type of tour</td>
                    <td>
                        <ul>
                            <li>
                                <input type="radio" name="type" value="shopping"/>Shopping
                            </li>
                            <li>
                                <input type="radio" name="type" value="excursion"/>Excursion
                            </li>
                            <li>
                                <input type="radio" name="type" value="vacation"/>Vacation
                            </li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>
                        Description
                    </td>
                    <td>
                        <center><input type="text" name="description"/></center>
                    </td>
                </tr>
                </tbody>
            </table>
            <p class="submit">
                <button type="submit">Send</button>
            </p>
            <input type="hidden" name="command" value="commitTour"/>
        </form>
    </body>
</html>
