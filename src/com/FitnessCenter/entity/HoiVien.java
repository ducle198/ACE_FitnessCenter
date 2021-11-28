/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.entity;

import java.util.Date;

/**
 *
 * @author Mycomputer
 */
public class HoiVien {
    private String MaHV;
    private String Madv;
    private String MaKH;
    private Date NgayBatDauVao;
    private boolean TrangThai;
    private String GhiChu;

    public HoiVien() {
    }

    public HoiVien(String MaHV, String Madv, String MaKH, Date NgayBatDauVao, boolean TrangThai, String GhiChu) {
        this.MaHV = MaHV;
        this.Madv = Madv;
        this.MaKH = MaKH;
        this.NgayBatDauVao = NgayBatDauVao;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
    }

    public String getMaHV() {
        return MaHV;
    }

    public void setMaHV(String MaHV) {
        this.MaHV = MaHV;
    }

    public String getMadv() {
        return Madv;
    }

    public void setMadv(String Madv) {
        this.Madv = Madv;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public Date getNgayBatDauVao() {
        return NgayBatDauVao;
    }

    public void setNgayBatDauVao(Date NgayBatDauVao) {
        this.NgayBatDauVao = NgayBatDauVao;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    @Override
    public String toString() {
        return "HoiVien{" + "MaHV=" + MaHV + ", Madv=" + Madv + ", MaKH=" + MaKH + ", NgayBatDauVao=" + NgayBatDauVao + ", TrangThai=" + TrangThai + ", GhiChu=" + GhiChu + '}';
    }

   

  
}
