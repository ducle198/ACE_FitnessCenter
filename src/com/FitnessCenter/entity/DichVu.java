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
    private float GiaDV;
    private String MaCa; 
    private Date NgayBD;
    private Date NgayKT;
    private boolean TrangThai;
    private String Mota;

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

    public float getGiaDV() {
        return GiaDV;
    }

    public void setGiaDV(float GiaDV) {
        this.GiaDV = GiaDV;
    }

    public String getMaCa() {
        return MaCa;
    }

    public void setMaCa(String MaCa) {
        this.MaCa = MaCa;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date NgayBD) {
        this.NgayBD = NgayBD;
    }

    public Date getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(Date NgayKT) {
        this.NgayKT = NgayKT;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    public DichVu(String MaDV, String TenDV, float GiaDV, String MaCa, Date NgayBD, Date NgayKT, boolean TrangThai, String Mota) {
        this.MaDV = MaDV;
        this.TenDV = TenDV;
        this.GiaDV = GiaDV;
        this.MaCa = MaCa;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
        this.TrangThai = TrangThai;
        this.Mota = Mota;
    }

    public DichVu() {
    }
    

    

    @Override
    public String toString() {
        return this.TenDV;
        
    }
    
   
}
