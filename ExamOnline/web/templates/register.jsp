<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/2/28
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="<%=request.getContextPath()%>/css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="<%=request.getContextPath()%>/user/register" method="post">

        <table>
            <tr>
                <td>学号</td>
                <td class="inputs">
                    <input name="userId" type="number" id="userId">
                    <br>
                    <span id="username_err" class="err_msg">${register_msg}</span>
                </td>
            </tr>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="userName" type="text" id="username">
                </td>
            </tr>

            <tr>
                <td>性别</td>
                <td class="inputs">
                    <select class="sex" name="sex">
                        <option value=""></option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>专业</td>
                <td class="inputs">
                    <input name="profession" type="text" id="profession">
                </td>
            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img id="checkCodeImg" src="<%=request.getContextPath()%>/checkCodeServlet">
                    <a href="#" id="changeImg">看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
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
