package com.atguigu.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @code Description 演示Session
 * @code author 本当迷
 * @code date 2022/7/19-8:08
 */
public class Demo02Servlet extends HttpServlet{
    /*
    * 会话跟踪技术
    *       客户端第一次发请求给服务器，服务器获取session，获取不到，则会创建新的，然后响应给客户端
    *       下次客户端给服务器请求时。会把session带给服务器，那么服务器就能获取到了，那么服务器就判断这一次请求和上次请求一样
    * 常用的API：
    *       request.getSession() -> 获取当前会话，没有则创建一个新的会话
    *       request.getSession(true) -> 效果和不带参数相同
    *       request.getSession(false) -> 获取当前会话，没有则返回null,不会创建新的
    *
    *       session.getId() -> 获取sessionID
    *       session.isNew() -> 判断当前 session 是否是新的
    *       session.getMaxInactiveInterval() -> session的非激活间隔时长 默然 1800 秒
    *       session.setMaxInactiveInterval() -> 设置会话时间
    *       session.invalidate(); -> 强制会话失效
    * */

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("****************************");
        // 获取 session ,如果获取不到，则会创建一个新的
        final HttpSession session = req.getSession();
        System.out.println("session ID:" + session.getId());
        System.out.println(session.getMaxInactiveInterval());
    }

}
