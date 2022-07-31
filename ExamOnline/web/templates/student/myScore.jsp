<%@ page import="com.oracle.wls.shaded.org.apache.xalan.xsltc.util.IntegerArray" %>
<%@ page import="jakarta.servlet.http.Cookie" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/7/13
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成绩</title>
    <style>
        .outBox {
            width: 60%;
            height: 400px;
            border: 1px solid rgb(107, 104, 104);
            box-shadow: 0 0 10px rgb(87, 80, 80);
            margin: 100px auto;
        }
        .score{
            font-size: 40px;
            width: 100px;
            font-weight: 600;
            color: red;
        }
        img{
            width: 180px;
            display: block;
            margin: 20px auto;
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
<input type="button" name="Submit" value="返回" onclick="toIndex()">
    <div class="outBox">
        <div style="font-size: 30px;font-weight:800;width: 240px;margin: 30px auto;">${requestScope.scoreMsg}</div>
        <div style="display:inline-block;font-size: 30px;font-weight:800;width: 260px;margin-left: 200px;margin-right: 30px;"><%=userName%>,你的分数是:</div>
        <span class="score">${requestScope.totalScore}分</span>
        <%
            String img = "";
            Integer totalScore = (Integer) request.getAttribute("totalScore");
            if(totalScore >= 60){
                img = "images/happy.jpg";
            }else{
                img = "images/unhappy.jpg";
            }
        %>
        <img src="<%=request.getContextPath()%>/<%=img%>">
    </div>
<script>
    function toIndex(){
        <%--window.location.replace("<%=request.getContextPath()%>/templates/student/studentIndex.jsp");--%>
        window.history.replaceState(null, "", '<%=request.getContextPath()%>/templates/student/studentIndex.jsp');
        window.history.go(0);
    }
</script>
</body>
</html>
