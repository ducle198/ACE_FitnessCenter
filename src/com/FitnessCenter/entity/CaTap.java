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
public class CaTap {
   private String MaCa;
    private String TenCa;
    private String GioBD;
    private String GioKT;

    public CaTap() {
    }

    public CaTap(String MaCa, String TenCa, String GioBD, String GioKT) {
        this.MaCa = MaCa;
        this.TenCa = TenCa;
        this.GioBD = GioBD;
        this.GioKT = GioKT;
    }

    public String getMaCa() {
        return MaCa;
    }

    public void setMaCa(String MaCa) {
        this.MaCa = MaCa;
    }

    public String getTenCa() {
        return TenCa;
    }

    public void setTenCa(String TenCa) {
        this.TenCa = TenCa;
    }

    public String getGioBD() {
        return GioBD;
    }

    public void setGioBD(String GioBD) {
        this.GioBD = GioBD;
    }

    public String getGioKT() {
        return GioKT;
    }

    public void setGioKT(String GioKT) {
        this.GioKT = GioKT;
    }

    @Override
    public String toString() {
        return MaCa;
    } 
}
