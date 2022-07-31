package com.wh.controller;

import com.alibaba.fastjson.JSON;
import com.wh.bean.*;
import com.wh.service.*;
import com.wh.service.impl.*;
import com.wh.utils.Random;
import com.wh.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author whstart
 * @creat 2022--12-14:30
 */
@WebServlet("/paper/*")
public class PaperServlet extends BaseServlet{
    private PaperService service = new IPaperService();
    private MultiQuestionService multiService = new IMultiQuestionService();
    private JudgeQuestionService judgeService = new IJudgeQuestionService();
    private FillQuestionService fillService = new IFillQuestionService();
    private ScoreService scoreService = new IScoreService();
    public void getPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paperId = request.getParameter("paperId");
        String examDesc = request.getParameter("examDesc");
        String course = request.getParameter("course");
        List<Paper> list = service.getPaper(paperId);
        ArrayList multiList = new ArrayList();
        ArrayList judgeList = new ArrayList();
        ArrayList fillList = new ArrayList();
        for(Paper paper : list){
            if(paper.getQuestionType() == 1){
                //选择题
                MultiQuestion multiQuestion = multiService.selectById(paper.getQuestionId());
                multiList.add(multiQuestion);
            }else if(paper.getQuestionType() == 2){
                //判断题
                JudgeQuestion judgeQuestion = judgeService.selectById(paper.getQuestionId());
                judgeList.add(judgeQuestion);
            }else if(paper.getQuestionType() == 3){
                //填空题
                FillQuestion fillQuestion = fillService.selectById(paper.getQuestionId());
                fillList.add(fillQuestion);
            }
        }

        request.setAttribute("multiQues",multiList);
        request.setAttribute("judgeQues",judgeList);
        request.setAttribute("fillQues",fillList);
        request.setAttribute("examDesc",examDesc);
        request.setAttribute("course",course);
        request.getRequestDispatcher("/templates/student/page.jsp").forward(request,response);
    }
    //评分
    public void correct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalScore = 100;
        for(int i = 0;i < 10;i++){
            //选择题一个5分
            String userAnswer = request.getParameter("userMultiAnswer" + i);
            String realAnswer = request.getParameter("multiAnswer" + i);
            if(userAnswer != null && userAnswer != ""){
                if(!userAnswer.equals(realAnswer)){
                    totalScore -= 5;
                }
            }else{
                totalScore -= 5;
            }
            //判断题一个2分
            String userJudgeAnswer = request.getParameter("userJudgeAnswer" + i);
            String judgeAnswer = request.getParameter("judgeAnswer" + i);
            if(userJudgeAnswer != null && userJudgeAnswer != ""){
                if(!userJudgeAnswer.equals(judgeAnswer)){
                    totalScore -= 2;
                }
            }else{
                totalScore -= 2;
            }
            //填空题一个3分
            String userFillAnswer = request.getParameter("userFillAnswer" + i);
            String fillAnswer = request.getParameter("fillAnswer" + i);
            if(userFillAnswer != null && userFillAnswer != ""){
                if(!userFillAnswer.equals(fillAnswer)){
                    totalScore -= 3;
                }
            }else{
                totalScore -= 3;
            }
        }
        if(totalScore >= 60){
            request.setAttribute("scoreMsg","恭喜你，成绩合格");
        }else{
            request.setAttribute("scoreMsg","好可惜，下次加油");
        }

        //将成绩存到表里
        Cookie[] cookies = request.getCookies();
        String studentId = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("userId")){
                studentId = cookie.getValue();
            }
        }
        String answerDate = request.getParameter("answerDate");
        Score score = new Score();
        score.setStudentId(Integer.parseInt(studentId));
        String course = request.getParameter("course");
        score.setSubject(course);
        score.setAnswerDate(answerDate);
        score.setScore(totalScore);
        scoreService.addScore(score);

        request.setAttribute("totalScore",totalScore);
        request.getRequestDispatcher("/templates/student/myScore.jsp").forward(request,response);
    }
    //新增试卷
    public void addPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取新增试卷的id
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Exam exam = JSON.parseObject(s, Exam.class);
        Integer paperId = exam.getPaperId();
        //获取题目科目
        String course = exam.getCourse();

        //获取三种题型对应科目的题目id
        int[] multiQuesIds = multiService.getQuesIdByCourse(course);
        int[] judgeQuesIds = judgeService.getQuesIdByCourse(course);
        int[] fillQuesIds = fillService.getQuesIdByCourse(course);

        //这种方法虽然能随机抽取id，但是不能判断是那门课
//        Integer minId = multiService.getMinId();
//        Integer maxId = multiService.getMaxId();
//        Integer judgeMinId = judgeService.getMinId();
//        Integer judgeMaxId = judgeService.getMaxId();
//        Integer fillMinId = fillService.getMinId();
//        Integer fillMaxId = fillService.getMaxId();
        //生成选择题id数组索引的随机数
        int[] multiIds = Random.randomCommon(0, multiQuesIds.length, 10);
        for(int i = 0;i < multiIds.length;i++){
            Paper paper = new Paper();
            paper.setPaperId(paperId);
            paper.setQuestionType(1);
            paper.setQuestionId(multiQuesIds[multiIds[i]]);
            service.addPaper(paper);
        }
        //生成判断题id数组索引的随机数
        int[] judgeIds = Random.randomCommon(0, judgeQuesIds.length, 10);
        for(int i = 0;i < judgeIds.length;i++){
            Paper paper = new Paper();
            paper.setPaperId(paperId);
            paper.setQuestionType(2);
            paper.setQuestionId(judgeQuesIds[judgeIds[i]]);
            service.addPaper(paper);
        }
        //生成填空题id的随机数
        int[] fillIds = Random.randomCommon(0, fillQuesIds.length, 10);
        for(int i = 0;i < fillIds.length;i++){
            Paper paper = new Paper();
            paper.setPaperId(paperId);
            paper.setQuestionType(3);
            paper.setQuestionId(fillQuesIds[fillIds[i]]);
            service.addPaper(paper);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("已自动生成试卷")));
    }
}
