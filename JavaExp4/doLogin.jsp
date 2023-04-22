<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %><%--
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
    request.setCharacterEncoding("UTF-8");
    String filename = request.getServletContext().getRealPath("/user.txt");
    String username = request.getParameter("username");
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String temp;
    boolean flag = false;
    while ((temp = br.readLine())!=null){
        String[] splited = temp.split(",");
        if(splited[0].equals(username)){
            if(splited[0].equals(request.getParameter("username"))){
                if(splited[1].equals(request.getParameter("password"))){
                    flag = true;
                    break;
                }
            }
        }
    }
    if(flag == true){
        response.getWriter().write("<h1>登录成功！</h1>");
        %>
<script>
    alert("登录成功");
</script>
<%
    }
    else {
        response.getWriter().write("<h1>用户名或密码错误，请检查输入。</h1>");
    }
    br.close();
%>
</body>
</html>
