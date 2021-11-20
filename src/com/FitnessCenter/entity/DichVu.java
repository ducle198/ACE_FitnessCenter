/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class DichVu {
    
    private String MaKhoaHoc;
    private String TenKhoaHoc;
    private boolean GioiTinh;
    private float GiaThanh;
    private Date ThoiGianBatDau;
    private Date ThoiGianKetThuc;
    private String HuongDanVien;
    private String GhiChu;

    public String getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(String MaKhoaHoc) {
        this.MaKhoaHoc = MaKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String TenKhoaHoc) {
        this.TenKhoaHoc = TenKhoaHoc;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public float getGiaThanh() {
        return GiaThanh;
    }

    public void setGiaThanh(float GiaThanh) {
        this.GiaThanh = GiaThanh;
    }

    public Date getThoiGianBatDau() {
        return ThoiGianBatDau;
    }

    public void setThoiGianBatDau(Date ThoiGianBatDau) {
        this.ThoiGianBatDau = ThoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return ThoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date ThoiGianKetThuc) {
        this.ThoiGianKetThuc = ThoiGianKetThuc;
    }

    public String getHuongDanVien() {
        return HuongDanVien;
    }

    public void setHuongDanVien(String HuongDanVien) {
        this.HuongDanVien = HuongDanVien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public DichVu(String MaKhoaHoc, String TenKhoaHoc, boolean GioiTinh, float GiaThanh, Date ThoiGianBatDau, Date ThoiGianKetThuc, String HuongDanVien, String GhiChu) {
        this.MaKhoaHoc = MaKhoaHoc;
        this.TenKhoaHoc = TenKhoaHoc;
        this.GioiTinh = GioiTinh;
        this.GiaThanh = GiaThanh;
        this.ThoiGianBatDau = ThoiGianBatDau;
        this.ThoiGianKetThuc = ThoiGianKetThuc;
        this.HuongDanVien = HuongDanVien;
        this.GhiChu = GhiChu;
    }

    @Override
    public String toString() {
        return "DichVu{" + "MaKhoaHoc=" + MaKhoaHoc + ", TenKhoaHoc=" + TenKhoaHoc + ", GioiTinh=" + GioiTinh + ", GiaThanh=" + GiaThanh + ", ThoiGianBatDau=" + ThoiGianBatDau + ", ThoiGianKetThuc=" + ThoiGianKetThuc + ", HuongDanVien=" + HuongDanVien + ", GhiChu=" + GhiChu + '}';
    }
    
}
