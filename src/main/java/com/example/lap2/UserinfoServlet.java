package com.example.lap2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet("/UserinfoServlet")
public class UserinfoServlet extends HttpServlet {
    public UserinfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");

        try {
            Connection connection = ConnectionUtils.getMySQLConnection();
            String sql = "SELECT * FROM USER_ACCOUNT WHERE CusUser = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    out.println("<html><head><title>Thông tin tài khoản</title></head><body>");
                    if (resultSet.next()) {
                        out.println("<h1>Thông tin tài khoản</h1>");
                        out.println("<p>Tên Đăng nhập : " + resultSet.getString("CusUser") + "</p>");
                        out.println("<p>Tên : " + resultSet.getString("CusName") + "</p>");
                        out.println("<p>Mã Tài khoản: " + resultSet.getString("CusID") + "</p>");
                        out.println("<p>Số điện thoại: " + resultSet.getString("CusPhone") + "</p>");
                        out.println("<p>Mật khẩu: " + resultSet.getString("CusPass") + "</p>");
                        out.println("<p>Địa chỉ: " + resultSet.getString("CusAdd") + "</p>");
                        out.println("<p>Email: " + resultSet.getString("CusEmail") + "</p>");
                        out.println("<p>Facebook: " + resultSet.getString("CusFacebook") + "</p>");
                        out.println("<p>Skyper: " + resultSet.getString("CusSkyper") + "</p>");


                    } else {
                        out.println("<h1>Tài khoản không tồn tại.</h1>");
                    }
                    out.println("</body></html>");
                }
            }

            connection.close();
        } catch (Exception ex) {
            out.println("<h1>Error: " + ex.getMessage() + "</h1>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
