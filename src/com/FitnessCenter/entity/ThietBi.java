/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.entity;

/**
 *
 * @author ADMIN
 */
public class ThietBi {
       private String MaTB;
       private String TenTB;
       private String MaNV;
       private float SoLuong;
       private float DonGia;
       private String NgayNhap;
       private String HangSX;

    public ThietBi() {
        
    }

    public ThietBi(String MaTB, String TenTB, String MaNV, float SoLuong, float DonGia, String NgayNhap, String HangSX) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MaNV = MaNV;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.NgayNhap = NgayNhap;
        this.HangSX = HangSX;
    }

    public String getMaTB() {
        return MaTB;
    }

    public void setMaTB(String MaTB) {
        this.MaTB = MaTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String TenTB) {
        this.TenTB = TenTB;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public float getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(float SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public String getHangSX() {
        return HangSX;
    }

    public void setHangSX(String HangSX) {
        this.HangSX = HangSX;
    }
       
    
       
}
