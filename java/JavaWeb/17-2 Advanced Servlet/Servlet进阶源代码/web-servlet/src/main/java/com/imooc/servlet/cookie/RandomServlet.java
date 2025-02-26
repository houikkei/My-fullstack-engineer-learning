package com.imooc.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/cookie/random")
public class RandomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer random = new Random().nextInt(10000);
        Cookie cookie = new Cookie("random" , String.valueOf(random));
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("<h2>随机数" +  random+ "已生成</h2>");
    }
}
