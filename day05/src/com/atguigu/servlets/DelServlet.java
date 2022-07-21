package com.atguigu.servlets;

import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.dao.util.JDBCUtils;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/19-16:37
 */

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            final int fid = Integer.parseInt(fidStr);
            Connection conn= null;
            try {
                conn = JDBCUtils.getConnection();
                final FruitDAOImpl dao = new FruitDAOImpl();
                dao.delFruit(conn, fid);

                resp.sendRedirect("index");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtils.closeResource(conn, null);
            }

        }

    }
}
