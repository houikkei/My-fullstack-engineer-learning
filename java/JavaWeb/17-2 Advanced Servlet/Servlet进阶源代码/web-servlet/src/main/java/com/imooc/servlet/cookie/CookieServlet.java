package com.imooc.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie/show")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Integer random = -1;
        response.setContentType("text/html;charset=utf-8");
        if(cookies == null){
            response.getWriter().println("未包含任何Cookie数据");
            return;
        }
        for (Cookie c : cookies){
            if(c.getName().equals("random")){
                random = Integer.parseInt(c.getValue());
            }
        }

        response.getWriter().println("name=random的cookie值为:" + random);
    }
}
