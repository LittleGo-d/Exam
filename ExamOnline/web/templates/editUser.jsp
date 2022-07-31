<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/7/6
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑用户</title>
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/layuimini.css" media="all">
    <link rel="stylesheet" href="../css/themes/default.css" media="all">
    <link rel="stylesheet" href="../lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <style>
        body{
            padding: 20px;
        }
    </style>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String userId = "";
    String userName = "";
    String sex = "";
    String profession = "";
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user_Id")){
                userId = cookie.getValue();
            }
            if(cookie.getName().equals("userName")){
                userName = cookie.getValue();
            }
            if(cookie.getName().equals("sex")){
                sex = cookie.getValue();
            }
            if(cookie.getName().equals("profession")){
                profession = cookie.getValue();
            }
        }
    }
%>
<form class="layui-form" action="">
    <input type="hidden" name="userId" value="<%=URLDecoder.decode(userId,"UTF-8")%>">
    <div class="layui-form-item">
        <label class="layui-form-label required">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="userName" lay-verify="required" lay-reqtext="姓名不能为空" placeholder="请输入姓名" value="<%=URLDecoder.decode(userName,"UTF-8")%>" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <select name="sex" lay-verify="required">
                <option value="<%=URLDecoder.decode(sex,"UTF-8")%>"><%=URLDecoder.decode(sex,"UTF-8")%></option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">专业</label>
        <div class="layui-input-block">
            <input type="text" name="profession" lay-verify="required" lay-reqtext="专业不能为空" placeholder="请输入专业" value="<%=URLDecoder.decode(profession,"UTF-8")%>" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="btn-submit" class="layui-btn" lay-submit lay-filter="userEdit-filter">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
<script src="../js/jquery.js" charset="utf-8"></script>
<script src="../layui/layui.js"></script>
<script src="../lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script inline="javascript">
    layui.use(['form','table'], function() {
        var form = layui.form;
        var tbale = layui.table;
        var $ = layui.$;
        form.on('submit(userEdit-filter)', function(data){
            //如果提交成功，将按钮置灰，防止反复提交
            $("#btn-submit").attr("disabled","disabled").addClass("layui-btn-disabled")
            var url = "<%=request.getContextPath()%>/user/editUser";
            var s = JSON.stringify(data.field);
            $.post(url,s,function (response){
                if(response.code == '0'){
                    layer.msg(response.message, {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        //刷新表格
                        parent.refreshTable();
                        //当你在iframe页面关闭自身时
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                }else{
                    layer.msg('修改失败，请联系管理员！', {
                        //新增失败，不可以关闭窗口
                        icon: 2,
                        anim: 6,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    });
                }
            })
        });
    });
</script>
</html>
