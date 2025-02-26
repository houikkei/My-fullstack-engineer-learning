package com.imooc.filter;

import javax.servlet.*;
import java.io.IOException;

public class UrlPatternFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("UrlPatternFilter已生效");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
