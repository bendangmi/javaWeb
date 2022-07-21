package com.atgui.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/20-13:10
 */
//@WebServlet(urlPatterns = "/demo01",
//        initParams = {
//                @WebInitParam(name = "hello", value = "world"),
//                @WebInitParam(name = "uname", value = "bdm")
//        }
//
//)
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        final ServletConfig config = getServletConfig();
        final String initValue = config.getInitParameter("hello");
        System.out.println("initValue = " + initValue);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
