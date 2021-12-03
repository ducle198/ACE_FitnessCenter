/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.FitnessCenter.entity.KhachHang;
import com.FitnessCenter.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ADMIN
 */
public class KhachHangDao extends ACE_FitnessCenterDao<KhachHang, String>{
    String INSERT_SQL = "INSERT INTO KHACHHANG(MAKH,TENKH,GIOITINH,IDCARD,NGAYSINH,DIACHI,SDT,EMAIL,HINHANH,GHICHU) VALUES(?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KHACHHANG SET TENKH = ?, GIOITINH = ?, IDCARD = ?, NGAYSINH = ?, DIACHI = ?, SDT = ?, EMAIL = ?, HINHANH = ?, GHICHU = ? WHERE MaKH = ?";
    String DELETE_SQL = "DELETE FROM KHACHHANG WHERE MAKH = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KHACHHANG";
    String SELECT_BY_ID_SQL = "SELECT * FROM KHACHHANG WHERE MAKH = ?";

    @Override
    public void insert(KhachHang entity) {
       XJdbc.update(INSERT_SQL, 
                entity.getTenKH(), entity.isGioiTinh(), entity.getIdCard(),
                new java.sql.Date(entity.getNgaySinh().getTime()), 
                entity.getDiaChi(),entity.getSdt(), entity.getEmail(),entity.getHinhAnh(), entity.getGhiChu());
    }

    @Override
    public void update(KhachHang entity) {
        XJdbc.update(UPDATE_SQL, 
                entity.getTenKH(), entity.isGioiTinh(), entity.getIdCard(),
                new java.sql.Date(entity.getNgaySinh().getTime()), 
                entity.getDiaChi(),entity.getSdt(), entity.getEmail(),entity.getHinhAnh(), entity.getGhiChu(),entity.getMaKH());
        
    }

    @Override
    public void delete(String id) {
        XJdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<KhachHang> selectAll() {
       return this.selectBySQL(SELECT_ALL_SQL); 
    }

    @Override
    public KhachHang selectByID(String id) {
        List<KhachHang> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                KhachHang nh = new KhachHang();
                nh.setMaKH(rs.getString("MaKH"));
                nh.setTenKH(rs.getString("TENKH"));
                nh.setGioiTinh(rs.getBoolean("GioiTinh"));              
                nh.setIdCard(rs.getString("IDCARD"));              
                nh.setNgaySinh(rs.getDate("NgaySinh"));
                nh.setDiaChi(rs.getString("DIACHI"));
                nh.setSdt(rs.getString("SDT"));
                nh.setEmail(rs.getString("Email"));
                nh.setHinhAnh(rs.getString("HinhAnh"));
                nh.setGhiChu(rs.getString("GhiChu"));                            
                list.add(nh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
            
            
        }//To change body of generated methods, choose Tools | Templates.
    }
    public List<KhachHang> selectByKeyword(String keyword){
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH LIKE ?";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }

    public List<KhachHang> selectNotInCourse(int makh, String keyword){
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH LIKE ? AND "
                + "MAKH NOT IN (SELECT HOIVIEN FROM HocVien WHERE MaKH = ?)";
        return this.selectBySQL(sql, "%" + keyword + "%", makh);
    }
    
}
