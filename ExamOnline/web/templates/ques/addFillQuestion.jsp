<%--
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
    <title>增加题库</title>
    <link rel="icon" href="../../images/favicon.ico">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/public.css" media="all">
</head>
<body>
    <form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">科目</label>
        <div class="layui-input-block">
            <select name="subject" lay-verify="required">
                <option value=""></option>
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
            <input type="text" name="content" lay-verify="required" lay-reqtext="题目不能为空" placeholder="请输入题目" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">答案</label>
        <div class="layui-input-block">
            <input type="text" name="answer" lay-verify="required" lay-reqtext="答案不能为空" placeholder="请输入答案" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分值</label>
        <div class="layui-input-block">
        <select name="score" lay-verify="required">
            <option value=""></option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">难度</label>
        <div class="layui-input-block">
            <select name="difficulty" lay-verify="required">
                <option value=""></option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所属章节</label>
        <div class="layui-input-block">
            <input type="text" name="section" lay-verify="required" lay-reqtext="章节不能为空" placeholder="请输入所属章节" value="" class="layui-input">
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
        layui.use(['form','table'], function () {
            var form = layui.form,
                layer = layui.layer,
                table = layui.table,
                $ = layui.$;

            //监听提交
            form.on('submit(formDemo)', function (data) {
                console.log("监听提交")
                //如果提交成功,将按钮置灰并设置为不可用
                $("#btn-submit").attr("disabled","disabled").addClass("layui-btn-disabled");
                var url = "<%=request.getContextPath()%>/fillQuestion/addQuestion";

                var s = JSON.stringify(data.field);
                $.post(url,s,function (response) {
                    if(response.code == '0'){
                        //添加成功
                        layer.msg(response.message,{icon:1,time:2000},function (){
                            //关闭表单，刷新表格
                            parent.refreshTable();
                            //关闭新增表格
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);//再执行关闭
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
