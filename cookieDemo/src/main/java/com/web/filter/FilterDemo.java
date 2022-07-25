package com.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-8:07
 */

@WebFilter("/*")
public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 放行前，对request数据进行处理

        System.out.println("FilterDemo1...");

        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

        // 放行后，对Response 数据进行处理
        System.out.println("FilterDemo2...");
    }

    @Override
    public void destroy() {

    }
}
