package com.example.lap2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://DESKTOP-MOTUJCG\\SQLSERVER:1433;databaseName=lap2";
        String user = "sa";
        String password = "1412";

//        // Đảm bảo driver được nạp vào bộ nhớ
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException  {

        System.out.println("Get connection ... ");
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = getMySQLConnection();
        System.out.println("Get connection " + conn);
        System.out.println("Done!");
    }
}
