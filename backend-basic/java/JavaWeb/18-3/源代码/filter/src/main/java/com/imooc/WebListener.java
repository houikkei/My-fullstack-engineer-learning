package com.imooc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class WebListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象已创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象已销毁");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("请求已销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("请求已创建");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("会话已创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("会话已销毁");
    }
}
