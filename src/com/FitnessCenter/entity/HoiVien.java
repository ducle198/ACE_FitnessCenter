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
    private int MaHV;
    private String Madv;
    private String MaKH;
    private Date NgayBatDauVao;
    private boolean TrangThai;

    public int getMaHV() {
        return MaHV;
    }

    public void setMaHV(int MaHV) {
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

     
}
