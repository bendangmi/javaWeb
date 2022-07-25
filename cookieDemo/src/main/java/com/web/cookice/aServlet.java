package com.web.cookice; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-23:54
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/aServlet")
public class aServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 发生Cookie

        // 1.创建Cookie对象
        final Cookie cookie = new Cookie("username", "zs");

        // 设置存活时机 一个星期
        cookie.setMaxAge(60*60*24*7);

        // 2.发生Cookie, response
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
