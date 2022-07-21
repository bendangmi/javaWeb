package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/19-9:17
 */
public class Demo04Servlet extends HttpServlet {
    // 演示向HttpSession保存数据

    /*
    * session 作用域
    *   session保存作用域和具体的某一个session对应的
    *   常用API：
    *       void session.setAttribute(k, v)
    *       Object session.getAttribute(k)
    *       void removeAttribute(k)
    *
    * */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("uname", "lina");
    }
}
