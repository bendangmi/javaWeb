package com.web.request;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/23-12:19
 */

@WebServlet(urlPatterns="/demo.do", loadOnStartup = 1)
public class ServletDemo1 implements Servlet {
    /**
     * 初始化方法：
     *      1.调用时机：默认情况下，Servlet被第一次访问时，调用
     *              loadOnStartup
     *      2.调用次数： 1次
     * @param servletConfig
     * @throws ServletException
     */
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("服务开始初始化~~");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     *  提供服务
     *          1.调用时机：每一次Servlet被访问时，调用
     *          2.调用次数：多次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet启动成功！");
    }


    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法：
     *      1.调用时机：内存释放或者服务器关闭的时候，Servlet对象会被销毁，调用
     *      2.调用次数：1次
     * @return
     */
    public void destroy() {

    }
}
