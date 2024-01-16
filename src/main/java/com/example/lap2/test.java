package com.example.lap2;

import java.sql.Connection;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Get connection ... ");
        Connection conn = ConnectionUtils.getMySQLConnection();

        System.out.println("Get connection " + conn);
        System.out.println("Done!");
    }
}
