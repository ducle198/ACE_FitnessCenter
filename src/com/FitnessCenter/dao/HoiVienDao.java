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
        String sql = "INSERT INTO HoiVien (MADV,MAKH,NGAYBATDAUVAO,TRANGTHAI) VALUES (?,?,?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMadv(),
                model.getMaKH(),
                model.getNgayBatDauVao(),
                model.isTrangThai()
        );
    }

    public void Update(HoiVien hoivien) {
        String sql = "UPDATE HoiVien SET MaDv=?, MaKh=? trangthai=?  WHERE MaHV=?";
        JdbcHelper.executeUpdate(sql,
                hoivien.getMadv(),
                hoivien.getMaKH(),
                hoivien.isTrangThai(),
                hoivien.getMaHV()
        );
    }

    public void delete(int id) {
        String sql = "DELETE FROM HoiVien WHERE MaHV=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<HoiVien> select() {
        String sql = "SELECT * FROM HoiVien";
        return select(sql);
    }

    public List<HoiVien> selectByKeyword(int keyword) {
        String sql = "SELECT * FROM HoiVien WHERE MaHV LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public HoiVien findById(int mahv) {
        String sql = "SELECT * FROM HoiVien WHERE MaHV=?";
        List<HoiVien> list = select(sql, mahv);
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
        hoivien.setMaHV(rs.getInt("MaHV"));
        hoivien.setMadv(rs.getString("MADV"));
        hoivien.setMaKH(rs.getString("MaKh"));
        hoivien.setNgayBatDauVao(rs.getDate("NgayBatDauVao"));
        hoivien.setTrangThai(rs.getBoolean("TrangThai"));
        return hoivien;
    }

     public List<HoiVien> selectByDichVu(String keyword) {
        String sql = "SELECT * FROM HoiVien WHERE MaDV LIKE ?";
        return select(sql, "%" + keyword + "%");
    }
//     public List<HoiVien> selectByKey(String maDV){
//         String sql = "SELECT *FROM HOIVIEN WHERE MAKH LIKE ? AND MADV LIKE ?";
//         return select(sql,maKH,maDV);
//     }


}
