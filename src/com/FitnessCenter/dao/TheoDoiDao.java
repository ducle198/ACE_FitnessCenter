/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.ACE_FitnessCenter.helper.JdbcHelper;
import com.FitnessCenter.entity.DichVu;
import com.FitnessCenter.entity.TheoDoi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mycomputer
 */
public class TheoDoiDao {

    public void insert(TheoDoi model) {
        String sql = "INSERT INTO TheoDoi (IDtheodoi,MAHV,CANNANG,SODOV1,SODOV2,SODOV3,GHICHU ) VALUES (?, ?, ?, ?, ?, ?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getIDtheodoi(),
                model.getMahv(),
                model.getCanNang(),
                model.getSoDov1(),
                model.getSoDov2(),
                model.getSodov3(),
                model.getGhiChu()
        );
    }

    public void Update(TheoDoi theodoi) {
        String sql = "UPDATE TheoDoi SET  CANNANG=?,SODOV1=?,SODOV2=?,SODOV3=?,GHICHU=? WHERE IDTheoDoi=?";
        JdbcHelper.executeUpdate(sql,
                theodoi.getCanNang(),
                theodoi.getSoDov1(),
                theodoi.getSoDov2(),
                theodoi.getSodov3(),
                theodoi.getGhiChu(),
                theodoi.getIDtheodoi()
        );
    }

    public void delete(int id) {
        String sql = "DELETE FROM TheoDoi WHERE MaHV=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public void deletetheoID(String id) {
        String sql = "DELETE FROM TheoDoi WHERE IDtheodoi=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    public List<TheoDoi> select() {
        String sql = "SELECT * FROM TheoDoi";
        return select(sql);
    }

    public List<TheoDoi> selecttheoID(int id) {
        String sql = "SELECT * FROM TheoDoi where MAHV=? ";
        return select(sql, id);
    }
    public List<TheoDoi> selecttheoIDtheodoi(String id) {
        String sql = "SELECT * FROM TheoDoi where IDTheoDoi=? ";
        return select(sql, id);
    }

    public List<TheoDoi> selectByKeyword(int keyword) {
        String sql = "SELECT * FROM TheoDoi WHERE MaHv LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public TheoDoi findById(int manh) {
        String sql = "SELECT * FROM TheoDoi WHERE MaHV=?";
        List<TheoDoi> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<TheoDoi> select(String sql, Object... args) {
        List<TheoDoi> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    TheoDoi model = readFromResultSet(rs);
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

    private TheoDoi readFromResultSet(ResultSet rs) throws SQLException {
        TheoDoi theodoi = new TheoDoi();
        theodoi.setIDtheodoi(rs.getString("IDtheodoi"));
        theodoi.setMahv(rs.getInt("Mahv"));
        theodoi.setCanNang(rs.getFloat("CanNang"));
        theodoi.setSoDov1(rs.getFloat("SoDov1"));
        theodoi.setSoDov2(rs.getFloat("SoDov2"));
        theodoi.setSodov3(rs.getFloat("SoDov3"));
        theodoi.setNgayTheoDoi(rs.getDate("NgayTheoDoi"));
        theodoi.setGhiChu(rs.getString("GhiChu"));
        return theodoi;
    }
}
