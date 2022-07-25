package com.web.response; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-17:40
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/resp1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("resp1...");

        // 重定向
        // 1.设置响应状态码
        response.setStatus(302);
        // 2.设置响应头 Location

        // 3.动态获取虚拟目录
        final String contextPath = request.getContextPath();
        response.setHeader("Location",contextPath + "/resp2");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
