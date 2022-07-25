package com.web.request;
/**
 * @code Description 中文乱码问题解决方案
 * @code author 本当迷
 * @code date 2022/7/23-16:49
 */

import javax.net.ssl.StandardConstants;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet("/demo6")
public class HttpServletDemo6 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决POST乱码问题
//        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");


        // GET:获取参数方式：getQueryString 注意：GET和 POST 乱码通用
        // 乱码原因：tomcat进行URL解码，默认的字符集ISO-8859-1
        // 1.先对乱码数据进行编码：转换为字节数组
        final byte[] bytes = username.getBytes(StandardCharsets.ISO_8859_1);
        // 2.字节数组解码
        username = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(username);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
