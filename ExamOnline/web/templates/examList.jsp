<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/7/1
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>所有考试</title>
    <link rel="icon" href="../images/favicon.ico">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/layuimini.css" media="all">
    <link rel="stylesheet" href="../css/themes/default.css" media="all">
    <link rel="stylesheet" href="../lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <style>
        .layui-table-cell .layui-form-checkbox[lay-skin=primary]{
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="div-content">
    <%--    搜索表单--%>
    <form class="layui-form layui-form-pane" id="form-search">
        <fieldset class="table-search-fieldset">
            <legend>搜索条件</legend>
            <div class="layui-form-item">

                <div class="layui-inline">
                    <label class="layui-form-label">课程</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" name="course" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">专业</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" name="major" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <button id="btn-search" lay-submit lay-filter="btn-search-filter" type="button" class="layui-btn layui-btn-radius layui-btn-normal">
                        <i class="layui-icon layui-icon-search"></i>
                        搜索
                    </button>
                    <button id="btn-reset"  type="reset" class="layui-btn layui-btn-radius layui-btn-primary">
                        <i class="layui-icon layui-icon-fonts-clear"></i>
                        清空条件
                    </button>
                </div>

            </div>
        </fieldset>
    </form>
    <table id="table-exam" lay-filter="table-exam-filter"></table>

<%--    <script type="text/html" id="table-exam-toolbar">--%>
<%--        <div class="layui-btn-container">--%>
<%--            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</button>--%>
<%--        </div>--%>
<%--    </script>--%>

    <script type="text/html" id="table-column-toolbar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
<%--        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
    </script>

    <script src="../layui/layui.js" charset="utf-8"></script>
    <script src="../js/jquery.js" charset="utf-8"></script>
    <script inline="javascript">
        function refreshTable(){
            $("#btn-reset").click();
            $("#btn-search").click();
        }
        this.refreshTable();
        layui.use(['table','form'], function(){
            var table = layui.table;
            var form = layui.form;
            table.render({
                elem: '#table-exam'
                ,url:  'http://localhost:8080<%=request.getContextPath()%>/exam/getExamList' //数据接口
                ,page: true //开启分页
                ,toolbar: "#table-exam-toolbar"//开启头部工具栏
                ,limits: [5,10,30,50,100]
                ,cols: [[ //表头
                    {type: "checkbox",width: 60}
                    ,{field: 'examId', title: '考试编号',width: 100,sort: true,align: 'center'}
                    ,{field: 'description', title: '描述',align: 'center'}
                    ,{field: 'course', title: '课程', sort: true ,align: 'center'}
                    ,{field: 'examDate', title: '考试日期', sort: true ,align: 'center'}
                    ,{field: 'totalTime', title: '考试时间', sort: true ,align: 'center'}
                    ,{field: 'grade', title: '年级', sort: true ,align: 'center'}
                    ,{field: 'term', title: '学期', sort: true ,align: 'center'}
                    ,{field: 'major', title: '专业', sort: true ,align: 'center'}
                    ,{title: '操作', width: 150,toolbar: '#table-column-toolbar',align: 'center'}
                ]]
            });
            //
            form.on('submit(btn-search-filter)', function(data){
                table.reload('table-exam', {
                    where: {
                        course:data.field.course,
                        major:data.field.major
                    } //设定异步数据接口的额外参数
                    ,page:{
                        curr: 1//修改当前页为1
                    }
                });
                return false;//阻止表单跳转
            });

            //触发事件
            table.on('toolbar(table-exam-filter)', function(obj){
                <%--if(obj.event == 'delete'){--%>
                <%--    var checkStatus = table.checkStatus('table-exam'); //idTest 即为基础参数 id 对应的值--%>
                <%--    var ids = new Array();--%>
                <%--    //如果没有选中行，提示--%>
                <%--    if(checkStatus.data.length == 0){--%>
                <%--        layer.msg('您没有选中数据！', {icon: 2,anim:6});--%>
                <%--        return;--%>
                <%--    }--%>
                <%--    //获取所有选中行的问题id--%>
                <%--    $(checkStatus.data).each(function (){--%>
                <%--        ids.push(this.examId);--%>
                <%--    })--%>
                <%--    layer.confirm('您确认要删除选中的记录吗？', {icon: 3, title:'是否删除'}, function(index){--%>
                <%--        var url = "<%=request.getContextPath()%>/exam/deleteByIds";--%>
                <%--        var params = {--%>
                <%--            _method:"DELETE",--%>
                <%--            examIds: JSON.stringify(ids)--%>
                <%--        }--%>
                <%--        $.post(url,params,function (response){--%>
                <%--            if(response.code == 0){--%>
                <%--                layer.msg(response.message, {icon: 1});--%>
                <%--                //刷新表格--%>
                <%--                refreshTable();--%>
                <%--            }else{--%>
                <%--                layer.alert(response.message,{icon:2,anim:6})--%>
                <%--            }--%>
                <%--        });--%>
                <%--        layer.close(index);--%>
                <%--    });--%>

                <%--}--%>
            });

            //列工具条
            table.on('tool(table-exam-filter)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

                <%--if(layEvent === 'del'){ //删除--%>
                <%--    layer.confirm('真的删除么', function(index){--%>
                <%--        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存--%>
                <%--        layer.close(index);--%>
                <%--        //向服务端发送删除指令--%>
                <%--        var id = obj.data.examId;--%>
                <%--        idStr = id + "";--%>
                <%--        var url = "<%=request.getContextPath()%>/exam/deleteById?examId=" + idStr ;--%>
                <%--        var params = {--%>
                <%--            _method:"DELETE"--%>
                <%--        }--%>
                <%--        $.post(url,params,function (response){--%>
                <%--            if(response.code == 0){--%>
                <%--                layer.msg(response.message, {icon: 1});--%>
                <%--                //刷新表格--%>
                <%--                refreshTable();--%>
                <%--            }else{--%>
                <%--                layer.alert(response.message,{icon:2,anim:6})--%>
                <%--            }--%>
                <%--        });--%>
                <%--    });--%>
                <%--}--%>
                if(layEvent === 'edit'){ //编辑
                    layer.open({
                        type: 2,
                        title: "修改用户信息",
                        shadeClose: false,
                        area: ['80%', '80%'],
                        content: "<%=request.getContextPath()%>/exam/examDetail?examId=" + data.examId
                    });
                }
            });
        });
    </script>
</div>
</body>
</html>
