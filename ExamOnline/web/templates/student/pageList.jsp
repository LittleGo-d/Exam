<%@ page import="com.wh.service.impl.IExamService" %>
<%@ page import="com.wh.vo.ExamQuery" %>
<%@ page import="com.wh.bean.Exam" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/7/12
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="div" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    IExamService service = new IExamService();
    List<Exam> examList = service.AllExam();
    request.setAttribute("exams",examList);
%>

<input type="button" name="Submit" value="返回上一页" onclick="javascript:window.history.back(-1);">

<div style="display: flex;flex-wrap: wrap;width: 80%;margin: 0 auto;justify-content: space-between;">
    <c:forEach items="${exams}" var="exam">
        <div class="page" style="background-color: skyblue;height: 200px;width: 200px;display: flex;margin-left: 20px;margin-top: 20px;justify-content: space-around;">
            <ul style="line-height: 30px;">
                <li>${exam.description}</li>
                <li>${exam.course}</li>
                <li>${exam.examDate}</li>
                <li><a href="<%=request.getContextPath()%>/paper/getPaper?paperId=${exam.paperId}&examDesc=${exam.description}&course=${exam.course}&answerDate=${exam.examDate}">进入考试</a></li>
            </ul>
        </div>
    </c:forEach>
</div>
</body>
</html>
