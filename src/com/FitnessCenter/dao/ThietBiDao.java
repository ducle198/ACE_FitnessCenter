/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.ACE_FitnessCenter.helper.JdbcHelper;
import com.FitnessCenter.entity.ThietBi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ThietBiDao {
    public void insert(ThietBi model) {
        String sql = "INSERT INTO ThieBi (MaTB, TenTB, MaNV, SoLuong, DonGia, NgayNhap, HangSX) VALUES (?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaTB(),
                model.getTenTB(),
                model.getMaNV(),
                model.getSoLuong(),
                model.getDonGia(),
                model.getNgayNhap(),
                model.getHangSX()
               
        );
    }

    public void Update(ThietBi thietbi) {
        String sql = "UPDATE ThietBi SET TenTB=?, MaNV=?, SoLuong=?, DonGia=?, NgayNhap=?, HangSX=? WHERE MaTB=?";
        JdbcHelper.executeUpdate(sql,              
                
                thietbi.getTenTB(),
                thietbi.getDonGia(),
                thietbi.getMaNV(),
                thietbi.getNgayNhap(),
                thietbi.getSoLuong(),
                thietbi.getHangSX(),
                thietbi.getMaTB()
        );
    }

    public void delete(String id) {
        String sql = "DELETE FROM ThietBi WHERE MaDV=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<ThietBi> select() {
        String sql = "SELECT * FROM ThietBi";
        return select(sql);
    }

    public List<ThietBi> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM DichVu WHERE MaTB LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public ThietBi findById(String manh) {
        String sql = "SELECT * FROM ThietBi WHERE MaTB=?";
        List<ThietBi> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<ThietBi> select(String sql, Object... args) {
        List<ThietBi> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ThietBi model = readFromResultSet(rs);
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

    private ThietBi readFromResultSet(ResultSet rs) throws SQLException {
        ThietBi thietbi = new ThietBi();
        thietbi.setMaTB(rs.getString("MaTB"));
        thietbi.setTenTB(rs.getString("TenTB"));
        thietbi.setMaNV(rs.getString("MaNV"));
        thietbi.setSoLuong(rs.getFloat("SoLuong"));
        thietbi.setDonGia(rs.getFloat("DonGia"));
        thietbi.setNgayNhap(rs.getString("NgayNhap"));
        thietbi.setHangSX(rs.getString("HangSX"));
        return thietbi;
    }
}
