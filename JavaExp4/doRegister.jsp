<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.util.HashMap" %><%--
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
        String filename = request.getServletContext().getRealPath("/user.txt");
        String username = request.getParameter("username");
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String temp;
        boolean flag = false;
        while ((temp = br.readLine())!=null){
            String[] splited = temp.split(",");
            if(splited[0].equals(username)){
                response.getWriter().write("<h1>用户已存在</h1>");
                flag = true;
                break;
            }
        }
        br.close();
        if(flag == false){
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            System.out.println(request.getParameter("username")+","+request.getParameter("password")+","+request.getParameter("sex"));
            bw.write(username+","+request.getParameter("password")+","+request.getParameter("sex"));
            bw.flush();
            bw.newLine();
            bw.close();
            response.getWriter().write("<h1>注册成功</h1>");
            %>
    <script>
        alert("注册成功");
    </script>
    <%
        }
    %>
</body>
</html>
