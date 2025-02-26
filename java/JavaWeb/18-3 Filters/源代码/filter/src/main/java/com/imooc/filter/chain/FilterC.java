package com.imooc.filter.chain;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//filterA -> filterB
//@WebFilter(filterName = "filterC" ,urlPatterns = "/*")
public class FilterC implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("I'm Filter C");
        chain.doFilter(request, response);
        //response.getWriter().println("<h1> I'm from Filter C!</h1>");
    }

    @Override
    public void destroy() {

    }
}
