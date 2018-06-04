package com.hong.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义Servlet.
 * Created by hong on 2017/5/25.
 */
@WebServlet(name = "myServlet",urlPatterns = {"/myServlet"},description = "Servlet的说明")
public class MyServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("myServlet......doGet");

        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello World</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>大家好，我的名字叫MyServlet</h1>");
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
