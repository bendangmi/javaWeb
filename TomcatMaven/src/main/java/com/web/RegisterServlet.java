package com.web; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-20:13
 */

import com.mapper.UserMapper;
import com.pojo.User;
import com.service.UserService;
import com.util.sqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("UTF-8");

        // 1.接受对象
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        // 封装用户对象
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        final User user1 = userService.selectByUsername(username);

        // 3.判断用户对象是否为null
        if(user1 == null){
            // 用户名不存在，添加用户
            userService.add(user);
        }else{
            // 用户名存在, 给出提示信息
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户已经存在！");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
