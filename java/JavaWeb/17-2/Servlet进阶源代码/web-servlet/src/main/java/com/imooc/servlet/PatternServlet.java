package com.imooc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PatternServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*if(1==1){
            throw new RuntimeException("未知错误");
        }*/
        String uri = request.getRequestURI();// URI:/class/785
        String id = uri.substring(uri.lastIndexOf("/") + 1);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>");
        if(id.equals("785")){
            out.println("Vue3.0高阶实战");
        }else if(id.equals("786")){
            out.println("面霸修炼手册");
        }else{
            out.println("其他课程");
        }
        out.println("</h1>");
    }
}
