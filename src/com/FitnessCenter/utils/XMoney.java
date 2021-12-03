/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class XMoney {
   public static String DinhDangTien(double tien) {
        return NumberFormat.getNumberInstance().format(tien);
    }

    //Hàm chuyển từ chữ sang số để tính toán
    public static double ChuyenTien(String tien) {
        try {
            return NumberFormat.getNumberInstance().parse(tien).doubleValue();
        } catch (ParseException ex) {
//            GUI.frmHeThong.ThongBao("Thông báo", "Lỗi chuyển dữ liệu");

        }
        return 0;
    }

    //Hàm chuyển ngày tháng sang chữ 
    public static String DinhDangNgay(Date ngay) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        return dateFormat.format(ngay);
    }

    //Hàm chuyển chữ sang ngày tháng
    public static Date LayNgay(String ngay) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            return dateFormat.parse(ngay);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    } 
}
