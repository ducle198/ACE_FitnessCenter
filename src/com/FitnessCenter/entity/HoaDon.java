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
public class HoaDon {
    String MaHD;
    Date ngayLap;
    String maKH;
    String maNV;
    float tongTien;
    float giamGia;
    float khachDaTra;
    float conNo;

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public float getKhachDaTra() {
        return khachDaTra;
    }

    public void setKhachDaTra(float khachDaTra) {
        this.khachDaTra = khachDaTra;
    }

    public float getConNo() {
        return conNo;
    }

    public void setConNo(float conNo) {
        this.conNo = conNo;
    }
    
}
