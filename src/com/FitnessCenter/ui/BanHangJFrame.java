/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.ui;

import com.ACE_FitnessCenter.helper.DialogHelper;
import com.FitnessCenter.dao.CTHoaDonDAO;
import com.FitnessCenter.dao.DichVuDao;
import com.FitnessCenter.dao.HoaDonDao;
import com.FitnessCenter.dao.KhachHangDao;
import com.FitnessCenter.dao.NhanVienDao;
import com.FitnessCenter.entity.CTHoaDon;
import com.FitnessCenter.entity.DichVu;
import com.FitnessCenter.entity.HoaDon;
import com.FitnessCenter.entity.KhachHang;
import com.FitnessCenter.entity.NhanVien;
import com.FitnessCenter.utils.Auth;
import com.FitnessCenter.utils.MsgBox;
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
public class BanHangJFrame extends javax.swing.JFrame { 
    DefaultTableModel model = new DefaultTableModel();
    DichVuDao dvdao = new DichVuDao();
    KhachHangDao khdao = new KhachHangDao();
    CTHoaDonDAO ctdao = new CTHoaDonDAO();
    HoaDonDao hddao = new HoaDonDao();
    NhanVienDao nvdao = new NhanVienDao();
    
    /**
     * Creates new form HoaDon2
     */
    public BanHangJFrame(String a) {
        initComponents();
        init();
        if(a != null){
            load(a);
        }else{
            taomoiHoaDon();
        }
    }
    void load(String a){
        HoaDon hd = hddao.selectByID(a);
        txtMaHD.setText(hd.getMaHD());
        txtNgayLap.setText(XMoney.DinhDangNgay(hd.getNgayLap()));
        txtMaNV.setText(hd.getMaNV());
        NhanVien nv = nvdao.selectByID(hd.getMaNV());
        txtTenNV.setText(nv.getTenNV());
        KhachHang kh = khdao.selectByID(hd.getMaKH());
        cboKhachHang.setSelectedItem(kh.getTenKH());
        CTHoaDon ct = ctdao.selectByID(a);
        DichVu dv = dvdao.findById(ct.getMaDV());
        System.out.println(dv.getTenDV());
        ThemSanPhamTbCTHD(ct.getMaDV(), dv.getTenDV(),ct.getSoLuong(),dv.getGiaDV(), ct.getGiamGia(), ct.getThanhTien(), ct.getGhiChu());
        lblTongTien.setText(String.valueOf(hd.getTongTien()));
        txtGiamGiaHoaDon.setText(String.valueOf(hd.getGiamGia()));
        txtKhachTra.setText(String.valueOf(hd.getKhachDaTra()));
        lblTienThua.setText(String.valueOf(hd.getConNo()));         
    }
    void updateStatus(String a){
        boolean edit = (a != null);
        txtMaHD.setEditable(!edit);
        txtNgayLap.setEditable(!edit);
        txtMaNV.setEditable(!edit);
        txtTenNV.setEditable(!edit);
        cboKhachHang.setEnabled(!edit);
        tblDichVu.setEnabled(!edit);
        tblCTHoaDon.setEnabled(!edit);
        mniSuaDV.setEnabled(!edit);
        mniSuaDV.setEnabled(!edit);
        txtGiamGiaHoaDon.setEditable(!edit);
        txtKhachTra.setEditable(!edit);
        btnThanhToan.setEnabled(!edit);
        btnTaoHoaDonMoi.setEnabled(edit);
        btnHuy.setEnabled(edit);
    }
    
    void init(){
        setLocationRelativeTo(null);
        fillTableDichVu();
        fillComboBoxKhachHang(txtTimKiemKH.getText());
        
        
    }
    void fillTableDichVu(){
       DefaultTableModel model = (DefaultTableModel) tblDichVu.getModel();
        model.setRowCount(0);
        try {

            String keyword = txtTimDV.getText();
            List<DichVu> list = dvdao.selectByName(keyword);

            for (DichVu nh : list) {
                Object[] row = {
                    nh.getMaDV(),
                    nh.getTenDV(),
                    nh.getGiaDV(),
                    nh.getMaCa(),                    
                    nh.isTrangThai() ? "Hoạt ĐỘng" : "Ngưng Hoạt Động", nh.getMota()};

                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void taomoiHoaDon(){
        DefaultTableModel tbmodel = (DefaultTableModel) tblCTHoaDon.getModel();
        if(Auth.isLogin()){
            txtMaNV.setText(Auth.user.getMaNV());
            txtTenNV.setText(Auth.user.getTenNV());
        }
        txtMaHD.setText(SoHoaDon());
        cboKhachHang.setSelectedIndex(0);
        txtNgayLap.setText(XMoney.DinhDangNgay(new Date()));
        tbmodel.setRowCount(0);
        lblTongTien.setText("0");
        txtGiamGiaHoaDon.setText("0");
        lblTienThua.setText("0");
        lblPhaiTra.setText("0");
        txtKhachTra.setText("0");
        lblTienThua.setText("0");
        lblDiaChi.setText("");
        lblSDT.setText("");
        
    }

    void fillComboBoxKhachHang(String key){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhachHang.getModel();
        model.removeAllElements();
        try {
//            String tenKH = txtTimKiemKH.getText();
            List<KhachHang> list = khdao.selectByTenKH(key);
            for (KhachHang dv : list) {
                model.addElement(dv.getTenKH());
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
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

        pmnCTHD = new javax.swing.JPopupMenu();
        mniXoaDV = new javax.swing.JMenuItem();
        mniSuaDV = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtTimDV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiemKH = new javax.swing.JTextField();
        lblSDT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        cboKhachHang = new javax.swing.JComboBox<>();
        txtMaHD = new javax.swing.JTextField();
        txtNgayLap = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHoaDon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtGiamGiaHoaDon = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lblPhaiTra = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        btnHuy = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        txtKhachTra = new javax.swing.JTextField();
        btnTaoHoaDonMoi = new javax.swing.JButton();

        mniXoaDV.setText("Xóa dịch vụ");
        mniXoaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniXoaDVActionPerformed(evt);
            }
        });
        pmnCTHD.add(mniXoaDV);

        mniSuaDV.setText("Sửa dịch vụ");
        mniSuaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSuaDVActionPerformed(evt);
            }
        });
        pmnCTHD.add(mniSuaDV);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bán hàng - ACE Fitness Center");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText("Thông tin dịch vụ"); // NOI18N

        txtTimDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimDVActionPerformed(evt);
            }
        });

        jLabel1.setText("Nhập từ khóa");

        jLabel2.setText("Chọn dịch vụ");

        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DV", "Tên dịch vụ", "Đơn giá", "Mã ca", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDichVuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDichVu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimDV)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimDV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Thông tin dịch vụ");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel4.setText("Số hóa đơn:");

        jLabel5.setText("Ngày lập:");

        jLabel6.setText("Mã nhân viên:");

        jLabel7.setText("Chọn khách hàng:");

        jLabel8.setText("Tìm khách hàng:");

        jLabel9.setText("Thông tin khách hàng");

        txtTimKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemKHActionPerformed(evt);
            }
        });
        txtTimKiemKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKHKeyReleased(evt);
            }
        });

        lblSDT.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSDT.setText("SDT");

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblDiaChi.setText("Địa chỉ");

        cboKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhachHangItemStateChanged(evt);
            }
        });

        txtMaHD.setEditable(false);

        txtNgayLap.setEditable(false);

        txtMaNV.setEditable(false);

        jLabel10.setText("Tên nhân viên:");

        txtTenNV.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboKhachHang, 0, 237, Short.MAX_VALUE)
                            .addComponent(txtTimKiemKH)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 309, Short.MAX_VALUE))
                            .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addGap(36, 36, 36))))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã dịch vụ", "Tên dịch vụ", "Đơn  giá", "Số lượng", "Giảm giá", "Thành tiền", "Ghi chú"
            }
        ));
        tblCTHoaDon.setComponentPopupMenu(pmnCTHD);
        jScrollPane2.setViewportView(tblCTHoaDon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setText("Tổng tiền:");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTongTien.setText("0");

        jLabel18.setText("Giảm giá:");

        txtGiamGiaHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaHoaDonKeyReleased(evt);
            }
        });

        jLabel19.setText("Khách phải trả:");

        lblPhaiTra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPhaiTra.setText("0");

        jLabel21.setText("Tiền khách trả:");

        jLabel23.setText("Còn dư:");

        lblTienThua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(255, 0, 0));
        lblTienThua.setText("0");

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(51, 204, 0));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyReleased(evt);
            }
        });

        btnTaoHoaDonMoi.setText("Tạo hóa đơn mới");
        btnTaoHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnTaoHoaDonMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnThanhToan)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(38, 38, 38)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel23)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(txtGiamGiaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(293, 293, 293))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19)
                        .addComponent(lblPhaiTra)
                        .addComponent(txtGiamGiaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel23)
                        .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDonMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimDVActionPerformed
        // TODO add your handling code here:
        fillTableDichVu();
    }//GEN-LAST:event_txtTimDVActionPerformed
    public static String MaDV;
    public static int SoLuong = 1;
    public static float GiamGia = 0;
    public static String GhiChu;
    public static boolean Huy = false;
    private void tblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() >= 2) {
            jdlAddDichVu jdl = new jdlAddDichVu(this, true);
            jdl.setVisible(true);
            if(Huy){
                return;
            }
            MaDV = tblDichVu.getValueAt(tblDichVu.getSelectedRow(), 0).toString();
            System.out.println(MaDV);
            String tenDV = tblDichVu.getValueAt(tblDichVu.getSelectedRow(), 1).toString();

            float thanhtien, dongia;
            dongia = Float.parseFloat(tblDichVu.getValueAt(tblDichVu.getSelectedRow(), 2).toString());           
            thanhtien = dongia * SoLuong * (100 - GiamGia) / 100;
            ThemSanPhamTbCTHD(MaDV, tenDV, SoLuong, dongia,GiamGia, thanhtien, GhiChu);
            lblTongTien.setText(XMoney.DinhDangTien(TinhTien()));
        }
    }//GEN-LAST:event_tblDichVuMouseClicked

    private void txtTimKiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemKHActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemKHActionPerformed

    private void cboKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhachHangItemStateChanged
        // TODO add your handling code here:
        if(cboKhachHang.getItemCount() > 0){
            KhachHang kh = khdao.selectByName(cboKhachHang.getSelectedItem().toString());
            lblSDT.setText(kh.getSdt());
            lblDiaChi.setText(kh.getDiaChi());
        }
    }//GEN-LAST:event_cboKhachHangItemStateChanged

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        List<HoaDon> listhd = hddao.selectAll();
        for (HoaDon hd : listhd) {
            if(txtMaHD.getText().equals(hd.getMaHD())){
                MsgBox.alert(this, "Vui lòng tạo hóa đơn mới");
                btnTaoHoaDonMoi.requestFocus();
                return;
            }
        }
        if(lblDiaChi.getText().length() == 0){
            MsgBox.alert(this, "Vui lòng chọn khách hàng!");
            cboKhachHang.requestFocus();
            return;
        }else if(tblCTHoaDon.getRowCount() <= 0){
            MsgBox.alert(this, "Vui lòng chọn dịch vụ");
            return;
        }else if(!Auth.user.getMaNV().equals(txtMaNV.getText())){
            MsgBox.alert(this, "Vui lòng tạo hóa đơn mới");
            btnTaoHoaDonMoi.requestFocus();
            return;
        }
        try {
            HoaDon hd = new HoaDon();
            hd.setMaHD(txtMaHD.getText());
            hd.setNgayLap(XMoney.LayNgay(txtNgayLap.getText()));
            KhachHang kh = khdao.selectByName(cboKhachHang.getSelectedItem().toString());
            hd.setMaKH(kh.getMaKH());
            hd.setMaNV(txtMaNV.getText());
            hd.setTongTien(XMoney.ChuyenTien(lblTongTien.getText()));
            hd.setGiamGia(XMoney.ChuyenTien(txtGiamGiaHoaDon.getText()));
            hd.setKhachDaTra(XMoney.ChuyenTien(txtKhachTra.getText()));
            hd.setConNo(XMoney.ChuyenTien(lblTienThua.getText()));
            hddao.insert(hd); 
            ThemCTHDTuTable(hd.getMaHD());
            MsgBox.alert(this, "Tạo hóa đơn thành công");
            taomoiHoaDon();
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi tạo hóa đơn");
            e.printStackTrace();
        }       
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void ThemCTHDTuTable(String MaHD) {
        for (int i = 0; i < tblCTHoaDon.getRowCount(); i++) {

            String MaDV = tblCTHoaDon.getValueAt(i, 1).toString();
            int SoLuong = Integer.parseInt(tblCTHoaDon.getValueAt(i, 4).toString());
            float DonGia = XMoney.ChuyenTien(tblCTHoaDon.getValueAt(i, 3).toString());
            float GiamGiact = XMoney.ChuyenTien(tblCTHoaDon.getValueAt(i, 5).toString());
            float ThanhTien = XMoney.ChuyenTien(tblCTHoaDon.getValueAt(i, 6).toString());
            String GhiChu = tblCTHoaDon.getValueAt(i, 7).toString();

            ThemChiTietHoaDon(MaHD, MaDV, SoLuong, DonGia, GiamGiact ,ThanhTien, GhiChu);
        }
    }
    private void ThemChiTietHoaDon(String MaHD, String MaDV, int SoLuong, float dongia,
            float GiamGia,float ThanhTien, String GhiChu) {

        CTHoaDon cthd = new CTHoaDon();
        cthd.setMaHD(MaHD);
        cthd.setMaDV(MaDV);
        cthd.setSoLuong(SoLuong);
        cthd.setDonGia(dongia);
        cthd.setGiamGia(GiamGia);
        cthd.setThanhTien(ThanhTien);
        cthd.setGhiChu(GhiChu);
        ctdao.insert(cthd);
    }
    private void mniXoaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniXoaDVActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        int row  = tblCTHoaDon.getSelectedRow();
        if(row >=0){
            model.removeRow(row);
            for (int i = 0; i < tblCTHoaDon.getRowCount(); i++) {                
               tblCTHoaDon.setValueAt(i + 1, i, 1);              
            }
        }
        lblTongTien.setText(XMoney.DinhDangTien(TinhTien()));
    }//GEN-LAST:event_mniXoaDVActionPerformed

    private void mniSuaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSuaDVActionPerformed
        // TODO add your handling code here:
            
            int viTri = tblCTHoaDon.getSelectedRow();
            SoLuong = Integer.parseInt(tblCTHoaDon.getValueAt(viTri, 4).toString());
            GiamGia = Float.parseFloat(tblCTHoaDon.getValueAt(viTri, 5).toString());
            GhiChu = tblCTHoaDon.getValueAt(viTri, 7).toString();
            
            jdlAddDichVu jdl = new jdlAddDichVu(this, true);
            jdl.setVisible(true);
//            if(Huy){
//                return;
//            }
            tblCTHoaDon.setValueAt(String.valueOf(SoLuong), viTri, 4);
            tblCTHoaDon.setValueAt(String.valueOf(GiamGia), viTri, 5);
            tblCTHoaDon.setValueAt(GhiChu, viTri, 7);
            double thanhtien, dongia;
            dongia = XMoney.ChuyenTien(tblCTHoaDon.getValueAt(viTri, 3).toString());
            thanhtien = dongia * SoLuong * (100 - GiamGia) / 100;
            tblCTHoaDon.setValueAt(String.valueOf(thanhtien), viTri, 6);
            tblCTHoaDon.setValueAt(GhiChu, viTri, 7);
            lblTongTien.setText(XMoney.DinhDangTien(TinhTien()));
    }//GEN-LAST:event_mniSuaDVActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
  
        
    }//GEN-LAST:event_formWindowOpened

    private void btnTaoHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonMoiActionPerformed
        // TODO add your handling code here:
        taomoiHoaDon();
    }//GEN-LAST:event_btnTaoHoaDonMoiActionPerformed

    private void txtGiamGiaHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaHoaDonKeyReleased
        // TODO add your handling code here:
        float KhachPhaiTra = (float) (XMoney.ChuyenTien(lblTongTien.getText()) * (100 - XMoney.ChuyenTien(txtGiamGiaHoaDon.getText())) /100);
        lblPhaiTra.setText(XMoney.DinhDangTien(KhachPhaiTra));
    }//GEN-LAST:event_txtGiamGiaHoaDonKeyReleased

    private void txtKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyReleased
        // TODO add your handling code here:
        float condu = (float) (XMoney.ChuyenTien(txtKhachTra.getText()) - XMoney.ChuyenTien(lblPhaiTra.getText()));
        lblTienThua.setText(XMoney.DinhDangTien(condu));
    }//GEN-LAST:event_txtKhachTraKeyReleased

    private void txtTimKiemKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKHKeyReleased
       fillComboBoxKhachHang(txtTimKiemKH.getText());
    }//GEN-LAST:event_txtTimKiemKHKeyReleased

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed
    private void ThemSanPhamTbCTHD(String maSP, String tenSP, int soLuong,
            float donGia,float giamGia, float thanhTien, String ghiChu) {

        DefaultTableModel tbModel = (DefaultTableModel) tblCTHoaDon.getModel();
        Object obj[] = new Object[8];

        obj[0] = tbModel.getRowCount() + 1;
        obj[1] = maSP;
        obj[2] = tenSP;
        obj[3] = XMoney.DinhDangTien(donGia);
        obj[4] = soLuong;
        obj[5] = giamGia;        
        obj[6] = XMoney.DinhDangTien(thanhTien);
        obj[7] = ghiChu;
        
        tbModel.addRow(obj);
    }
    private float TinhTien() {
        float tongTien = 0;
        for (int i = 0; i < tblCTHoaDon.getRowCount(); i++) {
            tongTien += XMoney.ChuyenTien(tblCTHoaDon.getValueAt(i, 6).toString());
        }

        return tongTien;
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(HoaDon2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HoaDon2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HoaDon2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HoaDon2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HoaDon2(String a).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoHoaDonMoi;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cboKhachHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblPhaiTra;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JMenuItem mniSuaDV;
    private javax.swing.JMenuItem mniXoaDV;
    private javax.swing.JPopupMenu pmnCTHD;
    private javax.swing.JTable tblCTHoaDon;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTextField txtGiamGiaHoaDon;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimDV;
    private javax.swing.JTextField txtTimKiemKH;
    // End of variables declaration//GEN-END:variables
private String SoHoaDon() {
        String soHoaDon = "";
        try {

            DateFormat dateFormat = new SimpleDateFormat("yyMMdd");

            Date d = new Date();

            soHoaDon = dateFormat.format(d);
//            System.out.println("soHoaDon: " + soHoaDon);
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
//                System.out.println("soHoaDon: " + soHoaDon);
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
}

