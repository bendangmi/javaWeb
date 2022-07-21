package com.atguigu.servlets;

import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.dao.util.JDBCUtils;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/19-15:38
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            response.sendRedirect("index");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }

    }
}
