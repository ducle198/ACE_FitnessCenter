/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import com.FitnessCenter.entity.NhanVien;
import com.FitnessCenter.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class NhanVienDao extends ACE_FitnessCenterDao<NhanVien, String>{
    String INSERT_SQL = "INSERT INTO NHANVIEN(MANV,MATKHAU, HOTEN,GIOITINH,NGAYSINH,SDT VAITRO, CHUCVU) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NHANVIEN SET MATKHAU =?, HOTEN=?,GIOITINH=?,NGAYSINH=?,SDT=?,VAITRO=?,CHUCVU=?";
    String DELETE_SQL = "DELETE FROM NHANVIEN WHRERE MANNV = ?";
    String SELECT_ALL_SQL = "SELECT *FROM NHANVIEN";
    String SELECT_BY_ID_SQL ="SELECT *FROM NHANVIEN WHERE MANV = ?";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.update(INSERT_SQL, 
                entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(),entity.isGioiTinh(),entity.getNgaySinh(),entity.getPhoneNumber(),
                entity.isVaiTro(),entity.getChucVu());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.update(UPDATE_SQL, 
                entity.getMatKhau(), entity.getHoTen(),entity.isGioiTinh(),entity.getNgaySinh(),entity.getPhoneNumber(),
                entity.isVaiTro(),entity.getChucVu());
    }

    @Override
    public void delete(String id) {
         XJdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
       return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectByID(String id) {
        List<NhanVien> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setVaiTro(rs.getBoolean("GioiTinh"));
                nv.setMatKhau(rs.getString("NgaySinh"));
                nv.setMatKhau(rs.getString("SDT"));
                nv.setVaiTro(rs.getBoolean("VaiTro"));
                nv.setMatKhau(rs.getString("ChucVu"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }
    
    

