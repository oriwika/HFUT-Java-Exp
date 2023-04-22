<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/4/17
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息保存</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");

        String url = "jdbc:mysql://localhost:3306/myweb";
        Connection connection;
        int count;
        connection = DriverManager.getConnection(url,"root","123456");
        String sql = "insert into user values ('"+username+"','"+password+"','"+sex+"')";
        Statement stmt = connection.createStatement();
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            count = 0;
        }
        if(count > 0){
            response.getWriter().write("<h1>注册成功！</h1>");
        }
        else {
            response.getWriter().write("<h1>注册失败！</h1>");
        }
        stmt.close();
        connection.close();
    %>
</body>
</html>
