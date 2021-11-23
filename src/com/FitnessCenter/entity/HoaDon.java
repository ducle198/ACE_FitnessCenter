/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.entity;

/**
 *
 * @author pc
 */
public class HoaDon {
    private String MaHoaDon ;
    private String MaNV ; 
    private String MaHV ;
   private String sdt;
    private double Giatien;

    public HoaDon() {
    }
    

    public HoaDon(String MaHoaDon, String MaNV, String MaHV, String sdt, double Giatien) {
        this.MaHoaDon = MaHoaDon;
        this.MaNV = MaNV;
        this.MaHV = MaHV;
        this.sdt = sdt;
        this.Giatien = Giatien;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaHV() {
        return MaHV;
    }

    public void setMaHV(String MaHV) {
        this.MaHV = MaHV;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public double getGiatien() {
        return Giatien;
    }

    public void setGiatien(double Giatien) {
        this.Giatien = Giatien;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "MaHoaDon=" + MaHoaDon + ", MaNV=" + MaNV + ", MaHV=" + MaHV + ", sdt=" + sdt + ", Giatien=" + Giatien + '}';
    }
    
    
    
    
    
}
