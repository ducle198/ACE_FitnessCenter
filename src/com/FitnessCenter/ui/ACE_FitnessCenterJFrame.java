/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.ui;

import com.ACE_FitnessCenter.helper.DialogHelper;
import com.ACE_FitnessCenter.helper.ShareHelper;
import com.FitnessCenter.utils.Auth;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JInternalFrame;
import javax.swing.JDialog;

/**
 *
 * @author ADMIN
 */
public class ACE_FitnessCenterJFrame extends javax.swing.JFrame {
        /**
     * Creates new form ACE_FitnessCenterJFrame
     */
    void init(){
        setLocationRelativeTo(null);
        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");
            @Override
            public void actionPerformed(ActionEvent e) {
               lblDongHo.setText(format.format(new Date()));
            }
        }).start();
        mnChao();
    }
    public ACE_FitnessCenterJFrame() {
        initComponents();
        init();
        mnDangNhap();
        PhanQuyen();
        ImageSet();
    }
    
    void ImageSet(){
        ImageIcon icon = new ImageIcon("src\\Images\\Wallpaper.png");
        Image img = icon.getImage();
        Image img2 = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imgicon = new ImageIcon(img2);
        image.setIcon(imgicon);
    }
    
    void PhanQuyen(){
        try{
            if(Auth.isLogin()){
                String chucvu = Auth.user.getChucVu();
                if(chucvu.equals("Quản lý")){
                    this.dispose();
                }else if(chucvu.equals("PT")){
                    this.dispose();
                    btndichvu.setEnabled(false);
                    btnhoadon.setEnabled(false);
                    btnkhachhang.setEnabled(false);
                    btnnhanvien.setEnabled(false);
                    btnthietbi.setEnabled(false);
                }else if(chucvu.equals("Marketing")){
                    btndichvu.setEnabled(false);
                    btnhoivien.setEnabled(false);
                    btnkhachhang.setEnabled(false);
                    btnnhanvien.setEnabled(false);
                    btnthietbi.setEnabled(false);
                }
            }
        }catch(Exception e){
            printStackTrace();
        }
    }
    
    public void mnChao(){
        new CuaSoChao1JDialog(this, true).setVisible(true);
    }
    
    public void mnDangNhap(){
        new DangNhapJDialog(this, true).setVisible(true); 
    }
    
    void dangXuat(){
        Auth.clear();
        new ACE_FitnessCenterJFrame().setVisible(true);
        this.dispose();
    }
    
    void thoat(){
        if(DialogHelper.confirm(this, "Bạn muốn thoát khỏi chương trình?")){
            System.exit(0);
        }
    }
    
    public void openKH(){
        try{
            KhachHangJDialog formKH = new KhachHangJDialog(this, true);
            formKH.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openHoiVien(){
        try{
            HoiVienJDialog formHV = new HoiVienJDialog(this, true);
            formHV.setVisible(true);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openNhanVien(){
        try{
            NhanvienJDialog formNV = new NhanvienJDialog(this, true);
            formNV.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openDichVu(){
        try{
            DichVuDialog formDV = new DichVuDialog(this, true);
            formDV.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openHoaDon(){
        try{
            HoaDonJDialog formDMK = new HoaDonJDialog(this, true);
            formDMK.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openDoiMK(){
        try{
            DoiMatKhauJDialog formDMK = new DoiMatKhauJDialog(this, true);
            formDMK.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openKhachHang(){
        try{
            KhachHangJDialog formKH = new KhachHangJDialog(this, true);
            formKH.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openThietBi(){
        try{
            ThietBiJDialog formTB = new ThietBiJDialog(this, true);
            formTB.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openHuongDan(){
        try{
            HuongdanJDialog formHD = new HuongdanJDialog();
            formHD.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openThongTin(){
        try{
            ThongTinJDialog formTT = new ThongTinJDialog();
            formTT.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void openX(JDialog x){
        for(JInternalFrame form : jDesktopPane1.getAllFrames()){
            form.dispose();
        }
        x.setLocation(this.getWidth()/2 - x.getWidth() / 2
                , (this.getHeight() - 20)/2 - x.getHeight() /2 -60);
        jDesktopPane1.add(x);
        x.setVisible(true);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        btnkhachhang = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnhoivien = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnnhanvien = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        btndichvu = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btnhoadon = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btnthietbi = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        btnthoat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        image = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btnkhachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/User group.png"))); // NOI18N
        btnkhachhang.setText("Khách hàng");
        btnkhachhang.setFocusable(false);
        btnkhachhang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnkhachhang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkhachhangActionPerformed(evt);
            }
        });
        jToolBar1.add(btnkhachhang);
        jToolBar1.add(jSeparator5);

        btnhoivien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/User.png"))); // NOI18N
        btnhoivien.setText("Hội viên");
        btnhoivien.setFocusable(false);
        btnhoivien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnhoivien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnhoivien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoivienActionPerformed(evt);
            }
        });
        jToolBar1.add(btnhoivien);
        jToolBar1.add(jSeparator6);

        btnnhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clien list.png"))); // NOI18N
        btnnhanvien.setText("Nhân viên");
        btnnhanvien.setFocusable(false);
        btnnhanvien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnhanvien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnhanvienActionPerformed(evt);
            }
        });
        jToolBar1.add(btnnhanvien);
        jToolBar1.add(jSeparator9);

        btndichvu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Lists.png"))); // NOI18N
        btndichvu.setText("Dịch vụ");
        btndichvu.setFocusable(false);
        btndichvu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndichvu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndichvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndichvuActionPerformed(evt);
            }
        });
        jToolBar1.add(btndichvu);
        jToolBar1.add(jSeparator7);

        btnhoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dollar.png"))); // NOI18N
        btnhoadon.setText("Hóa đơn");
        btnhoadon.setFocusable(false);
        btnhoadon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnhoadon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoadonActionPerformed(evt);
            }
        });
        jToolBar1.add(btnhoadon);
        jToolBar1.add(jSeparator8);

        btnthietbi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Brick house.png"))); // NOI18N
        btnthietbi.setText("Thiết bị");
        btnthietbi.setFocusable(false);
        btnthietbi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnthietbi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnthietbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthietbiActionPerformed(evt);
            }
        });
        jToolBar1.add(btnthietbi);
        jToolBar1.add(jSeparator2);
        jToolBar1.add(jSeparator10);

        btnthoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Stop sign.png"))); // NOI18N
        btnthoat.setText("Thoát");
        btnthoat.setFocusable(false);
        btnthoat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnthoat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnthoat);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Info.png"))); // NOI18N
        jLabel1.setText("Hệ thống quản lý phòng gym");

        lblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Alarm.png"))); // NOI18N
        lblDongHo.setText("12:12:12");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongHo)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDongHo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDesktopPane1.setBackground(new java.awt.Color(153, 204, 255));

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Wallpaper.png"))); // NOI18N
        image.setText("jLabel2");

        jDesktopPane1.setLayer(image, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jMenuItem1.setText("Đổi mật khẩu");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator11);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit.png"))); // NOI18N
        jMenuItem2.setText("Đăng xuất\n");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu5.setText("Trợ giúp");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Globe.png"))); // NOI18N
        jMenuItem3.setText("Hướng dẫn sử dụng\n");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Info.png"))); // NOI18N
        jMenuItem4.setText("Thông tin ứng dụng\n");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1128, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnhoivienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoivienActionPerformed
        // TODO add your handling code here:
        openHoiVien();
    }//GEN-LAST:event_btnhoivienActionPerformed

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        // TODO add your handling code here:
        thoat();
    }//GEN-LAST:event_btnthoatActionPerformed

    private void btnkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhachhangActionPerformed
        // TODO add your handling code here:
        openKhachHang();
    }//GEN-LAST:event_btnkhachhangActionPerformed

    private void btnnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanvienActionPerformed
        // TODO add your handling code here:
        openNhanVien();
    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btndichvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndichvuActionPerformed
        // TODO add your handling code here:
        openDichVu();
    }//GEN-LAST:event_btndichvuActionPerformed

    private void btnhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoadonActionPerformed
        // TODO add your handling code here:
        openHoaDon();
    }//GEN-LAST:event_btnhoadonActionPerformed

    private void btnthietbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthietbiActionPerformed
        // TODO add your handling code here:
        openThietBi();
    }//GEN-LAST:event_btnthietbiActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        openDoiMK();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        dangXuat();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        openHuongDan();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        openThongTin();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(ACE_FitnessCenterJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ACE_FitnessCenterJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ACE_FitnessCenterJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ACE_FitnessCenterJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ACE_FitnessCenterJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndichvu;
    private javax.swing.JButton btnhoadon;
    private javax.swing.JButton btnhoivien;
    private javax.swing.JButton btnkhachhang;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnthietbi;
    private javax.swing.JButton btnthoat;
    private javax.swing.JLabel image;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblDongHo;
    // End of variables declaration//GEN-END:variables
}
