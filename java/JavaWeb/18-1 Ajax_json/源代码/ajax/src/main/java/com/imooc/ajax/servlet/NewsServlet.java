package com.imooc.ajax.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.ajax.entity.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String t = request.getParameter("t");
        List<News> list = new ArrayList<>();
        if(t.equals("tiobe")) {
            list.add(new News("TIOBE:2018年编程语言排行趋势", "2018-5-1", "TIOBE", "..."));
            list.add(new News("TIOBE:2019年编程语言排行趋势", "2019-5-1", "TIOBE", "..."));
            list.add(new News("TIOBE:2020年编程语言排行趋势", "2020-5-1", "TIOBE", "..."));
            list.add(new News("TIOBE:2021年编程语言排行趋势", "2021-5-1", "TIOBE", "..."));
        }else if(t.equals("pypl")){
            list.add(new News("PYPL:2018年编程语言排行趋势", "2018-5-1", "PYPL", "..."));
            list.add(new News("PYPL:2019年编程语言排行趋势", "2019-5-1", "PYPL", "..."));
            list.add(new News("PYPL:2020年编程语言排行趋势", "2020-5-1", "PYPL", "..."));
            list.add(new News("PYPL:2021年编程语言排行趋势", "2021-5-1", "PYPL", "..."));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);
        response.setContentType("text/json;charset=utf-8");
//        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
