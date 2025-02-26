package com.imooc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*

@WebFilter(filterName = "acef" , urlPatterns = "/*"
    ,initParams = {
        @WebInitParam(name="encoding" , value = "UTF-8"),
        @WebInitParam(name="p1" , value = "v1"),
        @WebInitParam(name="p2" , value = "v2")
    }
)
*/

public class AnnotationCharacterEncodingFilter implements Filter{
    private String encoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        System.out.println("Encoding:" + encoding);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset=" + encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
