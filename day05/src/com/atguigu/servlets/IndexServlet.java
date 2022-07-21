package com.atguigu.servlets;

import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.dao.util.JDBCUtils;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int pageNo = 1;
        final HttpSession session = request.getSession();


        final String oper = request.getParameter("oper");
        // 如果oper!=null 说明 通过表单的查询按钮点击过来的
        // 如果oper是空的，说明 不是通过表单的查询按钮点击过来的

        String keyword = null;
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1 ;
            keyword = request.getParameter("keyword");
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            session.setAttribute("keyword",keyword);
        }else{
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }



        session.setAttribute("pageNo", pageNo);

        Connection conn= null;
        try {
            conn= JDBCUtils.getConnection();
            final Fruit fruit = new Fruit();
            final FruitDAOImpl dao = new FruitDAOImpl();
            final List<Fruit> list = dao.getFruitList(conn, keyword ,pageNo);
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

/*
*  Thymeleaf - 视图模板技术
* 1.添加thymeleaf 的jar包
* 2.在web.xml文件中添加配置
* 3.新建一个Servlet类
* 4.使得我们的Servlet继承ViewBaseServlet
*
*
* */