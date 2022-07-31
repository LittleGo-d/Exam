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
    <title>编辑填空题库</title>
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/layuimini.css" media="all">
    <link rel="stylesheet" href="../../css/themes/default.css" media="all">
    <link rel="stylesheet" href="../../lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <style>
        body{
            padding: 20px;
        }
    </style>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String questionId = "";
    String subject = "";
    String content = "";
    String answer = "";
    String score = "";
    String section = "";
    String difficulty = "";
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("questionId")){
                questionId = cookie.getValue();
            }
            if(cookie.getName().equals("subject")){
                subject = cookie.getValue();
            }
            if(cookie.getName().equals("content")){
                content = cookie.getValue();
            }
            if(cookie.getName().equals("answer")){
                answer = cookie.getValue();
            }
            if(cookie.getName().equals("score")){
                score = cookie.getValue();
            }
            if(cookie.getName().equals("section")){
                section = cookie.getValue();
            }
            if(cookie.getName().equals("difficulty")){
                difficulty = cookie.getValue();
            }
        }
    }
%>
<form class="layui-form" action="">
    <input type="hidden" name="questionId" value="<%=URLDecoder.decode(questionId,"UTF-8")%>">
    <div class="layui-form-item">
        <label class="layui-form-label">科目</label>
        <div class="layui-input-block">
            <select name="subject" lay-verify="required">
                <option value="<%=URLDecoder.decode(subject,"UTF-8")%>"><%=URLDecoder.decode(subject,"UTF-8")%></option>
                <option value="计算机组成原理">计算机组成原理</option>
                <option value="计算机网络">计算机网络</option>
                <option value="软件测试">软件测试</option>
                <option value="计算方法">计算方法</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">题目详细</label>
        <div class="layui-input-block">
            <input type="text" name="content" lay-verify="required" lay-reqtext="题目不能为空" placeholder="请输入题目" value="<%=URLDecoder.decode(content,"UTF-8")%>" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分值</label>
        <div class="layui-input-block">
            <select name="score" lay-verify="required">
                <option value="<%=URLDecoder.decode(score,"UTF-8")%>"><%=URLDecoder.decode(score,"UTF-8")%></option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">答案</label>
        <div class="layui-input-block">
            <input type="text" name="answer" lay-verify="required" lay-reqtext="答案不能为空" placeholder="请输入答案" value="<%=URLDecoder.decode(answer,"UTF-8")%>" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所属章节</label>
        <div class="layui-input-block">
            <input type="text" name="section" lay-verify="required" lay-reqtext="章节不能为空" placeholder="请输入所属章节" value="<%=URLDecoder.decode(section,"UTF-8")%>" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">难度</label>
        <div class="layui-input-block">
            <select name="difficulty" lay-verify="required">
                <option value="<%=URLDecoder.decode(difficulty,"UTF-8")%>"><%=URLDecoder.decode(difficulty,"UTF-8")%></option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="btn-submit" class="layui-btn" lay-submit lay-filter="questionEdit-filter">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
<script src="../../js/jquery.js" charset="utf-8"></script><script src="../../layui/layui.js"></script>
<script src="../../lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script inline="javascript">
    layui.use(['form','table'], function() {
        var form = layui.form;
        var tbale = layui.table;
        var $ = layui.$;
        form.on('submit(questionEdit-filter)', function(data){
            //如果提交成功，将按钮置灰，防止反复提交
            $("#btn-submit").attr("disabled","disabled").addClass("layui-btn-disabled")
            var url = "<%=request.getContextPath()%>/fillQuestion/editQuestion";
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
