package com.imooc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeviceAdapterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI(); // /index.html -> /mobile/index.html /  /desktop/index.html
        String userAgent = req.getHeader("user-agent");
        String targetUri = null;
        if(uri.startsWith("/desktop") || uri.startsWith("/mobile")){
            chain.doFilter(request, response);
        }else {
            if (userAgent.toLowerCase().indexOf("android") != -1 || userAgent.toLowerCase().indexOf("iphone") != -1) {
                targetUri = "/mobile" + uri; //  /mobile/index.html
                System.out.println("移动端设备正在访问,重新跳转URI:" + targetUri);
            } else {
                targetUri = "/desktop" + uri;
                System.out.println("PC端设备正在访问,重新跳转URI:" + targetUri);
            }
            resp.sendRedirect(targetUri);
        }
    }

    @Override
    public void destroy() {

    }
}
