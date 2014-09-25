<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            <!--
            @import url("view/css/Authorization/style.css");
            -->
        </style>
    </head>
    <body>
        <form id="login" action="GeneralController" method="post">
            <h1>Log In</h1>
            <fieldset id="inputs">
                <input id="username" type="text" name="login" placeholder="Username" autofocus required>   
                <input id="password" type="password" name="password" placeholder="Password" required>
                <input type="hidden" name="command" value="authorization">
            </fieldset>
            <fieldset id="actions">
                <input type="submit" id="submit" value="Log in">
            </fieldset>
        </form>
    </body>
</html>
