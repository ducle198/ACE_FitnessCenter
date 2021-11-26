/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.ACE_FitnessCenter.helper.JdbcHelper;
import com.FitnessCenter.entity.CaTap;
import com.FitnessCenter.entity.DichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mycomputer
 */
public class CaTapDao {
    public void insert(CaTap model) {
        String sql = "INSERT INTO CaTap (MaCa, TenCa, GioBD, GioKT) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaCa(),
                model.getTenCa(),
                model.getGioBD(),
                model.getGioKT()
                
        );
    }
    public void Update(CaTap catap) {
        String sql = "UPDATE DichVu SET TenCa=?, GioBD=?, GioKT=? WHERE MaCa=?";
        JdbcHelper.executeUpdate(sql,              
                catap.getMaCa(),
                catap.getTenCa(),
                catap.getGioBD(),
                catap.getGioKT()
                
        );
    }
 public void delete(String id) {
        String sql = "DELETE FROM Catap WHERE MaCa=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    

    public List<CaTap> select() {
        String sql = "SELECT * FROM CaTap";
        return select(sql);
    }
    public List<CaTap> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Catap WHERE Maca LIKE ?";
        return select(sql, "%" + keyword + "%");
    }
    public CaTap findById(String maca) {
        String sql = "SELECT * FROM Catap WHERE Maca=?";
        List<CaTap> list = select(sql, maca);
        return list.size() > 0 ? list.get(0) : null;
    }
    

    private List<CaTap> select(String sql, Object... args) {
        List<CaTap> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    CaTap model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    private CaTap readFromResultSet(ResultSet rs) throws SQLException {
        CaTap catap = new CaTap();
        catap.setMaCa(rs.getString("MaCa"));
        catap.setTenCa(rs.getString("TenCa"));
        catap.setGioBD(rs.getString("GioBD"));
        catap.setGioKT(rs.getString("GIOKT"));
        
        
        return catap;
    }
}
