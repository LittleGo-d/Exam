<%@ page import="java.sql.Array" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2022/7/12
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>考试</title>
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        html,body{
            height: 100%;
            width: 100%;
        }
        .page{
            height: auto;
            width: 80%;
            margin: 20px auto;
            box-shadow: 0 0 5px;
            padding: 30px;
        }
        .multi,.judge,.fill{
            border: none;
            width: 100%;
            height: auto;
        }
        li{
            list-style-type: none;
            margin-top: 10px;
        }
        .multi-ques li{
            margin-top: 5px;
            list-style-type: none;
        }
        .title{
            display: inline-block;
            margin-left: 350px;
        }
        h2{
            margin-left: 100px;
        }
        .submit{
            margin: 0 auto;
            display: block;
            height: 40px;
            width: 100px;
            background-color: #c6d3d8;
            border: none;
            font-size: 16px;
            font-weight: 600;
        }
    </style>
</head>
<body>
<form action="<%=request.getContextPath()%>/paper/correct" method="post">
    <input type="hidden" name="answerDate" value="<%=request.getParameter("answerDate")%>">
    <input type="hidden" name="course" value="${requestScope.course}">
    <div class="page" style="padding-left: 30px;">
        <div class="title">
            <h1>${requestScope.examDesc}</h1>
            <h2>${requestScope.course}</h2>
        </div>
        <div class="multi">
            <h3>一、选择题（每题5分）</h3>
            <ol>
                <c:forEach items="${requestScope.multiQues}" var="multi">
                    <li>${requestScope.multiQues.indexOf(multi)+1}.${multi.content}</li>
<%--                    携带答案，后台直接判断--%>
                    <input type="hidden" name="multiAnswer${requestScope.multiQues.indexOf(multi)}" value="${multi.answer}"/>
                    <div class="multi-ques">
                        <li>
                            <input type="radio" name="userMultiAnswer${requestScope.multiQues.indexOf(multi)}" value="A"/>
                            <label>A.
                                    ${multi.optionA}
                            </label>
                        </li>
                        <li>
                            <input type="radio" name="userMultiAnswer${requestScope.multiQues.indexOf(multi)}" value="B"/>
                            <label>B.
                                    ${multi.optionB}
                            </label>
                        </li>
                        <li>
                            <input type="radio" name="userMultiAnswer${requestScope.multiQues.indexOf(multi)}" value="C"/>
                            <label>C.
                                    ${multi.optionC}
                            </label>
                        </li>
                        <li>
                            <input type="radio" name="userMultiAnswer${requestScope.multiQues.indexOf(multi)}" value="D"/>
                            <label>D.
                                    ${multi.optionD}
                            </label>
                        </li>
                    </div>
                </c:forEach>
            </ol>
        </div>
        <div class="judge">
            <h3>二、判断题（每题2分）</h3>
            <ol>
                <c:forEach items="${requestScope.judgeQues}" var="judge">
                    <li>${requestScope.judgeQues.indexOf(judge)+1}.${judge.content}</li>
                    <input type="hidden" name="judgeAnswer${requestScope.judgeQues.indexOf(judge)}" value="${judge.answer}"/>
                    <div class="judge-ques">
                        <li>
                            <input type="radio" name="userJudgeAnswer${requestScope.judgeQues.indexOf(judge)}" value="T"/>
                            <label>T</label>
                        </li>
                        <li>
                            <input type="radio" name="userJudgeAnswer${requestScope.judgeQues.indexOf(judge)}" value="F"/>
                            <label>F</label>
                        </li>
                    </div>
                </c:forEach>
            </ol>
        </div>
        <div class="fill">
            <h3>三、填空题（每题3分）</h3>
            <ol>
                <c:forEach items="${requestScope.fillQues}" var="fill">
                    <li>${requestScope.fillQues.indexOf(fill)+1}.${fill.content}</li>
                    <input type="hidden" name="fillAnswer${requestScope.fillQues.indexOf(fill)}" value="${fill.answer}"/>
                    <li>
                        <textarea name="userFillAnswer${requestScope.fillQues.indexOf(fill)}"></textarea>
                    </li>
                </c:forEach>
            </ol>
        </div>
        <input class="submit" name="subBtn" onclick="return checkAll()" style="margin: 0 auto;" type="submit" value="提交试卷">
    </div>
</form>
<script>
    function checkAll(){
        var flag = 1;
        //判断试卷是否作答完毕
        for(var i = 0;i < 10;i++) {
            //选择题
            var userMultiAnswer = document.getElementsByName("userMultiAnswer" + i);
            //ABCD四个选项至少勾选一个
            if (!userMultiAnswer[0].checked && !userMultiAnswer[1].checked && !userMultiAnswer[2].checked && !userMultiAnswer[3].checked) {
                flag = 0;
            }
            //判断题
            var userJudgeAnswer = document.getElementsByName("userJudgeAnswer" + i);
            //TF至少勾选一个
            if(!userJudgeAnswer[0].checked && !userJudgeAnswer[1].checked){
                flag = 0;
            }
            //填空题
            var userFillAnswer = document.getElementsByName("userFillAnswer" + i);
            //填空不能为空
            if(userFillAnswer.value == '' || userFillAnswer == null){
                flag = 0;
            }
        }

        if(flag == 0){
            if(!confirm("你还有题目未完成，确定提交试卷吗？")){
                return false;
            }
        }
    }
</script>
</body>
</html>
