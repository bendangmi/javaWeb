package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @code Description 演示服务器内部转发以及客户端重定向
 * @code author 本当迷
 * @code date 2022/7/19-9:48
 */

/*
* 服务器内部转发以及客户端重定向
*       1.服务器内部转发：request.getRequestDispatcher("...").forward(request, response)
*           一次响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的
*           地址栏没有变化
*       2.客户端重定向：response.sendRedirect("...")
*           两次请求响应的过程。客户端肯定知道请求URL是有变化的
*           地址栏有变化
* */
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo6 ... ");

        // 服务器端内部转发
//        req.getRequestDispatcher("demo07").forward(req, resp);

        // 客户端重定向
        resp.sendRedirect("demo07");
    }
}
