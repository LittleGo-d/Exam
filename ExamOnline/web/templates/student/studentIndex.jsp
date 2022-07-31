<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/7/12
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生考试</title>
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        .head-nav{
            background-color: white;
            height: 60px;
            width: 100%;
            box-shadow: 0 0 5px;
        }
        .ul-nav{
            display: flex;
        }
        li{
            list-style-type: none;
            text-align: center;
            line-height: 50px;
            margin-left: 30px;

        }
        a{
            text-decoration: none;
            font-size: 20px;
            font-weight: 900;
            color: rgb(116, 112, 112);
        }
        span{
            display: inline-block;
            margin: 100px auto;
            font-size: 20px;
            font-weight: 800;
        }
    </style>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String userName = "";
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("username")){
            userName = cookie.getValue();
        }
    }
%>
<div class="head-nav">
    <ul class="ul-nav">
        <li><a href="pageList.jsp">所有考试</a></li>
        <li><a href="#">考试历史</a></li>
        <li><a href="#">我的成绩</a></li>
        <li><a href="<%=request.getContextPath()%>/user/exit">退出登录</a></li>
    </ul>
</div>
<span><%=URLDecoder.decode(userName,"UTF-8")%> 欢迎您来到在线考试系统</span>
</body>
</html>
