package com.atguigu.servlets;

import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.dao.util.JDBCUtils;
import com.atguigu.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class AddServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET方式目前不需要设置编码（基于tomcat8）

        // POST方式下：设置编码，防止中文乱码
        // 注意的是：设置编码这一句话必须在所有的获取参数动作之前
        request.setCharacterEncoding("UTF-8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        System.out.println("fname:"+ fname);
        System.out.println("price:"+ price);
        System.out.println("fcount:"+ fcount);
        System.out.println("remark:"+ remark);

        Connection conn= null;
        try {
            conn= JDBCUtils.getConnection();
            //2. 定义SQL
            final Fruit fruit = new Fruit(0, fname, price, fcount, remark);
            final FruitDAOImpl dao = new FruitDAOImpl();
            dao.addFruit(conn, fruit);
            System.out.println("添加成功！");
        } catch (Exception e) {
            System.out.println("报错..");
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

}
