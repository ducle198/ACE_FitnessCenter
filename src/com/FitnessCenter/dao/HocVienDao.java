/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.ACE_FitnessCenter.helper.JdbcHelper;
import com.FitnessCenter.entity.HocVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mycomputer
 */
public class HocVienDao {
    public void insert(HocVien hocvien) {
        String sql = "INSERT INTO HOCVIEN (MaHV, TenHV, GioiTinh, NgaySinh, SDT, MaDV, NgayDk, NgayHetHan,GhiChu,hinh,Manv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                hocvien.getMaHV(),
                hocvien.getTenHv(),
                hocvien.isGioiTinh(),
                hocvien.getNgaySinh(),
                hocvien.getSDT(),
                hocvien.getMaDV(),
                hocvien.getNgayDK(),
                hocvien.getNgayHetHan(),
                hocvien.getGhiChu(),
                hocvien.getHinh(),
                hocvien.getMaNV()
        );
    }

    public void update(HocVien hocvien) {
        String sql = "UPDATE KhoaHoc SET tenhv=?, gioitinh=?, ngaysinh=?, sdt=?, madv=?, ngaydk=?, ngayhethan=? , ghichu=?, hinh=?, manv=? WHERE MaHV=?";
        JdbcHelper.executeUpdate(sql,
                hocvien.getMaHV(),
                hocvien.getTenHv(),
                hocvien.isGioiTinh(),
                hocvien.getNgaySinh(),
                hocvien.getSDT(),
                hocvien.getMaDV(),
                hocvien.getNgayDK(),
                hocvien.getNgayHetHan(),
                hocvien.getGhiChu(),
                hocvien.getHinh(),
                hocvien.getMaNV()
        );
    }

    public void delete(String MaHv) {
        String sql = "DELETE FROM HocVien WHERE MaHv = ?";
        JdbcHelper.executeUpdate(sql, MaHv);
    }

    private HocVien readFormResultSet(ResultSet rs) throws SQLException {
        HocVien model = new HocVien();
        model.setMaHV(rs.getString("MaHv"));
        model.setTenHv(rs.getString("TenHv"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setSDT(rs.getString("SDT"));
        model.setMaDV(rs.getString("Madv"));
        model.setNgayDK(rs.getDate("NgayDK"));
        model.setNgayHetHan(rs.getDate("Ngayhethan"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setHinh(rs.getString("Hinh"));
        model.setMaNV(rs.getString("Manv"));
        return model;
    }

    private List<HocVien> select(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HocVien hocvien = readFormResultSet(rs);
                    list.add(hocvien);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<HocVien> select() {
        String sql = "SELECT * FROM HocVien";
        return select(sql);
    }

    public HocVien findById(String makh) {
        String sql = "SELECT * FROM Hocvien WHERE Mahv=?";
        List<HocVien> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<HocVien> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Hocvien WHERE MaDV = ?";
        return select(sql, "%" + keyword + "%");
    }

    public List<HocVien> findByMaCD(String madv) {
        String sql = "Select * from Hocvien where Madv = ?";
        return select(sql, madv);
    }
}
