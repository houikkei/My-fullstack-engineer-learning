package com.imooc.servlet.encoding;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/encoding/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //在Tomcat 8以后,默认对GET请求发来的参数视为UTF-8编码进行解析
        //在Tomcat 8以前,默认为ISO-8859-1,需要在server.xml配置字符集
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(keyword);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //利用UTF-8编码解析请求体中的请求参数
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(keyword);
    }
}
