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
    private String HanSuDung;
    private boolean TrangThai;
    private String Mota;

    public DichVu() {
    }

    public DichVu(String MaDV, String TenDV, float GiaDV, String MaCa, String HanSuDung, boolean TrangThai, String Mota) {
        this.MaDV = MaDV;
        this.TenDV = TenDV;
        this.GiaDV = GiaDV;
        this.MaCa = MaCa;
        this.HanSuDung = HanSuDung;
        this.TrangThai = TrangThai;
        this.Mota = Mota;
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

    public String getHanSuDung() {
        return HanSuDung;
    }

    public void setHanSuDung(String HanSuDung) {
        this.HanSuDung = HanSuDung;
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

    

    

    @Override
    public String toString() {
        return this.TenDV;
        
    }
    
   
}
