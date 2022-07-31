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
    <title>编辑考试信息</title>
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
    String examId = "";
    String description = "";
    String course = "";
    String paperId = "";
    String examDate = "";
    String totalTime = "";
    String grade = "";
    String term = "";
    String major = "";
    String totalScore = "";
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("examId")){
                examId = cookie.getValue();
            }
            if(cookie.getName().equals("description")){
                description = cookie.getValue();
            }
            if(cookie.getName().equals("course")){
                course = cookie.getValue();
            }
            if(cookie.getName().equals("paperId")){
                paperId = cookie.getValue();
            }
            if(cookie.getName().equals("examDate")){
                examDate = cookie.getValue();
            }
            if(cookie.getName().equals("totalTime")){
                totalTime = cookie.getValue();
            }
            if(cookie.getName().equals("grade")){
                grade = cookie.getValue();
            }
            if(cookie.getName().equals("term")){
                term = cookie.getValue();
            }
            if(cookie.getName().equals("major")){
                major = cookie.getValue();
            }
            if(cookie.getName().equals("totalScore")){
                totalScore = cookie.getValue();
            }
        }
    }
%>
<form class="layui-form" action="">
    <input type="hidden" name="examId" value="<%=URLDecoder.decode(examId,"UTF-8")%>">
    <div class="layui-form-item">
        <label class="layui-form-label required">描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" lay-verify="required" lay-reqtext="描述不能为空" placeholder="请输入描述" value="<%=URLDecoder.decode(description,"UTF-8")%>" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">课程</label>
        <div class="layui-input-block">
            <input type="text" name="course" lay-verify="required" lay-reqtext="课程不能为空" placeholder="请输入课程" value="<%=URLDecoder.decode(course,"UTF-8")%>" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">考试日期</label>
        <div class="layui-input-block">
            <input type="text" name="examDate" lay-verify="required" lay-reqtext="考试日期不能为空" placeholder="请输入考试日期" value="<%=URLDecoder.decode(examDate,"UTF-8")%>" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">考试时间</label>
        <div class="layui-input-block">
            <input type="text" name="totalTime" lay-verify="required" lay-reqtext="考试时间不能为空" placeholder="请输入考试时间" value="<%=URLDecoder.decode(totalTime,"UTF-8")%>" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">专业</label>
        <div class="layui-input-block">
            <input type="text" name="major" lay-verify="required" lay-reqtext="专业不能为空" placeholder="请输入专业" value="<%=URLDecoder.decode(major,"UTF-8")%>" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="btn-submit" class="layui-btn" lay-submit lay-filter="examEdit-filter">立即提交</button>
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
        form.on('submit(examEdit-filter)', function(data){
            //如果提交成功，将按钮置灰，防止反复提交
            $("#btn-submit").attr("disabled","disabled").addClass("layui-btn-disabled")
            var url = "<%=request.getContextPath()%>/exam/editExam";
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
