package com.imooc.ajax.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.ajax.entity.Channel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/channel")
public class ChannelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String level = request.getParameter("level");
        String parent = request.getParameter("parent");
        List<Channel> chlist = new ArrayList<>();
        if(level.equals("1")){
            chlist.add(new Channel("ai", "人工智能"));
            chlist.add(new Channel("web", "前端开发"));
        }else if(level.equals("2")){
            if(parent.equals("ai")){
                chlist.add(new Channel("dl", "深度学习"));
                chlist.add(new Channel("cv", "计算机视觉"));
                chlist.add(new Channel("nlp", "自然语言处理"));
            }else if(parent.equals("web")){
                chlist.add(new Channel("html", "HTML超文本标记语言"));
                chlist.add(new Channel("css", "CSS级联样式表"));
                chlist.add(new Channel("js", "JavaScript脚本"));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(chlist);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);

    }
}
