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
 * @code date 2022/7/19-17:00
 */

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        final String fname = request.getParameter("fname");
        final String priceStr = request.getParameter("price");
        final int price = Integer.parseInt(priceStr);
        final String fcountStr = request.getParameter("fcount");
        final int fount = Integer.parseInt(fcountStr);
        final String remark = request.getParameter("remark");
        final Fruit fruit = new Fruit(0, fname, price, fount, remark);

        Connection conn= null;
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
        response.sendRedirect("index");


    }

}
