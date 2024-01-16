package com.example.lap2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        out.println("<html><head><title>Login Form</title></head>");
        out.println("<body style='text-align:center;'>");
        out.println("<h1>Đăng nhập</h1>");
        out.println("<form action='LoginServlet' method='post'>");
        out.println("<p>Tài khoản: <input type='text' name='username'></p>");
        out.println("<p>Mật khẩu: <input type='password' name='password'></p>");
        out.println("<p><input type='submit' value='Đăng nhập'></p>");
        out.println("</form>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            if (username != null && password != null) {
                Connection connection = ConnectionUtils.getMySQLConnection();
                String sql = "SELECT * FROM USER_ACCOUNT WHERE CusUser = ? AND CusPass = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            resp.sendRedirect("UserinfoServlet?username=" + username);
                        } else {
                            out.println("<h1>Đăng nhập không thành công. Tài khoản hoặc mật khẩu không đúng.</h1>");
                        }
                    }
                }

                connection.close();
            } else {
                out.println("<h1>Login Failed. Invalid username or password.</h1>");
            }
        } catch (Exception ex) {
            out.println("<h1>Error: " + ex.getMessage() + "</h1>");
        }
    }



    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
