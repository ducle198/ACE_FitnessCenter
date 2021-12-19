/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

/**
 *
 * @author ADMIN ASUS
 */
public class Thongkesp {
    private int soluong;
    private String ten;

    public Thongkesp() {
    }

    public Thongkesp(int soluong, String ten) {
        this.soluong = soluong;
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
    
    
}
