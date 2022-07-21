package com.atguigu.servlets;

import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.dao.util.JDBCUtils;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

// Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn= null;
        try {
            conn= JDBCUtils.getConnection();
            final Fruit fruit = new Fruit();
            final FruitDAOImpl dao = new FruitDAOImpl();
            final List<Fruit> list = dao.getFruitList(conn);
            // 保存到session作用域
            final HttpSession session = request.getSession();
            session.setAttribute("fruitList", list);

            // 此处的视图名称是 index
            // 那么thymeleaf 会将这个逻辑视图 对应 到物理视图 名称上去
            // 逻辑视图名称： index
            // 物理视图名称： view-prefix + 逻辑视图名称 + view-suffix
            // 所有真实视图名称是： /index.html
            super.processTemplate("index", request, response);

        } catch (Exception e) {
            System.out.println("报错..");
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

}

/*
*  Thymeleaf - 视图模板技术
* 1.添加thymeleaf 的jar包
* 2.在web.xml文件中添加配置
* 3.新建一个Servlet类
* 4.使得我们的Servlet继承ViewBaseServlet
*
*
* */