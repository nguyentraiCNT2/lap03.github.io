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
import java.sql.ResultSet;
import java.sql.Statement;
@WebServlet("/SearchAccount")
public class SearchAccount extends HttpServlet {
    public SearchAccount() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String phone = req.getParameter("phone");


        try {
            out.println("<html><head><title>Search Account</title></head>");
            out.println("<body style='text-align:center'>");
            out.println("<h1>Kết quả tìm kiếm </h1>");
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
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            out.println("<h2> Danh Sách </h2>");
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
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
