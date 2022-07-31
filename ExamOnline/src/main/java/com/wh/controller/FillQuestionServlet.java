package com.wh.controller;

import com.alibaba.fastjson.JSON;
import com.wh.bean.FillQuestion;
import com.wh.service.FillQuestionService;
import com.wh.service.impl.IFillQuestionService;
import com.wh.vo.QuestionQuery;
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
 * @creat 2022--09-16:37
 */
@WebServlet("/fillQuestion/*")
public class FillQuestionServlet extends BaseServlet{
    private FillQuestionService service = new IFillQuestionService();
    public void getQuestionList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面获取查询条件
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        //根据条件生成query对象
        QuestionQuery questionQuery = new QuestionQuery();
        questionQuery.setSubject(subject);
        questionQuery.setContent(content);
        questionQuery.setPage(Integer.parseInt(page));
        questionQuery.setLimit(Integer.parseInt(limit));
        //根据query获得列表数据和数量
        List<FillQuestion> list = service.getQuestionList(questionQuery);
        Integer count = service.countQuestion(questionQuery);

        String s = JSON.toJSONString(Result.success(list, count));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    //删除题
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");

        service.deleteById(questionId);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("删除成功!")));
    }
    //批量删除
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("questionIds");

        int[] questionIds = JSON.parseObject(ids, int[].class);

        service.deleteByIds(questionIds);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("删除成功!")));
    }
    //展示详情
    public void questionDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("questionId");

        FillQuestion question = service.selectById(Integer.parseInt(id));

        Cookie questionId = new Cookie("questionId", URLEncoder.encode(question.getQuestionId().toString(),"utf-8"));
        Cookie subject = new Cookie("subject",URLEncoder.encode(question.getSubject(),"utf-8"));
        Cookie score = new Cookie("score",URLEncoder.encode(question.getScore().toString(),"utf-8"));
        Cookie section = new Cookie("section", URLEncoder.encode(question.getSection(),"utf-8"));
        Cookie answer = new Cookie("answer", URLEncoder.encode(question.getAnswer(),"utf-8"));
        Cookie content = new Cookie("content",URLEncoder.encode(question.getContent(),"utf-8"));
        Cookie difficulty = new Cookie("difficulty",URLEncoder.encode(question.getDifficulty().toString(),"utf-8"));
        ArrayList<Cookie> cookies = new ArrayList<>();
        cookies.add(questionId);
        cookies.add(subject);
        cookies.add(score);
        cookies.add(section);
        cookies.add(answer);
        cookies.add(content);
        cookies.add(difficulty);
        for(Cookie cookie : cookies){
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 *7);
            response.addCookie(cookie);
        }

        response.sendRedirect(request.getContextPath() + "/templates/ques/editFillQuestion.jsp");
    }
    //修改
    public void editQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        FillQuestion question = JSON.parseObject(s, FillQuestion.class);
//        System.out.println(question);
        service.updateQuestion(question);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("修改成功!")));
    }
    //跳转添加
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/templates/ques/addFillQuestion.jsp");
    }
    //添加
    public void addQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受数据
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        String text = JSON.parse(s).toString();

        FillQuestion question = JSON.parseObject(text, FillQuestion.class);

        service.addQuestion(question);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("添加成功!")));
    }
}
