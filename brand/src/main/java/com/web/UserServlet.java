package com.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-20:35
 */

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
