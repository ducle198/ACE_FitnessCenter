/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.ACE_FitnessCenter.helper.JdbcHelper;
import com.FitnessCenter.entity.DichVu;
import com.FitnessCenter.entity.HoiVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mycomputer
 */
public class HoiVienDao {

    public void insert(HoiVien model) {
        String sql = "INSERT INTO HoiVien (MaHv, MaDv, MaKh, NgayBatDauVao, trangthai, ghichu) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaHV(),
                model.getMadv(),
                model.getMaKH(),                
                model.getNgayBatDauVao(),
                model.isTrangThai(),
                model.getGhiChu()
        );
    }

    public void Update(HoiVien hoivien) {
        String sql = "UPDATE HoiVien SET MaDv=?, MaKh=?, NgayBatDauVao=?, trangthai=?, ghichu=?  WHERE MaHV=?";
        JdbcHelper.executeUpdate(sql,
                hoivien.getMadv(),
                hoivien.getMaKH(),
                hoivien.getNgayBatDauVao(),
                hoivien.isTrangThai(),
                hoivien.getGhiChu(),
                hoivien.getMaHV()
        );
    }

    public void delete(String id) {
        String sql = "DELETE FROM HoiVien WHERE MaHV=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<HoiVien> select() {
        String sql = "SELECT * FROM HoiVien";
        return select(sql);
    }

    public List<HoiVien> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HoiVien WHERE MaHV LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public HoiVien findById(String manh) {
        String sql = "SELECT * FROM HoiVien WHERE MaHV=?";
        List<HoiVien> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<HoiVien> select(String sql, Object... args) {
        List<HoiVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoiVien model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            
            throw new RuntimeException(ex);
        }
        return list;
    }

    private HoiVien readFromResultSet(ResultSet rs) throws SQLException {
        HoiVien hoivien = new HoiVien();
        hoivien.setMaHV(rs.getString("MaHV"));
        hoivien.setMadv(rs.getString("MaDV"));
        hoivien.setMaKH(rs.getString("MaKh"));
        hoivien.setNgayBatDauVao(rs.getDate("NgayBatDauVao"));
        hoivien.setTrangThai(rs.getBoolean("TrangThai"));
        hoivien.setGhiChu(rs.getString("GhiChu"));

        return hoivien;
    }
}
