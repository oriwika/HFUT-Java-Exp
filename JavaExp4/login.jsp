<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/4/18
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<form action="/doLogin.jsp" method="post">
    <h1>合肥工业大学用户登录</h1><br>
    <h2>用户名：</h2>
    <input name="username" type="text"><br>
    <h2>密码：</h2>
    <input name="password" type="password"><br>
    <input type="submit" name="登录">
</form>
</body>
</html>
