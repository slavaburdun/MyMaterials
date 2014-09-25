<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                background: url(view/css/pics/background14.jpeg) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                margin-left: auto;
                margin-right: 100px;
                text-align: left;
            }
            input[type=text] {
                border: 1px solid #000;
                padding: 4px 12px;
                border-radius: 8px;
                -webkit-appearance: none
            }
            -->
        </style>
    </head>
    <body>
        <form method="post" action="GeneralController">
            <table class="bordered">
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
            <center><input type="submit" class="button4" value="Submit"/></center>
            <input type="hidden" name="command" value="commitTour"/>
        </form>
    </body>
</html>
