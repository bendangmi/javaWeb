package com.web.response; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-17:40
 */

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字符数据：设置字节数据的响应体
 */
@WebServlet("/resp4")
public class ResponseDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.读取文件
        final FileInputStream inputStream = new FileInputStream("C:\\Users\\14740\\Pictures\\Saved Pictures\\01.png");

        // 2.获取response字节输出流
        final ServletOutputStream outputStream = response.getOutputStream();

        IOUtils.copy(inputStream, outputStream);
        inputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
