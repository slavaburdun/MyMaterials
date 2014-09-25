<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            <!--
            @import url("./Authorization/style.css");
            -->
        </style>
    </head>
    <body>
        <div id="container">
            <center><h1>Login Page</h1></center>
            <form id="form4" action="GeneralController" method="post">	
                <fieldset>
                    <p class="first">
                        <label for="login">Login</label>
                        <input name="login" id="name" size="30" type="text">
                    </p>
                    <p>
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" size="30" type="text">
                    </p>
                    <center><h2>Choose your role:</h2></center>
                    <table style="margin-left: auto; margin-right: auto; text-align: center; width:100%">
                        <tbody>
                            <tr>
                                <td>
                                    <input type="radio" name="role" value="customer">
                                </td>
                                <td>
                                    Customer
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" name="role" value="manager">
                                </td>
                                <td>
                                    Manager
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <p class="submit">
                        <button type="submit">Send</button>
                    </p>
                    <input type="hidden" name="command" value="authorization">
                </fieldset>
            </form>
        </div>
    </body>
</html>
