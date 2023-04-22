<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/4/18
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录信息</title>
</head>
<body>
<%
    try{
    String url = "jdbc:mysql://localhost:3306/myweb";
    Connection connection = DriverManager.getConnection(url,"root","123456");
    String sql = "select * from user";
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    boolean flag = false;
    while(rs.next()){
        if(username.equals(rs.getString(1))&&password.equals(rs.getString(2))){
            flag = true;
        }
    }
    if(flag == true) {
        response.getWriter().write("<h1>登录成功！</h1>");
    }
    else {
        response.getWriter().write("<h1>用户名或密码错误，请检查输入。</h1>");
    }
    }catch (SQLException e){
        e.printStackTrace();
    }
%>
</body>
</html>
