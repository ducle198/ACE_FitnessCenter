/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.FitnessCenter.entity.HoaDon;
import com.FitnessCenter.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonDao extends ACE_FitnessCenterDao<HoaDon, String>{
    String INSERT_SQL = "INSERT INTO HOADON(MAHD, NGAYLAP, MAKH, MANV, TONGTIEN, GIAMGIA, KHACHDATRA, CONGNO) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HOADON SET NGAYLAP = ?, MAKH = ?, MANV = ?, TONGTIEN = ?, GIAMGIA = ?, KHACHDATRA =?, CONGNO= ? WHERE MAHD = ?";
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
        XJdbc.update(UPDATE_SQL, 
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
                hd.setGiamGia(rs.getFloat("GiamGia"));
                hd.setKhachDaTra(rs.getFloat("KhachDaTra"));               
                hd.setConNo(rs.getFloat("CongNo"));               
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
    public List<HoaDon> selectByKH(String keyword){
        String sql = "SELECT *FROM HOADON WHERE MAHD IN (SELECT HOADON.MAHD FROM HOADON JOIN KHACHHANG ON HOADON.MAKH = KHACHHANG.MAKH WHERE TENKH LIKE ?)";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }
    public List<HoaDon> selectByNV(String keyword){
        String sql = "SELECT *FROM HOADON WHERE MAHD IN (SELECT HOADON.MAHD FROM HOADON JOIN NHANVIEN ON HOADON.MANV = NHANVIEN.MANV WHERE TENNV LIKE ?)";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }
    public static ResultSet CountSoHoaDon(String SoHoaDon) throws SQLException {
        String cauTruyVan = "select Count(*) from hoadon where MAHD like N'%" + SoHoaDon + "%'";
        return XJdbc.query(cauTruyVan);
    }
    public static ResultSet GetBySoHoaDon(String SoHoaDon) throws SQLException {
        String cauTruyVan = "select * from hoadon where MAHD = N'" + SoHoaDon + "'";
        return XJdbc.query(cauTruyVan);
    }

    
}
