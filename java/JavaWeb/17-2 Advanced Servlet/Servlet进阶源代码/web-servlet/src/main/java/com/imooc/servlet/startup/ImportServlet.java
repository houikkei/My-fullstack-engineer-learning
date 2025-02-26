package com.imooc.servlet.startup;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ImportServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        String file = config.getInitParameter("file");
        System.out.println("正在导入" + file + "文件数据...");
    }
}
