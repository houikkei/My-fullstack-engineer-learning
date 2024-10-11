package com.imooc.filter.chain;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(filterName = "filterA" ,urlPatterns = "/*")
public class FilterA implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("I'm Filter A");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
