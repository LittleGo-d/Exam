package com.wh.controller;

import com.alibaba.fastjson.JSON;
import com.wh.bean.Exam;
import com.wh.bean.JudgeQuestion;
import com.wh.service.ExamService;
import com.wh.service.PaperService;
import com.wh.service.impl.IExamService;
import com.wh.service.impl.IPaperService;
import com.wh.vo.ExamQuery;
import com.wh.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whstart
 * @creat 2022--11-17:24
 */
@WebServlet("/exam/*")
public class ExamServlet extends BaseServlet{
    private ExamService service = new IExamService();
    public void getExamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course = request.getParameter("course");
        String major = request.getParameter("major");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        ExamQuery examQuery = new ExamQuery();
        examQuery.setCourse(course);
        examQuery.setMajor(major);
        examQuery.setPage(Integer.parseInt(page));
        examQuery.setLimit(Integer.parseInt(limit));

        List<Exam> list = service.getExamList(examQuery);
        Integer count = service.countExam(examQuery);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success(list,count)));
    }
    //展示考试细节
    public void examDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("examId");
        Exam exam = service.selectById(Integer.parseInt(id));

        Cookie examId = new Cookie("examId", URLEncoder.encode(id, "utf-8"));
        Cookie description = new Cookie("description", URLEncoder.encode(exam.getDescription(), "utf-8"));
        Cookie course = new Cookie("course", URLEncoder.encode(exam.getCourse(), "utf-8"));
        Cookie paperId = new Cookie("paperId", URLEncoder.encode(exam.getPaperId().toString(), "utf-8"));
        Cookie examDate = new Cookie("examDate", URLEncoder.encode(exam.getExamDate(), "utf-8"));
        Cookie totalTime = new Cookie("totalTime", URLEncoder.encode(exam.getTotalTime().toString(), "utf8"));
        Cookie grade = new Cookie("grade", URLEncoder.encode(exam.getGrade(), "utf-8"));
        Cookie term = new Cookie("term", URLEncoder.encode(exam.getTerm(), "utf-8"));
        Cookie major = new Cookie("major", URLEncoder.encode(exam.getMajor(), "utf-8"));
        Cookie totalScore = new Cookie("totalScore", URLEncoder.encode(exam.getTotalScore().toString(), "utf-8"));

        ArrayList<Cookie> cookies = new ArrayList<>();
        cookies.add(examId);
        cookies.add(description);
        cookies.add(course);
        cookies.add(paperId);
        cookies.add(examDate);
        cookies.add(totalTime);
        cookies.add(grade);
        cookies.add(term);
        cookies.add(major);
        cookies.add(totalScore);

        for(Cookie cookie : cookies){
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 *7);
            response.addCookie(cookie);
        }

        response.sendRedirect(request.getContextPath() + "/templates/editExam.jsp");
    }
    //修改考试信息
    public void editExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        Exam exam = JSON.parseObject(s, Exam.class);
//        System.out.println(question);
        service.updateExam(exam);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("修改成功!")));
    }
    //添加考试
    public void addExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受数据
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        String text = JSON.parse(s).toString();

        Exam exam = JSON.parseObject(text, Exam.class);
        Integer paperId = exam.getPaperId();
        request.setAttribute("paperId",paperId);

        service.addExam(exam);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("添加成功!")));
    }
}
