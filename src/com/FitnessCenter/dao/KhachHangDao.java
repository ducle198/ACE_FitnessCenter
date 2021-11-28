/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.ACE_FitnessCenter.helper.JdbcHelper;
import com.FitnessCenter.entity.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mycomputer
 */
public class KhachHangDao extends MainDao<KhachHang, String> {
     String INSERT_SQL = "INSERT INTO KHACHHANG(MAKH,TENKH,GIOITINH,IDCARD,NGAYSINH,DIACHI,SDT,EMMAIL,HINHANH,GHICHU) VALUES (?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KHACHHANG SET TENKH = ?,GIOITINH =?,IDCARD =?, NGAYSINH =?,DIACHI = ? , SDT =?,EMMAIL = ? ,HINHANH = ?,GHICHU = ?  WHERE MAKH = ?";
    String DELETE_SQL = "DELETE KHACHHANG WHERE MAKH = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KHACHHANG";
    String SELECT_BY_ID_SQl = "SELECT * FROM KHACHHANG WHERE MAKH = ?";

    @Override
    public void insert(KhachHang entity) {
       JdbcHelper.update(INSERT_SQL , entity.getMaKH(), entity.getTenKH(),entity.getGioiTinh(), entity.getSoCCCD(), entity.getNgaysinh(), entity.getDiaChi(),entity.getSoDienThoai(), entity.getEmail(), entity.getHinhAnh(),entity.getGhiChu());
    }

    @Override
    public void update(KhachHang entity) {
        JdbcHelper.update(INSERT_SQL , entity.getMaKH(), entity.getTenKH(),entity.getGioiTinh(), entity.getSoCCCD(), entity.getNgaysinh(), entity.getDiaChi(),entity.getSoDienThoai(), entity.getEmail(), entity.getHinhAnh(),entity.getGhiChu());
    }

    @Override
    public void delete(String id) {
         JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public KhachHang selectById(String id) {
         List<KhachHang> list = this.selectBySql(SELECT_BY_ID_SQl, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectAll() {
       return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try {
            
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("MAKH"));
                entity.setTenKH(rs.getString("TENKH"));
                entity.setGioiTinh(rs.getBoolean("GIOITINH"));
                entity.setSoCCCD(rs.getString("IDCARD"));
                entity.setNgaysinh(rs.getDate("NGAYSINH"));
                entity.setDiaChi(rs.getString("DIACHI"));
                entity.setSoDienThoai(rs.getString("SODT"));
                 entity.setEmail(rs.getString("EMAIL"));
                  entity.setHinhAnh(rs.getString("HINHANH"));
                   entity.setGhiChu(rs.getString("GHICHU"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 public List<KhachHang> selectByKeyWork(String keywork) {
         String SELECT_BY_KeyWork = "SELECT * FROM KHACHHANG WHERE MAKH like '%"+keywork+"%' or TENKH like '%"+keywork+"%' or SODT like '%"+keywork+"%'";
        return this.selectBySql(SELECT_BY_KeyWork);
        
    }
 
     public  boolean checkDuplicate(String maKH) {
        KhachHang khachHang = selectById(maKH);
        if (khachHang == null) {
            return true;
        }
        return false;
    }
    
}

