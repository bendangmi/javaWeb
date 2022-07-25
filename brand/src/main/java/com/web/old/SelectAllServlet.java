package com.web.old; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-19:05
 */

import com.alibaba.fastjson.JSON;
import com.pojo.Brand;
import com.service.BrandService;
import com.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {

    private final BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.调用service查询
        final List<Brand> brands = brandService.selectAll();

        // 2.转为JSON
        final String jsonString = JSON.toJSONString(brands);

        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
