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
public class TheoDoi {
    private String IDtheodoi;
    private String Mahv;
    private float CanNang;
    private float SoDov1;
    private float SoDov2;
    private float Sodov3;
    private Date NgayTheoDoi;
    private String GhiChu;

    public TheoDoi() {
    }

    public TheoDoi(String IDtheodoi, String Mahv, float CanNang, float SoDov1, float SoDov2, float Sodov3, Date NgayTheoDoi, String GhiChu) {
        this.IDtheodoi = IDtheodoi;
        this.Mahv = Mahv;
        this.CanNang = CanNang;
        this.SoDov1 = SoDov1;
        this.SoDov2 = SoDov2;
        this.Sodov3 = Sodov3;
        this.NgayTheoDoi = NgayTheoDoi;
        this.GhiChu = GhiChu;
    }

    public String getIDtheodoi() {
        return IDtheodoi;
    }

    public void setIDtheodoi(String IDtheodoi) {
        this.IDtheodoi = IDtheodoi;
    }

    public String getMahv() {
        return Mahv;
    }

    public void setMahv(String Mahv) {
        this.Mahv = Mahv;
    }

    public float getCanNang() {
        return CanNang;
    }

    public void setCanNang(float CanNang) {
        this.CanNang = CanNang;
    }

    public float getSoDov1() {
        return SoDov1;
    }

    public void setSoDov1(float SoDov1) {
        this.SoDov1 = SoDov1;
    }

    public float getSoDov2() {
        return SoDov2;
    }

    public void setSoDov2(float SoDov2) {
        this.SoDov2 = SoDov2;
    }

    public float getSodov3() {
        return Sodov3;
    }

    public void setSodov3(float Sodov3) {
        this.Sodov3 = Sodov3;
    }

    public Date getNgayTheoDoi() {
        return NgayTheoDoi;
    }

    public void setNgayTheoDoi(Date NgayTheoDoi) {
        this.NgayTheoDoi = NgayTheoDoi;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    @Override
    public String toString() {
        return "TheoDoi{" + "IDtheodoi=" + IDtheodoi + ", Mahv=" + Mahv + ", CanNang=" + CanNang + ", SoDov1=" + SoDov1 + ", SoDov2=" + SoDov2 + ", Sodov3=" + Sodov3 + ", NgayTheoDoi=" + NgayTheoDoi + ", GhiChu=" + GhiChu + '}';
    }

    
    
}
