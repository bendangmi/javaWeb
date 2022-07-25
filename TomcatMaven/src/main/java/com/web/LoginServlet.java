package com.web; /**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-18:40
 */

import com.pojo.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.接受用户名和密码
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        System.out.println(username + " : " + password);


        final User user = userService.select(username, password);
        // 2.5释放资源

        // 获取字符输出流，并设置content type
        response.setContentType("text/html;charset=utf-8");
        final PrintWriter writer = response.getWriter();

        // 3.判断user != null
        if(user != null){
            // 登录成功！
            writer.write("登录成功！");
        }else{
            // 登录失败！
            writer.write("登录失败！");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
