 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.ui;

import com.ACE_FitnessCenter.helper.DialogHelper;
import com.FitnessCenter.dao.HoaDonDao;
import com.FitnessCenter.dao.CTHoaDonDAO;
import com.FitnessCenter.dao.DichVuDao;
import com.FitnessCenter.entity.CTHoaDon;
import com.FitnessCenter.entity.DichVu;
import com.FitnessCenter.entity.HoaDon;
import com.FitnessCenter.utils.MsgBox;
import com.FitnessCenter.utils.XDate;
import com.FitnessCenter.utils.XMoney;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class HoaDonJDialog extends javax.swing.JDialog {
    HoaDonDao hddao = new HoaDonDao(); 
    CTHoaDonDAO ctdao = new CTHoaDonDAO();
    DichVuDao dvdao = new DichVuDao();
//    DichVuDao dvdao = new DichVuDao();
    int row = -1;
    /**
     * Creates new form HoaDonJDialog
     */
    
    
    public HoaDonJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    void init() {
        setLocationRelativeTo(null);
        setTitle("ACE - Quản lý hóa đơn");
        this.fillTableHD();
        this.row = -1;
        this.updateStatus();
        this.fillComboBox();
        
        
                      
    }
    
    // code hóa đơn
   private void timkiemHoaDon(){
       this.fillTableHD();
        this.clearFormHD();
        this.row = -1;
        this.updateStatus();
   }
   void insertHD(){
     HoaDon hd = getFormHD();

        try {
            hddao.insert(hd);
            this.fillTableHD();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!" );
            e.printStackTrace();
        }  
   }
   void updateHD(){
      HoaDon hd = getFormHD();

        try {
            hddao.update(hd);
            this.fillTableHD();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            e.printStackTrace();
        } 
   }
   void deleteHD(){
    String mahd = txtMaHD.getText();
        if (MsgBox.confirm(this, "Bạn có thực sự muốn xóa khách hàng này?")) {
            try {
                hddao.delete(mahd);
                this.fillTableHD();
                this.clearFormHD();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thát bại!");
            }
        }   
    }
   void clearFormHD(){
     HoaDon hd = new HoaDon();
        this.setFormHD(hd);
        this.row = -1;
        this.updateStatus();
//        lblThanhTien.setToolTipText("");
//        lblTraLai.setText("");
        lblCanTra.setText("");
        
   }
   void editFormHD(){
       String mahd = (String) tblHoaDon.getValueAt(this.row, 0);
       
        HoaDon hd = hddao.selectByID(mahd);
        if (hd != null) {
            this.fillTableCTHD(mahd);
            this.setFormHD(hd);
            tabs.setSelectedIndex(0);
            this.updateStatus();           
        }  
   }
   void fillTableHD(){
       DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0); // xoa tat ca cac hang
        try {
            String keyword = txtTimKiem.getText();
            List<HoaDon> list = hddao.selectByKeyword(keyword); //truy vấn người học có tên chứa từ khóa tìm kiếm
            for (HoaDon hd : list) {
                Object[] row = {
                    hd.getMaHD(),
                    XDate.toString(hd.getNgayLap()),
                    hd.getMaKH(),
                    hd.getMaNV(),
                    hd.getTongTien(),
                    hd.getGiamGia(),
                    hd.getKhachDaTra(),
                    hd.getConNo()};
                model.addRow(row); //them 1 hang vao table
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        } 
   }
   
   HoaDon getFormHD(){
       HoaDon hd = new HoaDon();
        hd.setMaHD(txtMaHD.getText());
        hd.setNgayLap(XDate.toDate(txtNgayLap.getText()));
        hd.setMaKH(txtMaKH.getText());
        hd.setMaNV(txtMaNV.getText());
        hd.setTongTien(Float.valueOf(txtTongTien.getText()));
        hd.setGiamGia(Float.valueOf(txtGiamGia.getText()));
        hd.setKhachDaTra(Float.valueOf(txtKhachTra.getText()));
        hd.setConNo(Float.valueOf(lblTraLai.getText()));        
       return hd;
   }
   
     
    
    /* Chi tiết hóa đơn*/
    void fillTableCTHD(String key){
       DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        model.setRowCount(0); // xoa tat ca cac hang
        try {               
            List<CTHoaDon> list_CTHD =    ctdao.selectByKeyword(key); //truy vấn người học có tên chứa từ khóa tìm kiếm
            for (CTHoaDon ct : list_CTHD) {
                Object[] row = {
                    ct.getMaDV(),
                    ct.getSoLuong(),
                    ct.getDonGia(),
                    ct.getGiamGia(),
                    ct.getThanhTien(),
                    ct.getGhiChu()
                    };
                model.addRow(row); //them 1 hang vao table
            }
            txtTongTien.setText(String.valueOf(TinhTien()));
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
             } 
    }
    void editFormCTHD(){
        try {
            String maHD = txtMaHD.getText();
            String madv = (String) tblCTHoaDon.getValueAt(this.row, 0); 
            DichVu dv = dvdao.findById(madv);
            cbbDichVu.setSelectedItem(dv.getTenDV());
            CTHoaDon cthd = ctdao.selectByMaDV(maHD,madv);
            if (cthd != null) {            
            this.setFormCTHD(cthd);                     
        }  
        } catch (Exception e) {
           MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace(); 
        }
   }
    CTHoaDon getformCTHoaDon(){
        CTHoaDon ct = new CTHoaDon();
        ct.setMaHD(txtMaHD.getText());
        ct.setMaDV(lblMaDV.getText());
        ct.setDonGia(Float.valueOf(lblDonGia.getText()));
        ct.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        ct.setGiamGia(Float.valueOf(txtGiamGia2.getText()));
        ct.setThanhTien(Float.valueOf(lblThanhTien2.getText()));
        ct.setGhiChu(txtGhiChu.getText());
        return ct;
    }
    private void setFormCTHD(CTHoaDon ct) {
      lblMaDV.setText(ct.getMaDV());
      txtSoLuong.setText(String.valueOf(ct.getSoLuong()));
      lblDonGia.setText(String.valueOf(ct.getDonGia()));
      txtGiamGia2.setText(String.valueOf(ct.getGiamGia()));
      lblThanhTien2.setText(String.valueOf(ct.getThanhTien()));
      txtGhiChu.setText(String.valueOf(ct.getGhiChu()));
    }
    
    void insertCTHoaDon(){
     CTHoaDon ct = getformCTHoaDon();

        try {
            ctdao.insert(ct);
            this.fillTableCTHD(txtMaHD.getText());
            this.clearFormCTHoaDon();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!" );
            e.printStackTrace();
        }  
   }
   void updateCTHD(){
      CTHoaDon ct = getformCTHoaDon();

        try {
           ctdao.update(ct);
            this.fillTableCTHD(txtMaHD.getText());
            this.clearFormCTHoaDon();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
        } 
   }
   void deleteCTHD(){
    String madv = lblMaDV.getText();
    String mahd = txtMaHD.getText();
        if (MsgBox.confirm(this, "Bạn có thực sự muốn xóa khách hàng này?")) {
            try {
                ctdao.deleteCTHoaDon(madv, mahd);
               this.fillTableCTHD(txtMaHD.getText());
                    this.clearFormCTHoaDon();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thát bại!");
            }
        }   
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHoaDon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblTraLai = new javax.swing.JLabel();
        lblCanTra = new javax.swing.JLabel();
        btnTaoMoiHoaDon = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        txtMaNV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbbDichVu = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        lblDonGia = new javax.swing.JLabel();
        txtGiamGia2 = new javax.swing.JTextField();
        lblThanhTien2 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        lblMaDV = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        btnThemCTHD = new javax.swing.JButton();
        btnSuaCTHD = new javax.swing.JButton();
        btnXoaCTHD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("QUẢN LÝ HÓA ĐƠN");

        tabs.setBackground(new java.awt.Color(0, 204, 204));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày lập", "Mã khách hàng", "Mã nhân viên", "Tổng tiền", "Giảm giá", "Khách đã trả", "Trả lại/ Còn nợ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem)
                .addGap(239, 239, 239))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        tabs.addTab("Danh sách hóa đơn", jPanel1);

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã dịch vụ", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Mã hóa đơn:");

        jLabel5.setText("Ngày lập");

        jLabel6.setText("Mã khách hàng:");

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel7.setText("Tổng tiền hàng:");

        jLabel8.setText("Giảm giá");

        txtGiamGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGiamGiaFocusLost(evt);
            }
        });

        jLabel9.setText("Khách cần trả:");

        jLabel10.setText("Khách trả");

        txtKhachTra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKhachTraFocusLost(evt);
            }
        });

        jLabel11.setText("Trả lại/ Còn nợ");

        lblTraLai.setText("0");

        lblCanTra.setText("0");

        btnTaoMoiHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add.png"))); // NOI18N
        btnTaoMoiHoaDon.setText("Tạo hóa đơn");
        btnTaoMoiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiHoaDonActionPerformed(evt);
            }
        });

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Key.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã nhân viên:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lblTraLai, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                        .addGap(173, 173, 173))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtKhachTra)
                                                .addComponent(lblCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap(58, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTaoMoiHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoi)
                        .addGap(35, 35, 35)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(56, 56, 56)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu)
                    .addComponent(btnTaoMoiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblThanhTien.setBackground(new java.awt.Color(0, 204, 204));
        lblThanhTien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Mã dịch vụ:");

        jLabel13.setText("Tên dịch vụ:");

        jLabel14.setText("Đơn giá");

        jLabel15.setText("Giảm giá:");

        jLabel16.setText("Thành tiền");

        jLabel17.setText("Ghi chú");

        jLabel18.setText("Số lượng");

        cbbDichVu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDichVuItemStateChanged(evt);
            }
        });
        cbbDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDichVuActionPerformed(evt);
            }
        });

        lblDonGia.setText("0");

        txtGiamGia2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGiamGia2FocusLost(evt);
            }
        });

        lblThanhTien2.setText("0");

        lblMaDV.setText("Mã dịch vụ");

        jButton6.setText("Thêm");

        btnThemCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add.png"))); // NOI18N
        btnThemCTHD.setText("Thêm");
        btnThemCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTHDActionPerformed(evt);
            }
        });

        btnSuaCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Edit.png"))); // NOI18N
        btnSuaCTHD.setText("Sửa");
        btnSuaCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTHDActionPerformed(evt);
            }
        });

        btnXoaCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        btnXoaCTHD.setText("Xóa");
        btnXoaCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lblThanhTienLayout = new javax.swing.GroupLayout(lblThanhTien);
        lblThanhTien.setLayout(lblThanhTienLayout);
        lblThanhTienLayout.setHorizontalGroup(
            lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblThanhTienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblThanhTienLayout.createSequentialGroup()
                        .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel18)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel12))
                        .addGap(35, 35, 35)
                        .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbbDichVu, 0, 307, Short.MAX_VALUE)
                                .addComponent(txtSoLuong)
                                .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblThanhTien2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGhiChu)
                                .addComponent(lblMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaCTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemCTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaCTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(83, 83, 83))
        );
        lblThanhTienLayout.setVerticalGroup(
            lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblThanhTienLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(lblMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbbDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(btnThemCTHD))
                .addGap(18, 18, 18)
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaCTHD))
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblThanhTienLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(lblThanhTienLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnXoaCTHD)))
                .addGap(18, 18, 18)
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblThanhTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lblThanhTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabs.addTab("Chi tiết hóa đơn", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabs)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void tblCTHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHoaDonMouseClicked
        // TODO add your handling code here: 
//            txtTongTien.setText(String.valueOf(TinhTien()));
        
            this.row = tblCTHoaDon.getSelectedRow();
            if (this.row >= 0) {
                this.editFormCTHD(); 
                
            } 
       
    }//GEN-LAST:event_tblCTHoaDonMouseClicked

    private void btnTaoMoiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiHoaDonActionPerformed
        // TODO add your handling code here:    
        if (txtMaHD.getText().length() == 0) {
            MsgBox.alert(this, "Không được phép để trống mã hóa đơn!");
            clearFormHD();
            TaoMoiHD();
            return;
        } else if (txtMaKH.getText().length() ==0) {
            MsgBox.alert(this, "Mã khách hàng không được để trống!");
            txtMaKH.requestFocus();
            return ;
        }else if (txtMaNV.getText().length() ==0) {
            MsgBox.alert(this, "Mã nhân không được để trống!");
            txtMaNV.requestFocus();
            return ;
        } 
        insertHD();
        
    }//GEN-LAST:event_btnTaoMoiHoaDonActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        
        if(tblCTHoaDon.getRowCount() == 0){
           MsgBox.alert(this, "Bạn chưa chọn dịch vụ nào!");
           return;
        }
        updateHD();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
       clearFormHD();
       clearFormCTHoaDon();
        TaoMoiHD();
        
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timkiemHoaDon();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblHoaDon.rowAtPoint(evt.getPoint());
            if (this.row >= 0) {
                this.editFormHD();
                tabs.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtGiamGiaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiamGiaFocusLost
        // TODO add your handling code here:
        Float tongtienHang = Float.parseFloat(txtTongTien.getText());
        Float giamGia = Float.parseFloat(txtGiamGia.getText());
        Float khachcantra = tongtienHang - tongtienHang*giamGia/100;
        lblCanTra.setText(String.valueOf(khachcantra));
    }//GEN-LAST:event_txtGiamGiaFocusLost

    private void cbbDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDichVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDichVuActionPerformed

    private void cbbDichVuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDichVuItemStateChanged
        // TODO add your handling code here:
        if(cbbDichVu.getItemCount() > 0){           
            DichVu dv = dvdao.findByName(cbbDichVu.getSelectedItem().toString());
            lblMaDV.setText(dv.getMaDV());
            lblDonGia.setText(String.valueOf(dv.getGiaDV()));
        }
        
    }//GEN-LAST:event_cbbDichVuItemStateChanged

    private void btnThemCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTHDActionPerformed
        // TODO add your handling code here:
        insertCTHoaDon();
    }//GEN-LAST:event_btnThemCTHDActionPerformed

    private void btnSuaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTHDActionPerformed
        // TODO add your handling code here:
        updateCTHD();
    }//GEN-LAST:event_btnSuaCTHDActionPerformed

    private void btnXoaCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTHDActionPerformed
        // TODO add your handling code here:
        deleteCTHD();
    }//GEN-LAST:event_btnXoaCTHDActionPerformed

    private void txtGiamGia2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiamGia2FocusLost
        // TODO add your handling code here:
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        Float DonGia = Float.parseFloat(lblDonGia.getText());
        Float GiamGia2 = Float.parseFloat(txtGiamGia2.getText());
        Float thanhTien2 = DonGia*soLuong - DonGia*soLuong*GiamGia2/100;
        lblThanhTien2.setText(String.valueOf(thanhTien2));
    }//GEN-LAST:event_txtGiamGia2FocusLost

    private void txtKhachTraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKhachTraFocusLost
        // TODO add your handling code here:
        Float canTra = Float.parseFloat(lblCanTra.getText());
        Float daTra = Float.parseFloat(txtKhachTra.getText());
        Float conDu = daTra - canTra;
        lblTraLai.setText(String.valueOf(conDu));
    }//GEN-LAST:event_txtKhachTraFocusLost

    void clearFormCTHoaDon(){
        CTHoaDon hd = new CTHoaDon();
        this.setFormCTHD(hd);
    }
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
            java.util.logging.Logger.getLogger(HoaDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HoaDonJDialog dialog = new HoaDonJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSuaCTHD;
    private javax.swing.JButton btnTaoMoiHoaDon;
    private javax.swing.JButton btnThemCTHD;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaCTHD;
    private javax.swing.JComboBox<String> cbbDichVu;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCanTra;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblMaDV;
    private javax.swing.JPanel lblThanhTien;
    private javax.swing.JLabel lblThanhTien2;
    private javax.swing.JLabel lblTraLai;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblCTHoaDon;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtGiamGia2;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    private void updateStatus() {
     boolean edit = (this.row >= 0);
       txtMaKH.setEditable(!edit);
        btnTaoMoiHoaDon.setEnabled(!edit);
        
                  
    }

    private void setFormHD(HoaDon hd) {
      txtMaHD.setText(hd.getMaHD());
       txtNgayLap.setText(XDate.toString(hd.getNgayLap()));
       txtMaKH.setText(hd.getMaKH());
       txtMaNV.setText(hd.getMaNV());
       txtTongTien.setText(String.valueOf(hd.getTongTien()));
       txtGiamGia.setText(String.valueOf(hd.getGiamGia()));
       txtKhachTra.setText(String.valueOf(hd.getConNo()));
       lblTraLai.setText(String.valueOf(hd.getConNo())); 
    }
    private double TinhTien() {
        double tongTien = 0;
        for (int i = 0; i < tblCTHoaDon.getRowCount(); i++) {
            tongTien += XMoney.ChuyenTien(tblCTHoaDon.getValueAt(i, 4).toString());
        }

        return tongTien;
    }
    private void TaoMoiHD(){
        txtMaHD.setText(SoHoaDon());
        DefaultTableModel tbModel = (DefaultTableModel)tblCTHoaDon.getModel();
        tbModel.setRowCount(0);
    }
    private String SoHoaDon() {
        String soHoaDon = "";
        try {

            DateFormat dateFormat = new SimpleDateFormat("yyMMdd");

            Date d = new Date();

            soHoaDon = dateFormat.format(d);
            System.out.println("soHoaDon: " + soHoaDon);
            ResultSet rs = (ResultSet) hddao.CountSoHoaDon(soHoaDon);
            int rowCount = 0;
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
            boolean dup = false;
            do {
                if (rowCount > 98) {
                    soHoaDon = soHoaDon + (rowCount + 1);
                } else if (rowCount > 8) {
                    soHoaDon = soHoaDon + "0" + (rowCount + 1);
                } else {
                    soHoaDon = soHoaDon + "00" + (rowCount + 1);
                }
                System.out.println("soHoaDon: " + soHoaDon);
                ResultSet rs2 = hddao.GetBySoHoaDon(soHoaDon);
                if (rs2.next()) {
                    dup = true;
                    rowCount++;
                    soHoaDon = dateFormat.format(d);
                } else {
                    dup = false;
                }
            } while (dup);

        } catch (SQLException ex) {
            System.out.println("Lỗi số hóa đơn");
        }

        return soHoaDon;
    }
    HoaDon getTaoMoi(){
       HoaDon hd = new HoaDon();
        hd.setMaHD(SoHoaDon());
        hd.setNgayLap(XDate.toDate(txtNgayLap.getText()));
        hd.setMaKH(txtMaKH.getText());
        hd.setMaNV(txtMaNV.getText());
        hd.setTongTien(Float.valueOf(txtTongTien.getText()));
        hd.setGiamGia(Float.valueOf(txtGiamGia.getText()));
        hd.setKhachDaTra(Float.valueOf(txtKhachTra.getText()));
        hd.setConNo(Float.valueOf(lblTraLai.getText()));        
       return hd;
   }
    
    private void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbDichVu.getModel();
        model.removeAllElements();
        try {
            List<DichVu> list = dvdao.select();
            for (DichVu dv : list) {
                model.addElement(dv.getTenDV());
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    
}
