package com.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-16:22
 */

@WebServlet(urlPatterns = "/demo5")
public class HttpServletDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET 请求
        System.out.println("GET.....");

        // 1.获取所有参数的Map集合
        final Map<String, String[]> parameterMap = req.getParameterMap();
        for (String key : parameterMap.keySet()) {
            System.out.print(key + ":");
            System.out.println(Arrays.toString(parameterMap.get(key)));
        }

        // 根据key获取参数值，单个参数值
        final String password = req.getParameter("password");
        System.out.println(password);

        // 根据key获取参数值，数组
//        final String[] usernames = req.getParameterValues("username");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
