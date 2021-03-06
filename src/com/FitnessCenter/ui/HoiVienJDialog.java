/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.ui;

import com.FitnessCenter.dao.DichVuDao;
import com.FitnessCenter.dao.HoiVienDao;
import com.FitnessCenter.dao.KhachHangDao;
import com.FitnessCenter.entity.DichVu;
import com.FitnessCenter.entity.HoiVien;
import com.FitnessCenter.entity.KhachHang;
import com.FitnessCenter.utils.MsgBox;
import com.FitnessCenter.utils.XDate;
import com.FitnessCenter.utils.XMoney;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class HoiVienJDialog extends javax.swing.JDialog {
    DefaultTableModel mode = new DefaultTableModel();

    int index;
    DichVuDao dvDao = new DichVuDao();
    HoiVienDao hvdao = new HoiVienDao();
    KhachHangDao khdao = new KhachHangDao();
        
    /**
     * Creates new form HoiVienJDialog
     */
    public HoiVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    void init(){
        setLocationRelativeTo(null);
        filComboboxDichVu();
    }
    void filComboboxDichVu(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDichVu.getModel();
        model.removeAllElements();
        List<DichVu> list = dvDao.select();
        for (DichVu dv : list) {
            model.addElement(dv);
        }
         fillTableHoiVien();
    }
    void fillTableHoiVien(){
       DefaultTableModel model = (DefaultTableModel) tblHoiVien.getModel();
        model.setRowCount(0);
        DichVu dichvu = (DichVu) cboDichVu.getSelectedItem();        
        if (dichvu != null) {
            List<HoiVien> list = hvdao.selectByDichVu(dichvu.getMaDV());
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                HoiVien hv = list.get(i);
                String hoten = khdao.selectByID(hv.getMaKH()).getTenKH();
                model.addRow(new Object[]{i + 1, hv.getMaHV(), hv.getMaKH(), hoten,XDate.toString(hv.getNgayBatDauVao()),hv.isTrangThai() ? "T???m d???ng" : "Ho???t ?????ng"});
            }
            fillTableKhachHang();
        } 
    }
    void fillTableKhachHang(){
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0); // xoa tat ca cac hang
        try {
            String keyword = txtTimKiem.getText();
            List<KhachHang> list = khdao.selectByKeyword(keyword); //truy v???n ng?????i h???c c?? t??n ch???a t??? kh??a t??m ki???m
            for (KhachHang kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.isGioiTinh() ? "Nam" : "N???",
                    XDate.toString(kh.getNgaySinh()),                  
                    kh.getSdt(),
                    kh.getEmail(),
                    kh.getGhiChu()};
                model.addRow(row); //them 1 hang vao table
            }
        } catch (Exception e) {
            MsgBox.alert(this, "L???i truy v???n d??? li???u!");
        } 
    }
    void addHoivien(){
        try {
            DichVu dichvu = (DichVu) cboDichVu.getSelectedItem();
            for(int row : tblKhachHang.getSelectedRows()){
                HoiVien hv = new HoiVien();
                hv.setMadv(dichvu.getMaDV());
                hv.setMaKH((String) tblKhachHang.getValueAt(row, 0));
                hv.setNgayBatDauVao(XDate.now());
                hv.setTrangThai(true);
                hvdao.insert(hv); 
            }
            fillTableHoiVien();
            MsgBox.alert(this, "Th??m h???i vi??n th??nh c??ng!");
            tabs.setSelectedIndex(1);
        } catch (Exception e) {
        }
    }
    void removeHoivien(){
        try {
            if (MsgBox.confirm(this, "B???n c?? mu???n x??a c??c h???i vi??n ???????c ch???n?")) {
                    for (int row : tblHoiVien.getSelectedRows()) {
                        int mahv = (Integer) tblHoiVien.getValueAt(row, 1);
                        hvdao.delete(mahv);
                    }
                    fillTableHoiVien();
                    MsgBox.alert(this, "X??a h???c vi??n th??nh c??ng!");
                }
        } catch (Exception e) {
            e.printStackTrace();
             }
        }
    void updateTrangThai() {
        try {
            DichVu dichvu = (DichVu) cboDichVu.getSelectedItem();
            for (int i = 0; i < tblHoiVien.getRowCount(); i++) {
                int mahv = (Integer) tblHoiVien.getValueAt(i, 1);
                String makh = (String) tblHoiVien.getValueAt(i, 2);
                String ngaybd = tblHoiVien.getValueAt(i, 4).toString();
                boolean trangthai = tblHoiVien.getValueAt(i, 4).toString().equals("Ho???t ?????ng") ? false :true;
                HoiVien hv = hvdao.findById(mahv);
                hv.setMaHV(mahv);
                hv.setMadv(dichvu.getMaDV());
                hv.setMaKH(makh);
                hv.setNgayBatDauVao(XDate.toDate(ngaybd));
                hv.setTrangThai(trangthai);               
                hvdao.Update(hv);
            }
            fillTableHoiVien();
            MsgBox.alert(this, "C???p nh???t tr???ng th??i th??nh c??ng!");
        } catch (Exception e) {
            MsgBox.alert(this, "C???p nh???t tr???ng th??i th???t b???i!");
            e.printStackTrace();
        }

    }
    public boolean validateForm(boolean chk){
        DichVu dichvu = (DichVu) cboDichVu.getSelectedItem();
        List<HoiVien> list = hvdao.selectByDichVu(dichvu.getMaDV());
        int row = tblKhachHang.getSelectedRow();
        String makh = (String) tblKhachHang.getValueAt(row,0);
        /*Check Kh??ch h??ng ???? ????ng k?? h???i vi??n*/
        if (chk) {
            for (HoiVien cd : list) {
                if (makh.equals(cd.getMaKH())) {
                    MsgBox.alert(this, "Kh??ch h??ng ???? l?? h???i vi??n");                    
                    return false;
                }
            }
        }
        /*Check Kh??ch h??ng ???? mua d???ch v???*/
        List<KhachHang> list2 = khdao.selectNotInCourse(dichvu.getMaDV());
        if(chk){
            for (KhachHang kh : list2) {
                if (makh.equals(kh.getMaKH())) {
                    MsgBox.alert(this, "Kh??ch h??ng ch??a mua d???ch v???");                    
                    return false;
                }
            }
        }
        return true;
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
        jPanel1 = new javax.swing.JPanel();
        cboDichVu = new javax.swing.JComboBox<>();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btnThemHoiVien = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoiVien = new javax.swing.JTable();
        btnTheoDoi = new javax.swing.JButton();
        btnXoaHV = new javax.swing.JButton();
        btnCapNhatTT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("QU???N L?? H???I VI??N");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "D???ch v???", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        cboDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDichVuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(cboDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cboDichVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "T??m ki???m", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? kh??ch h??ng", "H??? t??n kh??ch h??ng", "Gi???i t??nh", "Ng??y sinh", "SDT", "Email", "Ghi ch??"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        btnThemHoiVien.setText("Th??m h???i vi??n");
        btnThemHoiVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoiVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnThemHoiVien)
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(btnThemHoiVien)
                .addGap(25, 25, 25))
        );

        tabs.addTab("Danh s??ch kh??ch h??ng", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        tblHoiVien.setBackground(new java.awt.Color(0, 204, 204));
        tblHoiVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "M?? h???i vi??n", "M?? kh??ch h??ng", "H??? v?? t??n", "Ng??y b???t ?????u t???p", "Tr???ng th??i"
            }
        ));
        jScrollPane2.setViewportView(tblHoiVien);

        btnTheoDoi.setText("Theo d??i h???i vi??n");
        btnTheoDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTheoDoiActionPerformed(evt);
            }
        });

        btnXoaHV.setText("X??a h???i vi??n");
        btnXoaHV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHVActionPerformed(evt);
            }
        });

        btnCapNhatTT.setText("C???p nh???t tr???ng th??i");
        btnCapNhatTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTheoDoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 437, Short.MAX_VALUE)
                .addComponent(btnXoaHV)
                .addGap(40, 40, 40)
                .addComponent(btnCapNhatTT)
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTheoDoi)
                    .addComponent(btnXoaHV)
                    .addComponent(btnCapNhatTT))
                .addGap(26, 26, 26))
        );

        tabs.addTab("H???i vi??n", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(tabs, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
        fillTableKhachHang();
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void cboDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDichVuActionPerformed
        // TODO add your handling code here:
        fillTableHoiVien();
    }//GEN-LAST:event_cboDichVuActionPerformed

    private void btnThemHoiVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoiVienActionPerformed
        // TODO add your handling code here:
        if(validateForm(true)){
            addHoivien();       
         }
    }//GEN-LAST:event_btnThemHoiVienActionPerformed

    private void btnTheoDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTheoDoiActionPerformed
        // TODO add your handling code here:     
        try {
            index = tblHoiVien.getSelectedRow();
            int a = (Integer) tblHoiVien.getValueAt(index, 1);
            this.dispose();
            new TheoDoiJDialog(a).setVisible(true);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTheoDoiActionPerformed

    private void btnXoaHVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHVActionPerformed
        // TODO add your handling code here:
        removeHoivien();
    }//GEN-LAST:event_btnXoaHVActionPerformed

    private void btnCapNhatTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatTTActionPerformed
        // TODO add your handling code here:
           updateTrangThai();
    }//GEN-LAST:event_btnCapNhatTTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoiVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoiVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoiVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoiVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HoiVienJDialog dialog = new HoiVienJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatTT;
    private javax.swing.JButton btnThemHoiVien;
    private javax.swing.JButton btnTheoDoi;
    private javax.swing.JButton btnXoaHV;
    private javax.swing.JComboBox<String> cboDichVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHoiVien;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    
}
