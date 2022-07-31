<%@ page import="com.wh.service.impl.IPaperService" %>
<%@ page import="com.wh.service.PaperService" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/7/2
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加考试</title>
    <link rel="icon" href="../../images/favicon.ico">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/public.css" media="all">
</head>
<body>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label required">描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" lay-verify="required" lay-reqtext="描述不能为空" placeholder="请输入描述" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">课程</label>
        <div class="layui-input-block">
            <select name="course" lay-verify="required">
                <option value=""></option>
                <option value="计算机组成原理">计算机组成原理</option>
                <option value="计算机网络">计算机网络</option>
                <option value="软件测试">软件测试</option>
                <option value="计算方法">计算方法</option>
            </select>
        </div>
    </div>
<%--    试卷编号自动生成，隐藏--%>
    <%
        PaperService service = new IPaperService();
        Integer maxPaperId = service.getMaxPaperId();
    %>
    <input type="hidden" name="paperId" lay-verify="required" lay-reqtext="试卷不能为空" placeholder="请输入试卷编号" value="<%=maxPaperId+1%>" class="layui-input">
    <div class="layui-form-item">
        <label class="layui-form-label">考试日期</label>
        <div class="layui-input-block">
            <input type="text" name="examDate" lay-verify="required" lay-reqtext="日期不能为空" placeholder="请输入日期" class="layui-input" id="test1">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">考试时长</label>
        <div class="layui-input-block">
            <input type="number" name="totalTime" lay-verify="required" lay-reqtext="时长不能为空" placeholder="请输入时长" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年级</label>
        <div class="layui-input-block">
            <select name="grade">
                <option value=""></option>
                <option value="2020">2020</option>
                <option value="2021">2021</option>
                <option value="2022">2022</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">学期</label>
        <div class="layui-input-block">
            <select name="term">
                <option value=""></option>
                <option value="1">1</option>
                <option value="2">2</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">专业</label>
        <div class="layui-input-block">
            <input type="text" name="major" lay-verify="required" lay-reqtext="专业不能为空" placeholder="请输入专业" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">总分</label>
        <div class="layui-input-block">
            <input type="number" name="totalScore" lay-verify="required" lay-reqtext="总分不能为空" placeholder="请输入总分" value="" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="btn-submit" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">确认保存</button>
        </div>
    </div>
</form>
<script src="../../js/jquery.js"></script>
<script src="../../layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form','table','laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            table = layui.table,
            laydate = layui.laydate,
            $ = layui.$;

        //执行一个laydate实例
        laydate.render({
            elem: '#test1' //指定元素
        });

        //监听提交
        form.on('submit(formDemo)', function (data) {
            console.log("监听提交")
            //如果提交成功,将按钮置灰并设置为不可用
            $("#btn-submit").attr("disabled","disabled").addClass("layui-btn-disabled");
            var url = "<%=request.getContextPath()%>/exam/addExam";

            var s = JSON.stringify(data.field);
            $.post(url,s,function (response) {
                if(response.code == '0'){
                    //添加成功
                    layer.msg(response.message,{icon:1,time:2000});
                    //把试卷的id提交给addPaper
                    $.post('<%=request.getContextPath()%>/paper/addPaper',s,function (response){
                        if(response.code == '0'){
                            layer.msg(response.message,{icon:1,time:2000});
                        }
                    });
                }else{
                    layer.msg('新增失败，请联系管理员！', {
                        //新增失败，不可以关闭窗口
                        icon: 2,
                        anim: 6,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    });
                }
            })
            return false;
        });

    });
</script>
</body>
</html>
