/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.FitnessCenter.entity.HoaDon;
import com.FitnessCenter.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonDao extends ACE_FitnessCenterDao<HoaDon, String>{
    String INSERT_SQL = "INSERT INTO HOADON(MAHD, NGAYLAP, MAKH, MANV, TONGTIEN, GIAMGIA, KHACHDATRA, TRALAI) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HOADON SET NGALAP = ?, MAKH = ?, MANV = ?, TONGTIEN = ?, GIAMGIA = ?, KHACHDATRA =?, TRALAI= ? WHERE MAHD = ?";
    String DELETE_SQL = "DELETE FROM HOADON WHERE MAHD = ?";
    String SELECT_ALL_SQL = "SELECT *FROM HOADON";
    String SELECT_BY_ID_SQL = "SELECT * FROM HOADON WHERE MAHD = ?";
    @Override
    public void insert(HoaDon entity) {
        XJdbc.update(INSERT_SQL, 
                entity.getMaHD(), entity.getNgayLap(), entity.getMaKH(), entity.getMaNV(), entity.getTongTien(), entity.getGiamGia(),entity.getKhachDaTra(),entity.getConNo());
    }

    @Override
    public void update(HoaDon entity) {
        XJdbc.update(INSERT_SQL, 
                entity.getNgayLap(), entity.getMaKH(), entity.getMaNV(), entity.getTongTien(), entity.getGiamGia(),entity.getKhachDaTra(),entity.getConNo(),entity.getMaHD());
    }

    @Override
    public void delete(String id) {
         XJdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<HoaDon> selectAll() {
         return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public HoaDon selectByID(String id) {
       List<HoaDon> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setTongTien(rs.getFloat("TongTien"));
                hd.setGiamGia(rs.getFloat("TongTien"));
                hd.setKhachDaTra(rs.getFloat("KhachDaTra"));               
                hd.setConNo(rs.getFloat("Tralai"));               
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<HoaDon> selectByKeyword(String keyword){
        String sql = "SELECT * FROM HOADON WHERE MAHD LIKE ?";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }

    
}
