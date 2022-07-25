package com.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-13:14
 */
@WebServlet(urlPatterns = "/demo3")
public class HttpServletDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 使用request对象，获取请求数据
        final String name = req.getParameter("name");

        // 使用response对象，设置响应数据
        resp.setHeader("content-type", "text/html;charset=utf-8");
        resp.getWriter().write("<h1>"+name+",欢迎你！</h1>");
    }
}
