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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

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
   String sql_bieudongay=" select TENDV,sum(SOLUONG) as N'Số Lượng Đã Bán' from  CTHOADON join DICHVU on CTHOADON.MADV=DICHVU.MADV  join HOADON on CTHOADON.MAHD=HOADON.MAHD\n" +
" where  day(NGAYLAP)=day(getdate())\n" +
" group by TENDV";
   String sql_bieudonthang ="select TENDV,sum(SOLUONG) as N'Số Lượng Đã Bán' from  CTHOADON join DICHVU on CTHOADON.MADV=DICHVU.MADV  join HOADON on CTHOADON.MAHD=HOADON.MAHD\n" +
" where  month(NGAYLAP)=month(getdate())\n" +
" group by TENDV";
   String sql_bieudonnam =" select TENDV,sum(SOLUONG) as N'Số Lượng Đã Bán' from  CTHOADON join DICHVU on CTHOADON.MADV=DICHVU.MADV  join HOADON on CTHOADON.MAHD=HOADON.MAHD\n" +
" where  year(NGAYLAP)=year(getdate())\n" +
" group by TENDV";
   
   
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
   public void loadchartngay(JPanel jpnItem) {
         List<Thongkesp> lst= Chart1_sp_ngay();
 jpnItem.removeAll();
 jpnItem.updateUI();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        if (lst != null) {
            for (Thongkesp tk : lst) {
                dataset.addValue(tk.getSoluong(), "Dịch Vụ", tk.getTen());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng dịch vụ đã bán hôm nay".toUpperCase(),
                "Tên Dịch Vụ", "Số Lượng Đã bán",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 550));

       
        jpnItem.setLayout(new CardLayout());
        jpnItem.setBackground(Color.yellow);
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
        
    }
    private List<Thongkesp> getListOfArray_thongke_spban(String sql, String[] cols, Object... args) {
        try {
            List<Thongkesp> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Thongkesp a = new Thongkesp();
                a.setTen(rs.getString(1));
                a.setSoluong(rs.getInt(2));
                list.add(a);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     public List<Thongkesp> Chart1_sp_ngay() {

        String[] cols = {"TENDV", "Số Lượng Đã Bán"};
        return this.getListOfArray_thongke_spban(sql_bieudongay, cols);
    }
  public List<Thongkesp> Chart1_sp_Thang() {

        String[] cols = {"TENDV", "Số Lượng Đã Bán"};
        return this.getListOfArray_thongke_spban(sql_bieudonthang, cols);
    }
   public List<Thongkesp> Chart1_sp_nam() {

        String[] cols = {"TENDV", "Số Lượng Đã Bán"};
        return this.getListOfArray_thongke_spban(sql_bieudonnam, cols);
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

    public void loadchartthang(JPanel jpnItem) {
         List<Thongkesp> lst= Chart1_sp_Thang();
 jpnItem.removeAll();
 jpnItem.updateUI();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        if (lst != null) {
            for (Thongkesp tk : lst) {
                dataset.addValue(tk.getSoluong(), "Dịch Vụ", tk.getTen());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng dịch vụ đã bán tháng này".toUpperCase(),
                "Tên Dịch Vụ", "Số Lượng Đã bán",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 550));

       
        jpnItem.setLayout(new CardLayout());
        jpnItem.setBackground(Color.yellow);
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    public void loadchartnam(JPanel jpnItem) {
        List<Thongkesp> lst= Chart1_sp_nam();
 jpnItem.removeAll();
 jpnItem.updateUI();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        if (lst != null) {
            for (Thongkesp tk : lst) {
                dataset.addValue(tk.getSoluong(), "Dịch Vụ", tk.getTen());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng dịch vụ đã bán tháng này".toUpperCase(),
                "Tên Dịch Vụ", "Số Lượng Đã bán",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 550));

       
        jpnItem.setLayout(new CardLayout());
        jpnItem.setBackground(Color.yellow);
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
}
