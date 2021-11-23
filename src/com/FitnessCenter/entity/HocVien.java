/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.entity;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class HocVien {
    private String MaHV;
    private String TenHv;
    private boolean GioiTinh;
    private Date NgaySinh;
    private String SDT;
    private String MaDV;
    private Date NgayDK;
    private Date NgayHetHan;
    private String GhiChu;
    private String Hinh;
    private String MaNV;

    @Override
    public String toString() {
        return "HocVien{" + "MaHV=" + MaHV + ", TenHv=" + TenHv + ", GioiTinh=" + GioiTinh + ", NgaySinh=" + NgaySinh + ", SDT=" + SDT + ", MaDV=" + MaDV + ", NgayDK=" + NgayDK + ", NgayHetHan=" + NgayHetHan + ", GhiChu=" + GhiChu + ", Hinh=" + Hinh + ", MaNV=" + MaNV + '}';
    }

    public String getMaHV() {
        return MaHV;
    }

    public void setMaHV(String MaHV) {
        this.MaHV = MaHV;
    }

    public String getTenHv() {
        return TenHv;
    }

    public void setTenHv(String TenHv) {
        this.TenHv = TenHv;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public Date getNgayDK() {
        return NgayDK;
    }

    public void setNgayDK(Date NgayDK) {
        this.NgayDK = NgayDK;
    }

    public Date getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(Date NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public HocVien(String MaHV, String TenHv, boolean GioiTinh, Date NgaySinh, String SDT, String MaDV, Date NgayDK, Date NgayHetHan, String GhiChu, String Hinh, String MaNV) {
        this.MaHV = MaHV;
        this.TenHv = TenHv;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.SDT = SDT;
        this.MaDV = MaDV;
        this.NgayDK = NgayDK;
        this.NgayHetHan = NgayHetHan;
        this.GhiChu = GhiChu;
        this.Hinh = Hinh;
        this.MaNV = MaNV;
    }

    public HocVien() {
    }
}
