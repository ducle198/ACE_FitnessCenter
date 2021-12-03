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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mycomputer
 */
public class JdbcHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=ACE_FitnessCenter2";
    private static String username = "admin";
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
    public static void update(String INSERT_SQL, String maKH, String tenKH, String diaChi, String soDienThoai) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void update(String INSERT_SQL, String maKH, String tenKH, boolean gioiTinh, String soCCCD, Date ngaysinh, String diaChi, String soDienThoai, String email, String ghiChu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void update(String INSERT_SQL, String maKH, String tenKH, boolean gioiTinh, String soCCCD, Date ngaysinh, String diaChi, String soDienThoai, String email, String hinhAnh, String ghiChu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void update(String DELETE_SQL, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ResultSet query(String sql, Object[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
