package com.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-13:28
 */
@WebServlet(urlPatterns = "/demo4")
public class HttpServletDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // String getMethod()：获取方式：GET
        final String reqMethod = req.getMethod();
        System.out.println(reqMethod);

        // String getContextPath():获取虚拟目录（访问路径）：// request-demo
        final String contextPath = req.getContextPath();
        System.out.println(contextPath);

        // StringBuffer getRequestURL():获取统一资源占位符
        final StringBuffer requestURL = req.getRequestURL();
        System.out.println(requestURL);

        // String getRequestURL():获取统一资源标识符
        final String requestURI = req.getRequestURI();
        System.out.println(requestURI);

        // String getQueryString():获取请求参数
        final String queryString = req.getQueryString();
        System.out.println(queryString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取post 请求体：请求参数

        // 1.获取字符输入流
        final BufferedReader reader = req.getReader();

        // 2.读取数据
        final String readLine = reader.readLine();
        System.out.println(readLine);
    }
}
