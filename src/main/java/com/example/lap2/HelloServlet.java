package com.example.lap2;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
    public HelloServlet() {
        super();
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<html>");
        out.println("<head><title>Hello Servlet</title></head>");
        out.println("<body>");
        out.println("<h1> Hello World!</h1>");
        out.println("<p> this is my first Servlet</p>");
        out.println("<p> <a  href='https://devmaster.edu.vn'>Devmaster Academy</p>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}