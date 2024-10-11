package com.imooc.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet("/session/random")
public class RandomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer random = new Random().nextInt(10000);
        HttpSession session = request.getSession();
        session.setAttribute("random" , random);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("<h2>随机数" +  random + "已生成</h2>");
    }
}
