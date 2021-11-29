/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.FitnessCenter.entity.CTHoaDon;
import com.FitnessCenter.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CTHoaDonDAO extends ACE_FitnessCenterDao<CTHoaDon, String> {
    String INSERT_SQL = "INSERT INTO CTHOADON(MACTHD, MAHD, MADV, SOLUONG, DONGIA, GIAMGIA,THANHTIEN, GHICHU) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE CTHOADON SET MAHD = ?, SOLUONG = ?, DONGIA = ?, GIAMGIA = ?, THANHTIEN = ? WHERE MaHD = ? AND MADV = ?";
    String DELETE_SQL = "DELETE FROM CTHOADON WHERE MACTHD = ?";
    String SELECT_ALL_SQL = "SELECT * FROM CTHOADON";
    String SELECT_BY_ID_SQL = "SELECT * FROM CTHOADON WHERE MAHD = ?";
    @Override
    public void insert(CTHoaDon entity) {
        XJdbc.update(INSERT_SQL, 
                entity.getMaHD(), entity.getMaDV(), entity.getSoLuong(),  
                entity.getDonGia(), entity.getGiamGia(),entity.getThanhTien(), entity.getGhiChu());
    }

    @Override
    public void update(CTHoaDon entity) {
        XJdbc.update(UPDATE_SQL, 
                 entity.getSoLuong(),  
                entity.getDonGia(), entity.getGiamGia(),entity.getThanhTien(), entity.getGhiChu(),entity.getMaHD(), entity.getMaDV());
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<CTHoaDon> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public CTHoaDon selectByID(String id) {
        List<CTHoaDon> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<CTHoaDon> selectBySQL(String sql, Object... args) {
       List<CTHoaDon> list = new ArrayList<CTHoaDon>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                CTHoaDon hd = new CTHoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaDV(rs.getString("MaDV"));
                hd.setSoLuong(rs.getInt("SOLUONG"));
                hd.setDonGia(rs.getFloat("DONGiA"));
                hd.setGiamGia(rs.getFloat("GIAMGIA"));
                hd.setThanhTien(rs.getFloat("THANHTIEN"));
                hd.setGhiChu(rs.getString("GHICHU"));                                              
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
