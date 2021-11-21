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
    
    private String MaDV;
    private String TenDV;
    private float DonGia;
    private String MoTa;
    private String MaNV;
    private boolean TinhTrang;

    public DichVu(String MaDV, String TenDV, float DonGia, String MoTa, String MaNV, boolean TinhTrang) {
        this.MaDV = MaDV;
        this.TenDV = TenDV;
        this.DonGia = DonGia;
        this.MoTa = MoTa;
        this.MaNV = MaNV;
        this.TinhTrang = TinhTrang;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public boolean isTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(boolean TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    

    public DichVu() {
    }
    

    

    @Override
    public String toString() {
        return this.TenDV;
        
    }
    
   
}
