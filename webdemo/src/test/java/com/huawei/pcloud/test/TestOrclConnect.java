package com.huawei.pcloud.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestOrclConnect {

	public static void main(String[] args) {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
            //String dbURL = "jdbc:oracle:thin:@192.168.201.122:1521:testing";
            String dbURL = "jdbc:oracle:thin:@//192.168.201.122:1521/testing";
            conn = DriverManager.getConnection(dbURL, "edi", "edi");
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
