package com.imooc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String sex = request.getParameter("sex");
        String[] specs = request.getParameterValues("spec");
        PrintWriter out = response.getWriter();
        out.println("<h1>name:" + name + "</h1>");
        out.println("<h1>mobile:" + mobile + "</h1>");
        out.println("<h1>sex:" + sex + "</h1>");
        for(String spec:specs){
            out.println("<h2>spec:" + spec + "</h2>");
        }
    }
}
