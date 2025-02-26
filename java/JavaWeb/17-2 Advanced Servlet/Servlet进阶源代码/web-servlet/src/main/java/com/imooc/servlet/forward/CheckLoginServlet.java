package com.imooc.servlet.forward;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class CheckLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("admin") && password.equals("123456")){
            System.out.println("用户登录成功");
            //request.setAttribute("username",username);
            //请求转发
            //request.getRequestDispatcher("/index").forward(request,response);
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            //响应重定向
            response.sendRedirect("/index");
        }else{
            System.out.println("用户登录失败");
        }
    }
}
