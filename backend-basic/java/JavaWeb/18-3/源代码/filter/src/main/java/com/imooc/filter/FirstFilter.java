package com.imooc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter已被初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器已生效");
        //将请求响应向后传递,被后续的Filter或者Servlet进行处理
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("FirstFilter已被销毁");
    }
}
