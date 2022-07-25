package com.web;

import com.alibaba.fastjson.JSON;
import com.pojo.Brand;
import com.pojo.PageBean;
import com.service.BrandService;
import com.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-20:35
 */

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private final BrandService brandService = new BrandServiceImpl();


    /**
     * 查询所有数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectAll");
        // 1.调用service查询
        final List<Brand> brands = brandService.selectAll();

        // 2.转为JSON
        final String jsonString = JSON.toJSONString(brands);

        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("add");
        // 1.接受品牌数据
        final BufferedReader reader = request.getReader();

        final String edit = request.getParameter("edit");
        System.out.println(edit);

        // JSON数据
        final String params = reader.readLine();

        // 转为Brand对象
        final Brand brand = JSON.parseObject(params, Brand.class);

        System.out.println(brand);

        if(edit.equals("false")){
            // 2.调用service添加
            brandService.add(brand);
        }else{
            brandService.update(brand);
        }
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deleteIdS");
        // 1.接受品牌数据
        final BufferedReader reader = request.getReader();

        // JSON数据
        final String params = reader.readLine();

        // 转为Brand对象
        final int[] ints = JSON.parseObject(params, int[].class);

        System.out.println(Arrays.toString(ints));

        // 2.调用service添加
        brandService.deleteByIds(ints);

        // 3.响应成功的信息
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接受 当前页码 和 每页展示条数
        final int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        final int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        // 2.调用service查询
        final PageBean<Brand> brandPageBean = brandService.selectByPage(currentPage, pageSize);


        // 2.转为JSON
        final String jsonString = JSON.toJSONString(brandPageBean);

        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("开始查询！——————————————————————————");
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);


        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        System.out.println(pageBean);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
