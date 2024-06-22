package com.imooc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SampleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.处理请求中的用户数据
        String name = req.getParameter("name");
        String mobile = req.getParameter("mobile");
        //2.通过响应返回结果
        PrintWriter out = resp.getWriter();
        out.println("<h1>name:" + name + "</h1>");
        out.println("<h1>mobile:" + mobile + "</h1>");
    }
}
