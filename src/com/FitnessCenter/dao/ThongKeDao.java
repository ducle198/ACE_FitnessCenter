/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ACE_FitnessCenter.helper.JdbcHelper;

/**
 *
 * @author ADMIN ASUS
 */
public class ThongKeDao {

    String sql_doanhthutong_ngay_table = " select TENDV,sum(SOLUONG) as N'Số Lượng Đã Bán',sum(THANHTIEN) as N'Thành Tiền' from CTHOADON join DICHVU on CTHOADON.MADV=DICHVU.MADV join HOADON on CTHOADON.MAHD=HOADON.MAHD\n"
            + " where day(NGAYLAP)=day(getdate())\n"
            + " group by TENDV";
    String sql_doanhthutong_Thang_table = " select TENDV,sum(SOLUONG) as N'Số Lượng Đã Bán',sum(THANHTIEN) as N'Thành Tiền' from CTHOADON join DICHVU on CTHOADON.MADV=DICHVU.MADV join HOADON on CTHOADON.MAHD=HOADON.MAHD\n"
            + " where MOnth(NGAYLAP)=MOnth(getdate())\n"
            + " group by TENDV";
    String sql_doanhthutong_nam_table = " select TENDV,sum(SOLUONG) as N'Số Lượng Đã Bán',sum(THANHTIEN) as N'Thành Tiền' from CTHOADON join DICHVU on CTHOADON.MADV=DICHVU.MADV join HOADON on CTHOADON.MAHD=HOADON.MAHD\n"
            + " where year(NGAYLAP)=year(getdate())\n"
            + " group by TENDV";
String sql_doanhthutong_ngay_label=" select sum(THANHTIEN)from CTHOADON join HOADON on CTHOADON.MAHD=HOADON.MAHD where day(NGAYLAP)=day(getdate())";
   String sql_doanhthutong_thang_label=" select sum(THANHTIEN)from CTHOADON join HOADON on CTHOADON.MAHD=HOADON.MAHD where month(NGAYLAP)=month(getdate())";
String sql_doanhthutong_nam_label="select sum(THANHTIEN)from CTHOADON join HOADON on CTHOADON.MAHD=HOADON.MAHD where  year(NGAYLAP)=year(getdate())";
   
   
   
   
   
   private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Object[]> getDoanhThu_ngay_table() {

        String[] cols = {"TENDV", "Số Lượng Đã Bán", "Thành Tiền"};
        return this.getListOfArray(sql_doanhthutong_ngay_table, cols);
    }

    public List<Object[]> getDoanhThu_Thang_table() {

        String[] cols = {"TENDV", "Số Lượng Đã Bán", "Thành Tiền"};
        return this.getListOfArray(sql_doanhthutong_Thang_table, cols);
    }

    public List<Object[]> getDoanhThu_nam_table() {

        String[] cols = {"TENDV", "Số Lượng Đã Bán", "Thành Tiền"};
        return this.getListOfArray(sql_doanhthutong_nam_table, cols);
    }

    public List<Long> selectdaonhthu_ngay_label() {

        List<Long> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql_doanhthutong_ngay_label);
            while (rs.next()) {
                list.add(rs.getLong(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
      public List<Long> selectdaonhthu_Thang_label() {

        List<Long> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql_doanhthutong_thang_label);
            while (rs.next()) {
                list.add(rs.getLong(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
      public List<Long> selectdaonhthu_nam_label(){

        List<Long> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql_doanhthutong_nam_label);
            while (rs.next()) {
                list.add(rs.getLong(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
