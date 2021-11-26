/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.ACE_FitnessCenter.helper.JdbcHelper;
import com.FitnessCenter.entity.DichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mycomputer
 */
public class DichVuDao {

    public void insert(DichVu model) {
        String sql = "INSERT INTO DichVu (MaDV, TenDV, GiaDV, Maca, NgayBD, NgayKT, TrangThai, Mota) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaDV(),
                model.getTenDV(),
                model.getGiaDV(),
                model.getMaCa(),
                model.getNgayBD(),
                model.getNgayKT(),
                model.isTrangThai(),
                model.getMota()
        );
    }

    public void Update(DichVu dichvu) {
        String sql = "UPDATE DichVu SET TenDV=?, GiaDV=?, Maca=?, NgayBd=?, NgayKT=?, TrangThai=?, mota=? WHERE MaDV=?";
        JdbcHelper.executeUpdate(sql,              
                
                dichvu.getTenDV(),
                dichvu.getGiaDV(),
                dichvu.getMaCa(),
                dichvu.getNgayBD(),
                dichvu.getNgayKT(),
                dichvu.isTrangThai(),
                dichvu.getMota(),
                dichvu.getMaDV()
        );
    }

    public void delete(String id) {
        String sql = "DELETE FROM DichVu WHERE MaDV=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<DichVu> select() {
        String sql = "SELECT * FROM DichVu";
        return select(sql);
    }

    public List<DichVu> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM DichVu WHERE Madv LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public DichVu findById(String manh) {
        String sql = "SELECT * FROM DichVu WHERE MaDV=?";
        List<DichVu> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<DichVu> select(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DichVu model = readFromResultSet(rs);
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

    private DichVu readFromResultSet(ResultSet rs) throws SQLException {
        DichVu dichvu = new DichVu();
        dichvu.setMaDV(rs.getString("MaDV"));
        dichvu.setTenDV(rs.getString("TenDV"));
        dichvu.setGiaDV(rs.getFloat("GiaDV"));
        dichvu.setMaCa(rs.getString("MaCa"));
        dichvu.setNgayBD(rs.getDate("NgayBd"));
        dichvu.setNgayKT(rs.getDate("NgayKT"));
        dichvu.setTrangThai(rs.getBoolean("TrangThai"));
        dichvu.setMota(rs.getString("Mota"));
        
        return dichvu;
    }
}
