package com.wh.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author whstart
 * @creat 2022--02-14:57
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //判断资源是否与登录或注册有关,有关则放行
        //先获取这类资源
        String urls[] = {
                "/css/",
                "/images/",
                "/templates/login.jsp",
                "/templates/register.jsp",
                "checkCodeServlet",
                "user/login",
                "user/register"
        };
        //获取当前访问的路径
        String url = request.getRequestURL().toString();

        for(String u : urls){
            if(url.contains(u)){
                //放行
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }

        //1. 判断session中是否有user
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //2. 判断user是否为null
        if(user != null){
            // 登录过了
            //放行
            filterChain.doFilter(request, response);
        }else {
            // 没有登陆，存储提示信息，跳转到登录页面
            request.setAttribute("login_msg","您尚未登录！");
            request.getRequestDispatcher("/templates/login.jsp").forward(request,response);
        }

    }

    @Override
    public void destroy() {
    }
}
