package com.web.session; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-0:18
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/demo1")
public class SessionDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 存储到Session对象
        // 1.获取Session对象
        final HttpSession session = request.getSession();

        // 2.存储数据
        session.setAttribute("username", "bdm");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
