package com.atguigu.controllers;

import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.dao.util.JDBCUtils;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/20-6:48
 */
@WebServlet("/fruit.do")
public class FruitController extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        // 方法二
        // 获取当前类中所有的方法
        final Method[] methods = this.getClass().getDeclaredMethods();
        for(Method method: methods){
            // 获取方法名称
            final String methodName = method.getName();
            if(operate.equals(methodName)){
                try {
                    // 找到和operator同名的方法，通过反射技术调用它
                    method.invoke(this, request, response);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }

//方法一
//        switch (operate) {
//            case "index":
//                index(request, response);
//                break;
//            case "add":
//                add(request, response);
//                break;
//            case "del":
//                del(request, response);
//                break;
//            case "edit":
//                edit(request, response);
//                break;
//            case "update":
//                update(request, response);
//                break;
//
//        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        request.setCharacterEncoding("utf-8");

        // 2.获取编码
        final String fidStr = request.getParameter("fid");
        final int fid = Integer.parseInt(fidStr);
        final String fname = request.getParameter("fname");
        final String priceStr = request.getParameter("price");
        final int price = Integer.parseInt(priceStr);
        final String fcountStr = request.getParameter("fcount");
        final int fount = Integer.parseInt(fcountStr);
        final String remark = request.getParameter("remark");

        Connection conn= null;
        try {
            conn = JDBCUtils.getConnection();
            final Fruit fruit = new Fruit(fid, fname, price, fount, remark);
            final FruitDAOImpl dao = new FruitDAOImpl();
            // 3.执行更新
            dao.updateFruit(conn, fruit);
            // 4.资源跳转
//            super.processTemplate("index", request, response);

            // 此处需要重定向，目的是重新给IndexServlet发请求，重新获取fruitList,
            // 然后覆盖到session中，这样index.html页面上显示的session中的数据才是最终的
            response.sendRedirect("fruit.do");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Connection conn= null;
            try {
                conn = JDBCUtils.getConnection();
                final FruitDAOImpl dao = new FruitDAOImpl();
                final Fruit fruit = dao.getFruitByFid(conn, fid);
                System.out.println(fruit);

                req.setAttribute("fruit", fruit);
//                req.getSession().setAttribute("fruit", fruit); 错误的
                super.processTemplate("edit", req, resp);

            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtils.closeResource(conn, null);
            }

        }
    }

    private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            final int fid = Integer.parseInt(fidStr);
            Connection conn = null;
            try {
                conn = JDBCUtils.getConnection();
                final FruitDAOImpl dao = new FruitDAOImpl();
                dao.delFruit(conn, fid);

                resp.sendRedirect("fruit.do");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtils.closeResource(conn, null);
            }

        }

    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        final String fname = request.getParameter("fname");
        final String priceStr = request.getParameter("price");
        final int price = Integer.parseInt(priceStr);
        final String fcountStr = request.getParameter("fcount");
        final int fount = Integer.parseInt(fcountStr);
        final String remark = request.getParameter("remark");
        final Fruit fruit = new Fruit(0, fname, price, fount, remark);

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            final FruitDAOImpl dao = new FruitDAOImpl();
            dao.addFruit(conn, fruit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }

        // 重定向
        response.sendRedirect("fruit.do");


    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageNo = 1;
        final HttpSession session = request.getSession();


        final String oper = request.getParameter("oper");
        // 如果oper!=null 说明 通过表单的查询按钮点击过来的
        // 如果oper是空的，说明 不是通过表单的查询按钮点击过来的

        String keyword = null;
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }


        session.setAttribute("pageNo", pageNo);

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            final Fruit fruit = new Fruit();
            final FruitDAOImpl dao = new FruitDAOImpl();
            final List<Fruit> list = dao.getFruitList(conn, keyword, pageNo);
            // 保存到session作用域
            session.setAttribute("fruitList", list);

            // 此处的视图名称是 index
            // 那么thymeleaf 会将这个逻辑视图 对应 到物理视图 名称上去
            // 逻辑视图名称： index
            // 物理视图名称： view-prefix + 逻辑视图名称 + view-suffix
            // 所有真实视图名称是： /index.html
            super.processTemplate("index", request, response);

            // 总记录条数
            final Long fruitCount = dao.getFruitCount(conn, keyword);
            // 总页数
            final long pageCount = (fruitCount + 5 - 1) / 5;
            session.setAttribute("pageCount", pageCount);


        } catch (Exception e) {
            System.out.println("报错..");
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}
