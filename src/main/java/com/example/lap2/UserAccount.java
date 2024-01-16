package com.example.lap2;

import com.oracle.webservices.internal.api.message.ContentType;

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

import static com.sun.deploy.net.HttpRequest.CONTENT_TYPE;
@WebServlet("/UserAccount")
public class UserAccount extends HttpServlet {
    private Connection connection;
    private static final long serialVersionUID = 1L;

    public UserAccount() {
        super();
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String phone = req.getParameter("phone");


        try {
            out.println("<html><head><title>Search Account</title></head>");
            out.println("<body style='text-align:center'>");
            out.println("<h1>Demo tra cứu bằng Servlet </h1>");
            out.println("<form action='SearchAccount' method='post'>");
            out.println("<p> Input Phone:");
            out.println("<input type='text' name='phone' value='" + (phone != null ? phone : "") + "'> ");
            out.println("</p><input type='submit' value='Search'/> ");
            out.println("</form> ");
            String sql = "SELECT * FROM USER_ACCOUNT";
            if (phone != null && phone.length() != 0) {
                sql += " WHERE CusPhone like '%" + phone + "%'";
            }
            Connection con = ConnectionUtils.getMySQLConnection();
          String sussces = "Get connection "+ con;
            System.out.println("Get connection " + con);
            System.out.println("Done!");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            out.println("<h2> Danh Sách "+sussces+"</h2>");
            out.println("<table border=1 align='center' style = '100%'> ");
            out.println("<tr><th>User</th><th>Name</th><th>Phone</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("CusUser") + "</td>");
                out.println("<td>" + rs.getString("CusName") + "</td>");
                out.println("<td>" + rs.getString("CusPhone") + "</td>");
                out.println("</tr>");
            }
            rs.close();
            out.println("</table>");

        } catch (Exception ex) {
            out.println("<h1> " + ex.getMessage() + "</h1>");
            out.println("<p> " + ex.toString() + "</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    @Override
    public void destroy() {
        try {
            // Đóng kết nối khi Servlet bị hủy
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu cần
        }
    }
}
