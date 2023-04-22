<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/4/17
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<form action="/doRegister.jsp" method="post">
    <h1>合肥工业大学用户注册</h1><br>
    <h2>用户名：</h2>
    <input name="username" type="text"><br>
    <h2>密码：</h2>
    <input name="password" type="password"><br>
    <h2>性别：</h2>
    <select name="sex">
        <option value="man">男</option>
        <option value="woman">女</option>
    </select>
    <input type="submit" value="注册">
</form>
</body>
</html>