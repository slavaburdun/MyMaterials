<%-- 
    Document   : login
    Created on : 1 ???? 2010, 11:38:30
    Author     : Family
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <title>Login</title>
    </head>
    <body>
        <h3>Login</h3>
        <hr/>
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login"/>
                Login:<br/>
                <input type="text" name="login" value=""><br/>
                Password:<br/>
                <input type="password" name="password" value="">
                <br/>
                <input type="submit" value="Enter">
        </form><hr/>
    </body>
</html>