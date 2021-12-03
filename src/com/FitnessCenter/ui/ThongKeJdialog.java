package com.FitnessCenter.ui;

import dao.khoaHocDAO;
import dao.thongKeDAO;
import helper.shareHelper;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.khoaHoc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sieu Nhan Bay
 */
public class ThongKeJdialog extends javax.swing.JInternalFrame {

    /**
     * Creates new form thongKeJInternalFrame
     */
    public ThongKeJdialog(int index) {
        initComponents();
        tabs.setSelectedIndex(index);
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlNguoiHoc = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNguoiHoc = new javax.swing.JTable();
        pnlBangDiem = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBangDiem = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboKhoaHoc = new javax.swing.JComboBox<>();
        pnlKhoaHoc = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKhoaHoc = new javax.swing.JTable();
        pnlDoanhThu = new javax.swing.JPanel();
        pnlTruongPhong = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        pnlNhanVien = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("TỔNG HỢP THỐNG KÊ");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TỔNG HỢP THỐNG KÊ");

        pnlNguoiHoc.setLayout(new java.awt.BorderLayout());

        tblNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NĂM", "SỐ NGƯỜI HỌC", "ĐẦU TIÊN", "SAU CÙNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoiHoc.setRowHeight(25);
        jScrollPane1.setViewportView(tblNguoiHoc);

        pnlNguoiHoc.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tabs.addTab("NGƯỜI HỌC", new javax.swing.ImageIcon(getClass().getResource("/icon/User group.png")), pnlNguoiHoc, "NGƯỜI HỌC"); // NOI18N

        pnlBangDiem.setLayout(new java.awt.BorderLayout());

        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MÃ NH", "HỌ VÀ TÊN", "ĐIỂM", "XẾP LOẠI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangDiem.setRowHeight(25);
        jScrollPane2.setViewportView(tblBangDiem);
        if (tblBangDiem.getColumnModel().getColumnCount() > 0) {
            tblBangDiem.getColumnModel().getColumn(1).setPreferredWidth(140);
        }

        pnlBangDiem.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Card file.png"))); // NOI18N
        jLabel2.setText("KHÓA HỌC: ");
        jPanel2.add(jLabel2, java.awt.BorderLayout.LINE_START);

        cboKhoaHoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoaHocItemStateChanged(evt);
            }
        });
        cboKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhoaHocActionPerformed(evt);
            }
        });
        cboKhoaHoc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboKhoaHocPropertyChange(evt);
            }
        });
        jPanel2.add(cboKhoaHoc, java.awt.BorderLayout.CENTER);

        pnlBangDiem.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        tabs.addTab("BẢNG ĐIỂM", new javax.swing.ImageIcon(getClass().getResource("/icon/Numbered list.png")), pnlBangDiem, "BẢNG ĐIỂM"); // NOI18N

        pnlKhoaHoc.setLayout(new java.awt.BorderLayout());

        tblKhoaHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CHUYÊN ĐỀ", "TỔNG SỐ HV", "CAO NHẤT", "THẤP NHẤT", "ĐIỂM TRUNG BÌNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhoaHoc.setRowHeight(25);
        jScrollPane3.setViewportView(tblKhoaHoc);
        if (tblKhoaHoc.getColumnModel().getColumnCount() > 0) {
            tblKhoaHoc.getColumnModel().getColumn(0).setPreferredWidth(230);
        }

        pnlKhoaHoc.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        tabs.addTab("TỔNG HỢP ĐIỂM", new javax.swing.ImageIcon(getClass().getResource("/icon/Clien list.png")), pnlKhoaHoc, "TỔNG HỢP ĐIỂM"); // NOI18N

        pnlDoanhThu.setLayout(new java.awt.CardLayout());

        pnlTruongPhong.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Date.png"))); // NOI18N
        jLabel3.setText("NĂM: ");
        jPanel1.add(jLabel3, java.awt.BorderLayout.LINE_START);

        cboNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamItemStateChanged(evt);
            }
        });
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });
        cboNam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboNamPropertyChange(evt);
            }
        });
        jPanel1.add(cboNam, java.awt.BorderLayout.CENTER);

        pnlTruongPhong.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CHUYÊN ĐỀ", "SỐ KHÓA", "SỐ HV", "DOANH THU", "HP CAO NHẤT", "HP THẤP NHẤT", "HP TRUNG BÌNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThu.setRowHeight(25);
        jScrollPane4.setViewportView(tblDoanhThu);
        if (tblDoanhThu.getColumnModel().getColumnCount() > 0) {
            tblDoanhThu.getColumnModel().getColumn(0).setPreferredWidth(210);
        }

        pnlTruongPhong.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        pnlDoanhThu.add(pnlTruongPhong, "card1");

        pnlNhanVien.setLayout(new java.awt.BorderLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Secure.png"))); // NOI18N
        jLabel4.setToolTipText("cẩn thận bị trừ lương");
        pnlNhanVien.add(jLabel4, java.awt.BorderLayout.PAGE_END);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CHỈ TRƯỞNG PHÒNG MỚI ĐƯỢC XEM DOANH THU");
        jLabel5.setToolTipText("cẩn thận bị trừ lương");
        pnlNhanVien.add(jLabel5, java.awt.BorderLayout.CENTER);

        pnlDoanhThu.add(pnlNhanVien, "card2");

        tabs.addTab("DOANH THU", new javax.swing.ImageIcon(getClass().getResource("/icon/Coins.png")), pnlDoanhThu, "DOANH THU"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    thongKeDAO dao = new thongKeDAO();
    khoaHocDAO khdao = new khoaHocDAO();

    void init() {
        setFrameIcon(shareHelper.APP_ICON_1);
        CardLayout card= (CardLayout) pnlDoanhThu.getLayout();
        if(shareHelper.USER.isVaiTro()){
            card.show(pnlDoanhThu, "card1");
        }else{
            card.show(pnlDoanhThu, "card2");
        }
    }

    //xóa cbo, lấy tất cả đt khoaHoc từ CSDL thêm mới vào cbo
    //chọn sẵn Item thứ nhất
    void fillComboBoxkhoaHoc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhoaHoc.getModel(); //kết nối cbo với model
        model.removeAllElements(); //xóa tất cả item
        List<khoaHoc> list = khdao.select();
        for (khoaHoc kh : list) {
            model.addElement(kh);
        }
        cboKhoaHoc.setSelectedIndex(0);
    }

    //lấy tất cả năm của khóa học (int) điền vào cbo (điền đt int), ko điền trùng
    //chọn sẵn Item thứ nhất
    void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
//        List<khoaHoc> list = khdao.select();
//        for (khoaHoc kh : list) {
//            int nam = kh.getNgayKG().getYear() + 1900; //date.getYear() trả về int năm của date - 1900
//            if (model.getIndexOf(nam) < 0) { //kiểm tra xem trong cbo đã có năm này chưa, nếu chưa có thì mới thêm năm
//                //model.getIndexOf(Object) trả về vị trí của object trong cbo, nêú chưa có trả về -1
//                model.addElement(nam);
//                //model.getElementAt(2);                  //chẳng để làm gì
//            }
//        }
        List<Integer> list=dao.getNamKhaiGiang();
        for(Integer nam: list){
            model.addElement(nam);
        }
        cboNam.setSelectedIndex(0);
    }

    //xóa bảng điểm, điền dữ liệu vào bảng điểm theo MaKH
    void fillTableBangDiem() {
        DefaultTableModel model = (DefaultTableModel) tblBangDiem.getModel();
        model.setRowCount(0);     
         khoaHoc kh = (khoaHoc) cboKhoaHoc.getSelectedItem();
        List<Object[]> list = dao.getBangDiem(kh.getMaKH()); //lấy về 1 <Object[]> list theo MaKH
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    //xóa bảng người học, đièm dữ liệu vào bảng người học
    void fillTableNguoiHoc() {
        DefaultTableModel model = (DefaultTableModel) tblNguoiHoc.getModel();
        model.setRowCount(0);
        List<Object[]> list = dao.getNguoiHoc();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    //xóa bảng tổng hợp điểm, điền dữ liệu vào bảng tổng hợp điểm
    void fillTablekhoaHoc() {
        DefaultTableModel model = (DefaultTableModel) tblKhoaHoc.getModel();
        model.setRowCount(0);
        List<Object[]> list = dao.getDiemTheoChuyenDe();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    //xóa bảng doanh thu, điền dữ liệu vào bảng doanh thu theo năm tương ứng
    void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> list = dao.getDoanhThu(nam);
        for (Object[] row : list) {
            model.addRow(row);
        }
    }
    /*
    khi mở JInternalFrame:
    điền dữ liệu vào cboKhoaHoc và cboNam
    điền dữ liệu vào các bảng
    */
    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        fillComboBoxkhoaHoc();
        fillTableBangDiem();
        fillTableNguoiHoc();
        fillTablekhoaHoc();
        fillComboBoxNam();
        fillTableDoanhThu();
    }//GEN-LAST:event_formInternalFrameOpened

    private void cboNamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboNamPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNamPropertyChange

    private void cboKhoaHocPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboKhoaHocPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKhoaHocPropertyChange

    private void cboKhoaHocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoaHocItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKhoaHocItemStateChanged

    private void cboNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNamItemStateChanged

    //khi thay đổi Item được chọn ở cboKhoaHoc, load lại bảng khóahọc
    private void cboKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhoaHocActionPerformed
        // TODO add your handling code here:
        fillTableBangDiem();                     //ActionPerformed
    }//GEN-LAST:event_cboKhoaHocActionPerformed

    //khi thay đổi Item được chọn ở cboNam, load lại bảng doanh thu
    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
        fillTableDoanhThu();                   //ActionPerformed
    }//GEN-LAST:event_cboNamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboKhoaHoc;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlBangDiem;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlKhoaHoc;
    private javax.swing.JPanel pnlNguoiHoc;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlTruongPhong;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblBangDiem;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblKhoaHoc;
    private javax.swing.JTable tblNguoiHoc;
    // End of variables declaration//GEN-END:variables
}
