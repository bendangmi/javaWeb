package com.web.response; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-17:40
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字符数据：设置字符数据的响应体
 */
@WebServlet("/resp3")
public class ResponseDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应头 解决中文数据乱码问题
        response.setContentType("text/html;charset=utf-8");
        // 1.获取字符输出流
        final PrintWriter writer = response.getWriter();

        writer.write("本当迷");

        // 注意：该流不需要关闭，随着响应结束，response对象销毁，由服务器关闭
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
