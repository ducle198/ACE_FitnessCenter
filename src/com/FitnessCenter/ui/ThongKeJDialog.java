/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.ui;

import com.FitnessCenter.dao.ThongKeDao;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mycomputer
 */
public class ThongKeJDialog extends javax.swing.JFrame {

    ThongKeDao sv = new ThongKeDao();

    /**
     * Creates new form ThongKeJDialog
     */
    public ThongKeJDialog() {
        initComponents();
        setLocationRelativeTo(null);
        
        fillcombobox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cc = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldoanhthu = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cboDate = new javax.swing.JComboBox<>();
        lbldoanhthu_so = new javax.swing.JLabel();
        lbldoanhthu = new javax.swing.JLabel();
        btnbieudo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnDong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        cc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh Thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        cc.setForeground(new java.awt.Color(255, 204, 204));

        tbldoanhthu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Dịch Vụ", "Số Lượng Đã Bán", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbldoanhthu);

        javax.swing.GroupLayout ccLayout = new javax.swing.GroupLayout(cc);
        cc.setLayout(ccLayout);
        ccLayout.setHorizontalGroup(
            ccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ccLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        ccLayout.setVerticalGroup(
            ccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ccLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 255, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        cboDate.setToolTipText("");
        cboDate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDateItemStateChanged(evt);
            }
        });

        lbldoanhthu_so.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldoanhthu_so.setText("jLabel4");

        lbldoanhthu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldoanhthu.setForeground(new java.awt.Color(255, 255, 255));
        lbldoanhthu.setText("Tổng Doanh Thu");

        btnbieudo.setText("Biểu Đồ");
        btnbieudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbieudoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnbieudo, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(cboDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbldoanhthu_so, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(cboDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbldoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbldoanhthu_so, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btnbieudo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Tổng Hợp Thống Kê ");

        btnDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit.png"))); // NOI18N
        btnDong.setText("Đóng");
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(btnDong))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDong))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(cc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboDateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDateItemStateChanged

        if (cboDate.getSelectedIndex() == 0) {
            filldoanhthu_ngay_label("Hôm Nay");
            //            fillsosanh_Ngay_label("Hôm Qua");
            //            filltongspban_ngay_label("Hôm Nay");
            //            ////            long a=Integer.valueOf(lblDoanhthu.getText());
            //            ////            long b=Integer.valueOf(lblsosanhso.getText());
            //            ////            float c=((b-a)/b);
            //            //            lblDoanhthu2.setText(c+" %");

            filltotablengay_ngay();
        }
        if (cboDate.getSelectedIndex() == 1) {
            filldoanhthu_thang_label("Tháng Này");
            //            fillsosanh_Thang_label("Tháng Trước");
            //            filltongspban_thang_label("Tháng Này");
            //            //             int a=Integer.valueOf(lblDoanhthu.getText());
            //            //            int b=Integer.valueOf(lblsosanhso.getText());
            //            //            float c=((b-a)/b);
            //            //            lblDoanhthu2.setText(c+" %");
            filltotablethang();
        }
        if (cboDate.getSelectedIndex() == 2) {
            filldoanhthu_nam_label("Năm Nay");
            //            fillsosanh_nam_label("Năm Trước");
            //            filltongspban_nam_label("Năm Nay");
            //            //             int a=Integer.valueOf(lblDoanhthu.getText());
            //            //            int b=Integer.valueOf(lblsosanhso.getText());
            //            //            float c=((b-a)/b);
            //            //            lblDoanhthu2.setText(c+" %");
            filltotablenam();

        }
    }//GEN-LAST:event_cboDateItemStateChanged

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnbieudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbieudoActionPerformed
       Bieudo bd=new Bieudo();
       bd.setVisible(true);
    }//GEN-LAST:event_btnbieudoActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeJDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnbieudo;
    public static javax.swing.JComboBox<String> cboDate;
    private javax.swing.JPanel cc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldoanhthu;
    private javax.swing.JLabel lbldoanhthu_so;
    private javax.swing.JTable tbldoanhthu;
    // End of variables declaration//GEN-END:variables

    private void fillcombobox() {
        String[] a = {"Hôm Nay", "Tháng Này", "Năm Này"};
        DefaultComboBoxModel modelcb = (DefaultComboBoxModel) cboDate.getModel();
        for (int i = 0; i < a.length; i++) {
            modelcb.addElement(a[i]);
        }
    }

    private void filldoanhthu_ngay_label(String a) {
        List<Long> list = sv.selectdaonhthu_ngay_label();
        lbldoanhthu.setText("Doanh Thu " + a);
        for (Long tien : list) {

            String patternTienTe = "###,###,###,###,### " + "VND";
            DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formatTienTe.format(tien);
            lbldoanhthu_so.setText(stringTienTe);
        }
    }
    

    private void filltotablengay_ngay() {
        DefaultTableModel model = (DefaultTableModel) tbldoanhthu.getModel();
        model.setRowCount(0);
        List< Object[]> list = sv.getDoanhThu_ngay_table();
        for (Object[] row : list) {
            model.addRow(row);

        }
    }

    private void filldoanhthu_thang_label(String a) {
       List<Long> list = sv.selectdaonhthu_Thang_label();
        lbldoanhthu.setText("Doanh Thu " + a);
        for (Long tien : list) {

            String patternTienTe = "###,###,###,###,### " + "VND";
            DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formatTienTe.format(tien);
            lbldoanhthu_so.setText(stringTienTe);
        }
    }

    private void filltotablethang() {
        DefaultTableModel model = (DefaultTableModel) tbldoanhthu.getModel();
        model.setRowCount(0);
        List< Object[]> list = sv.getDoanhThu_Thang_table();
        for (Object[] row : list) {
            model.addRow(row);

        }
    }

    private void filldoanhthu_nam_label(String a) {
        List<Long> list = sv.selectdaonhthu_nam_label();
        lbldoanhthu.setText("Doanh Thu " + a);
        for (Long tien : list) {

            String patternTienTe = "###,###,###,###,### " + "VND";
            DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
            String stringTienTe = formatTienTe.format(tien);
            lbldoanhthu_so.setText(stringTienTe);

        }
    }

    private void filltotablenam() {
        DefaultTableModel model = (DefaultTableModel) tbldoanhthu.getModel();
        model.setRowCount(0);
        List< Object[]> list = sv.getDoanhThu_nam_table();
        for (Object[] row : list) {
            model.addRow(row);

        }
    }
}
