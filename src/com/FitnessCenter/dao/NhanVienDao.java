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
public class NhanVienDao extends ACE_FitnessCenterDao<NhanVien, String> {

    String INSERT_SQL = "INSERT INTO NHANVIEN(MANV,MATKHAU,TENNV,GIOITINH,IDCARD,NGAYSINH,DIACHI,SDT,EMAIL,CHUCVU,KINHNGHIEM,HINHANH,TRANGTHAI) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NHANVIEN SET MATKHAU =?,TENNV=?,GIOITINH=?,IDCARD=?,NGAYSINH=?,DIACHI=?,SDT=?,EMAIL=?,CHUCVU=?,KINHNGHIEM=?,HINHANH=?,TRANGTHAI=? WHERE MANV=?";
    String DELETE_SQL = "DELETE FROM NHANVIEN WHERE MANV = ?";
    String SELECT_ALL_SQL = "SELECT *FROM NHANVIEN";
    String SELECT_BY_ID_SQL = "SELECT *FROM NHANVIEN WHERE MANV = ?";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.update(INSERT_SQL,
                entity.getMaNV(), entity.getMatKhau(), entity.getTenNV(), entity.isGioiTinh(), entity.getIdCard(), entity.getNgaySinh(), entity.getDiaChi(), entity.getPhoneNumber(),
                entity.getEmail(), entity.getChucVu(), entity.getKinhNghiem(), entity.getHinhAnh(), entity.isTrangThai());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.update(UPDATE_SQL,
                entity.getMatKhau(),
                entity.getTenNV(),
                entity.isGioiTinh(),
                entity.getIdCard(),
                entity.getNgaySinh(),
                entity.getDiaChi(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getChucVu(),
                entity.getKinhNghiem(),
                entity.getHinhAnh(),
                entity.isTrangThai(),
                entity.getMaNV());
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
        if (list.isEmpty()) {
            return null;
        }
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setIdCard(rs.getString("IdCard"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setPhoneNumber(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                nv.setKinhNghiem(rs.getFloat("KinhNghiem"));
                nv.setHinhAnh(rs.getString("HinhAnh"));
                nv.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<NhanVien> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NHANVIEN WHERE MANV LIKE ?";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }

}
