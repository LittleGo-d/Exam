package com.wh.controller; /**
 * @author whstart
 * @creat 2022-${MOTH}-02-15:25
 */

import com.alibaba.fastjson.JSON;
import com.wh.bean.MultiQuestion;
import com.wh.bean.User;
import com.wh.service.UserService;
import com.wh.service.impl.IUserService;
import com.wh.vo.Result;
import com.wh.vo.UserQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends com.wh.controller.BaseServlet {
    private UserService service = new IUserService();
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        User _user = new User();
        _user.setUserId(Integer.parseInt(userId));
        _user.setPassword(password);

        User user = service.login(_user);

        //先判断验证码是否正确
        HttpSession session = request.getSession();
        Object checkCodeGen = session.getAttribute("checkCodeGen");
        if (!checkCodeGen.equals((checkCode))) {
            //错误
            request.setAttribute("login_msg","验证码错误");
            request.getRequestDispatcher("/templates/login.jsp").forward(request,response);
            //代码执行完毕
            return;
        }

        if(user != null){
            //登陆成功
            //判断是否勾选复选框remember
            String remember = request.getParameter("remember");

            String username = user.getUserName();
            Cookie c_username = new Cookie("username", username);
            c_username.setMaxAge(60 * 60 * 24 *7);
            c_username.setPath("/");
            response.addCookie(c_username);

            if("1".equals(remember)){
                //勾选了
                Cookie c_userId = new Cookie("userId",userId);
                Cookie c_password = new Cookie("password",password);

                c_userId.setMaxAge(60 * 60 * 24 *7);
                c_password.setMaxAge(60 * 60 * 24 *7);

                c_userId.setPath("/");
                c_password.setPath("/");

                response.addCookie(c_userId);
                response.addCookie(c_password);
            }
            session.setAttribute("user",user);
            if(user.getFlag() == 1){
                //管理员Id
                response.sendRedirect(request.getContextPath() + "/templates/ques/index.jsp");
                return;
            }else if(user.getFlag() == 2){
                //教师登录
                response.sendRedirect(request.getContextPath() + "/templates/teacher/teacherIndex.jsp");
                return;
            }else{
                //学生登录
                response.sendRedirect(request.getContextPath() + "/templates/student/studentIndex.jsp");
                return;
            }
        }else{
            //登陆失败
            request.setAttribute("login_msg","用户名或密码不正确！");
            request.getRequestDispatcher("/templates/login.jsp").forward(request,response);
        }
    }
    //退出登录
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/templates/login.jsp");
        }
    }
    //获取所有教师
    public void getTeacherList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面获取参数
        String userName = request.getParameter("userName");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        UserQuery userQuery = new UserQuery();
        userQuery.setUserName(userName);
        userQuery.setLimit(Integer.parseInt(limit));
        userQuery.setPage(Integer.parseInt(page));

        List<User> list = service.getTeacherList(userQuery);
        Integer count = service.countTeacher(userQuery);
        String s = JSON.toJSONString(Result.success(list,count));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    //获取所有学生
    public void getStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从页面获取参数
        String userName = request.getParameter("userName");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        UserQuery userQuery = new UserQuery();
        userQuery.setUserName(userName);
        userQuery.setLimit(Integer.parseInt(limit));
        userQuery.setPage(Integer.parseInt(page));

        List<User> list = service.getStudentList(userQuery);
        Integer count = service.countStudent(userQuery);
        String s = JSON.toJSONString(Result.success(list,count));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    //用户详细
    public void userDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        User user = service.selectById(userId);
        Cookie _userId = new Cookie("user_Id", URLEncoder.encode(user.getUserId().toString(),"utf-8"));
        Cookie _userName = new Cookie("userName",URLEncoder.encode(user.getUserName(),"utf-8"));
        Cookie _sex = new Cookie("sex",URLEncoder.encode(user.getSex(),"utf-8"));
        Cookie _profession = new Cookie("profession",URLEncoder.encode(user.getProfession(),"utf-8"));
        ArrayList<Cookie> cookies = new ArrayList<>();
        cookies.add(_userId);
        cookies.add(_userName);
        cookies.add(_sex);
        cookies.add(_profession);
        for(Cookie cookie : cookies){
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 *7);
            response.addCookie(cookie);
        }
        response.sendRedirect(request.getContextPath() + "/templates/editUser.jsp");
    }
    //编辑用户
    public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        User user = JSON.parseObject(s, User.class);

//        System.out.println(question);
        service.updateUser(user);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("修改成功!")));
    }
    //删除用户
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        service.deleteById(userId);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("删除成功!")));
    }
    //批量删除
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("userIds");

        int[] userIds = JSON.parseObject(ids, int[].class);

        service.deleteByIds(userIds);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success("删除成功!")));
    }
    //用户注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String sex = request.getParameter("sex");
        String profession = request.getParameter("profession");
        String password = request.getParameter("password");

        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setUserName(userName);
        user.setSex(sex);
        user.setProfession(profession);
        user.setPassword(password);

        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        Object checkCodeGen = session.getAttribute("checkCodeGen");

        if(!checkCodeGen.equals(checkCode)){
            //验证码错误
            request.setAttribute("register_msg","验证码错误!");
            request.getRequestDispatcher("/templates/register.jsp").forward(request,response);
            return;
        }

        boolean isNull = service.register(user);
        if(isNull){
            //注册成功
            request.setAttribute("register_msg","注册成功，请登录!");
            request.getRequestDispatcher("/templates/login.jsp").forward(request,response);
        }else{
            request.setAttribute("register_msg","用户已存在!");
            request.getRequestDispatcher("/templates/register.jsp").forward(request,response);
        }
    }
}
