package com.web.old; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-19:32
 */

import com.alibaba.fastjson.JSON;
import com.pojo.Brand;
import com.service.BrandService;
import com.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private final BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接受品牌数据
        final BufferedReader reader = request.getReader();

        // JSON数据
        final String params = reader.readLine();

        // 转为Brand对象
        final Brand brand = JSON.parseObject(params, Brand.class);

        System.out.println(brand);

        // 2.调用service添加
        brandService.add(brand);

        // 3.响应成功的信息
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
