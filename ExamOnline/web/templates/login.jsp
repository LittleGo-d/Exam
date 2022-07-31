<%@ page import="jakarta.servlet.http.Cookie" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/2/28
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
</head>
<%
    Cookie[] cookies = request.getCookies();
    String userId = "";
    String password = "";
    if(cookies != null){
        for(Cookie cookie : cookies){
            if("userId".equals(cookie.getName())){
                userId = cookie.getValue();
            }
            if("password".equals(cookie.getName())){
                password = cookie.getValue();
            }
        }
    }
%>
<body style="background-image: url('<%=request.getContextPath()%>/images/exam-back.jpg');background-size: cover;background-repeat: no-repeat;">
<div id="loginDiv" style="height: 400px">
    <form action="<%=request.getContextPath()%>/user/login" method="post" id="form">
        <h1 id="loginMsg">EXAM-ONLINE</h1>
        <div id="errorMsg">${login_msg}${register_msg}</div>
        <p>UserId:<input id="userId" name="userId" value="<%=userId%>" type="number"></p>

        <p>Password:<input id="password" name="password" value="<%=password%>" type="password"></p>

        <p>
            验证码:<input name="checkCode" type="text" required id="checkCode">
            <img id="checkCodeImg" src="<%=request.getContextPath()%>/checkCodeServlet">
            <a href="#" id="changeImg">看不清？</a>
        </p>

        <p>Remember:<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
            <a href="<%=request.getContextPath()%>/templates/register.jsp">没有账号？</a>
        </div>
    </form>
</div>
<script>
    document.getElementById("changeImg").onclick = function (){
        //时间永远在变，此处路径加上时间，就可以保证路径永远不会被占用
        document.getElementById("checkCodeImg").src = "<%=request.getContextPath()%>/checkCodeServlet?" + new Date().getMilliseconds();
    }
    document.getElementById("checkCodeImg").onclick = function (){
        this.src = "<%=request.getContextPath()%>/checkCodeServlet?" + new Date().getMilliseconds();
    }
</script>
</body>
</html>
