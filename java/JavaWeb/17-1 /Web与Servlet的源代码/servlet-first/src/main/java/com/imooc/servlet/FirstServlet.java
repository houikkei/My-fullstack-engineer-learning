package com.imooc.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
    public FirstServlet() {
        System.out.println("正在创建FirstServlet");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化FirstServlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String html = "<h1 style='color:red'>hi," + name + "!</h1><hr/>";
        System.out.println("返回给浏览器的响应内容为:" + html);
        PrintWriter out = resp.getWriter();
        out.println(html);
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁FirstServlet");
    }
}
