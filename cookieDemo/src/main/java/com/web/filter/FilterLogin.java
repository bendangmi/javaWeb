package com.web.filter; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-8:21
 */

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录验证的filter
 */
@WebFilter( "/*")
public class FilterLogin implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 1.判断session中是否有user
        HttpServletRequest req = (HttpServletRequest)request;

        // 判断访问资源路径是否和登录注册相关
        String[] urls = {"/login.jsp", "/imgs/", "/css/", "/loginServlet", "/register.jsp", "/registerServlet", "/checkCodeServlet"};

        final String requestURL = req.getRequestURL().toString();

        for (String str :
                urls) {
            if(requestURL.contains(str)){
                // 放行
                chain.doFilter(request, response);
                return;
            }
        }


        final HttpSession session = req.getSession();
        final Object user = session.getAttribute("user");

        if(user != null){
            // 登录过了，放行
            chain.doFilter(request, response);
        }else{
            // 没有登录，存储提示信息，跳转到登录页面
            req.setAttribute("login_msg", "你尚未登陆！");
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }
}
