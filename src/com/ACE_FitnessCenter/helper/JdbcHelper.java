/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACE_FitnessCenter.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mycomputer
 */
public class JdbcHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=ACE_FitnessCenter2";
    private static String username = "sa";
    private static String password = "123";

    ;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(dburl, username, password);
        PreparedStatement statment = null;
        if (sql.trim().startsWith("{")) {
            statment = connection.prepareCall(sql);
        } else {
            statment = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            statment.setObject(i + 1, args[i]);
        }
        return statment;
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement statement = preparedStatement(sql, args);
            try {
                statement.executeUpdate();
            } finally {
                statement.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement statement = preparedStatement(sql, args);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
