package com.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @code Description urlPattern:可以配置多个访问路径
 * @code author 本当迷
 * @code date 2022/7/23-12:56
 */
@WebServlet(urlPatterns = {"/demo1", "/demo2"})
public class HttpServletDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("urlPattern:可以配置多个访问路径");
    }
}
