<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/7/1
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>教师主页</title>
    <link rel="icon" href="../../images/logo.png">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/layuimini.css" media="all">
    <link rel="stylesheet" href="../../css/themes/default.css" media="all">
    <link rel="stylesheet" href="../../lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <style id="layuimini-bg-color">
    </style>
    <style>
        .layui-nav .layui-nav-item .layui-nav-more{
            top: 20px;
        }
        .layuimini-header-content .layui-nav .layui-nav-item .layui-nav-more{
            top: 0;
            position: relative;
        }
    </style>
</head>
<body class="layui-layout-body layuimini-all">
<%
    Cookie[] cookies = request.getCookies();
    String username = "";

    if(cookies != null){
        for(Cookie cookie : cookies){
            if("username".equals(cookie.getName())){
                username = cookie.getValue();
            }
        }
    }
%>
<div class="layui-layout layui-layout-admin">

    <div class="layui-header header">
        <div class="layui-logo layuimini-logo"></div>

        <div class="layuimini-header-content">
            <a>
                <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
            </a>

            <!--电脑端头部菜单-->
            <ul class="layui-nav layui-layout-left layuimini-header-menu layuimini-menu-header-pc layuimini-pc-show">
            </ul>

            <!--手机端头部菜单-->
            <ul class="layui-nav layui-layout-left layuimini-header-menu layuimini-mobile-show">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-list-ul"></i> 选择模块&nbsp;&nbsp;</a>
                    <dl class="layui-nav-child layuimini-menu-header-mobile">
                    </dl>
                </li>
            </ul>

            <ul class="layui-nav layui-layout-right">

                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" data-refresh="刷新"><i class="fa fa-refresh"></i></a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" data-clear="清理" class="layuimini-clear"><i class="fa fa-trash-o"></i></a>
                </li>
                <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                    <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
                </li>
                <li class="layui-nav-item layuimini-setting">
                    <a href="javascript:;"><span><%=username%>&nbsp;&nbsp;</span></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" layuimini-content-href="page/user-setting.html" data-title="基本资料" data-icon="fa fa-gears">基本资料<span class="layui-badge-dot"></span></a>
                        </dd>
                        <dd>
                            <a href="javascript:;" layuimini-content-href="page/user-password.html" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                        </dd>
                        <dd>
                            <hr>
                        </dd>
                        <dd>
                            <a href="javascript:;" class="login-out">退出登录</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layuimini-select-bgcolor" lay-unselect>
                    <a href="javascript:;" data-bgcolor="配色方案"><i class="fa fa-ellipsis-v"></i></a>
                </li>
            </ul>
        </div>
    </div>

    <!--无限极左侧菜单-->
    <div class="layui-side layui-bg-black layuimini-menu-left">
    </div>

    <!--初始化加载层-->
    <div class="layuimini-loader">
        <div class="layuimini-loader-inner"></div>
    </div>

    <!--手机端遮罩层-->
    <div class="layuimini-make"></div>

    <!-- 移动导航 -->
    <div class="layuimini-site-mobile"><i class="layui-icon"></i></div>

    <div class="layui-body">

        <div class="layuimini-tab layui-tab-rollTool layui-tab" lay-filter="layuiminiTab" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id=""></li>
            </ul>
            <div class="layui-tab-control">
                <li class="layuimini-tab-roll-left layui-icon layui-icon-left"></li>
                <li class="layuimini-tab-roll-right layui-icon layui-icon-right"></li>
                <li class="layui-tab-tool layui-icon layui-icon-down">
                    <ul class="layui-nav close-box">
                        <li class="layui-nav-item">
                            <a href="javascript:;"><span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">
                                <dd><a href="javascript:;" layuimini-tab-close="current">关 闭 当 前</a></dd>
                                <dd><a href="javascript:;" layuimini-tab-close="other">关 闭 其 他</a></dd>
                                <dd><a href="javascript:;" layuimini-tab-close="all">关 闭 全 部</a></dd>
                            </dl>
                        </li>
                    </ul>
                </li>
            </div>
            <div class="layui-tab-content">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show"></div>
            </div>
        </div>

    </div>
</div>
<script src="../../layui/layui.js" charset="utf-8"></script>
<script src="../../js/lay-config.js" charset="utf-8"></script>
<script inline="javascript">
    layui.use(['jquery', 'layer', 'miniAdmin','miniTongji'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            miniAdmin = layui.miniAdmin,
            miniTongji = layui.miniTongji;

        var options = {
            iniUrl: "../../api/teacherInit.json",    // 初始化接口
            clearUrl: "../../api/clear.json", // 缓存清理接口
            urlHashLocation: true,      // 是否打开hash定位
            bgColorDefault: false,      // 主题默认配置
            multiModule: true,          // 是否开启多模块
            menuChildOpen: false,       // 是否默认展开菜单
            loadingTime: 0,             // 初始化加载时间
            pageAnim: true,             // iframe窗口动画
            maxTabNum: 20,              // 最大的tab打开数量
        };
        miniAdmin.render(options);

        // 百度统计代码，只统计指定域名
        miniTongji.render({
            specific: true,
            domains: [
                '99php.cn',
                'layuimini.99php.cn',
                'layuimini-onepage.99php.cn',
            ],
        });

        $('.login-out').on("click", function () {
            layer.confirm('确定要退出吗', function(index){
                layer.close(index);
                window.location = "<%=request.getContextPath()%>/user/exit"
            });
        });
    });
</script>
</body>
</html>
