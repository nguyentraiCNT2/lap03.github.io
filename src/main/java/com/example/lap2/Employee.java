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
@WebServlet("/Employee")
public class Employee extends HttpServlet {
    public Employee() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String empNo = req.getParameter("empNo");
        String empName = req.getParameter("empName");
        String salary = req.getParameter("salary");

        try {
            out.println("<html><head><title>Search Employee</title></head>");
            out.println("<body style='text-align:center'>");
            out.println("<h1>Search Employee</h1>");
            out.println("<form action='SearchEmployee' method='post'>");
            out.println("<p> EMP_NO: <input type='text' name='empNo' value='" + (empNo != null ? empNo : "") + "'> </p>");
            out.println("<p> EMP_NAME: <input type='text' name='empName' value='" + (empName != null ? empName : "") + "'> </p>");
            out.println("<p> SALARY: <input type='text' name='salary' value='" + (salary != null ? salary : "") + "'> </p>");
            out.println("<input type='submit' value='Search'/> ");
            out.println("</form> ");

            String sql = "SELECT * FROM Employee";
            if (empNo != null && empNo.length() != 0
                    && empName != null && empName.length() != 0
                    && salary != null && salary.length() != 0) {
                sql += " WHERE EMP_NO LIKE '%" + empNo
                        + "%' AND EMP_NAME LIKE '%" + empName
                        + "%' AND SALARY =" + + Float.parseFloat(salary);;
            }


            Connection con = ConnectionUtils.getMySQLConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            out.println("<h2> Employee List </h2>");
            out.println("<table border=1 align='center' style='width:50%'> ");
            out.println("<tr><th>EMP_ID</th><th>EMP_NAME</th><th>EMP_NO</th><th>SALARY</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("EMP_ID") + "</td>");
                out.println("<td>" + rs.getString("EMP_NAME") + "</td>");
                out.println("<td>" + rs.getString("EMP_NO") + "</td>");
                out.println("<td>" + rs.getString("SALARY") + "</td>");
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
        super.doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
