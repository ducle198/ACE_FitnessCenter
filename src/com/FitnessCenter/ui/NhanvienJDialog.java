/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FitnessCenter.ui;

import com.FitnessCenter.dao.NhanVienDao;
import com.FitnessCenter.entity.NhanVien;
import com.FitnessCenter.utils.MsgBox;
import com.FitnessCenter.utils.XDate;
import com.FitnessCenter.utils.XImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class NhanvienJDialog extends javax.swing.JDialog {
  JFileChooser fileChooser = new JFileChooser();
    NhanVienDao dao = new NhanVienDao();
    int row = -1;

    /**
     * Creates new form NhanvienJDialog
     */
    public NhanvienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        innit();
    }

    void innit() {
        setLocationRelativeTo(null);
        this.fillTable();
        this.row = -1;
        this.updateStatus();

    }

    private void timKiem() {
         
        this.fillTable();
        this.FindByID();
        this.clearForm();
        this.row = -1;
        this.updateStatus(); 

    }

    void insert() {
        NhanVien nv = getForm();

        try {
            dao.insert(nv);
            this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!" + e);
        }

    }

    void update() {
        NhanVien nv = getForm();

        try {
            dao.update(nv);
            this.fillTable();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {

            MsgBox.alert(this, "Cập nhật thất bại!");
            e.printStackTrace();
        }

    }

    void delete() {
        String manv = txtMaNV.getText();
        if (MsgBox.confirm(this, "Bạn có thực sự muốn xóa khách hàng này?")) {
            try {
                dao.delete(manv);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thát bại!");
                e.printStackTrace();
            }
        }
    }

    void clearForm() {
       txtMaNV.setText("");
       txtMatKhau.setText("");
       txtTenNV.setText("");
       txtNgaySinh.setText("");
       txtSDT.setText("");
       txtEmail.setText("");
       txtChucVu.setText("");
       txtCMND.setText("");
       txtDiaChi.setText("");
       txtKinhNghiem.setText("");
       
        this.row = -1;
        this.updateStatus();
    }

    void edit() {
        String manv = (String) tblNhanVien.getValueAt(this.row, 0);
        NhanVien nv = dao.selectByID(manv);
        if (nv != null) {
            this.setForm(nv);
            jTabbedPane1.setSelectedIndex(1);
            this.updateStatus();

        }
    }

    void first() {
        this.row = 0;
        this.edit();
    }

    void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
        }
    }

    void next() {
        if (this.row < tblNhanVien.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }

    void last() {
        this.row = tblNhanVien.getRowCount() - 1;
        this.edit();
    }

    void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV());
        txtMatKhau.setText(nv.getMatKhau());
        txtTenNV.setText(nv.getTenNV());
        rdoNam.setSelected(nv.isGioiTinh());
        rdoNu.setSelected(!nv.isGioiTinh());
       
        txtCMND.setText(nv.getIdCard());
        txtNgaySinh.setText(XDate.toString(nv.getNgaySinh()));
        txtDiaChi.setText(nv.getDiaChi());
        txtSDT.setText(nv.getPhoneNumber());
        txtEmail.setText(nv.getEmail());
        txtChucVu.setText(nv.getChucVu());
        txtKinhNghiem.setText(nv.getKinhNghiem() + "");
   

    }

    NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMaNV.getText());
        nv.setMatKhau(txtMatKhau.getText());
        nv.setTenNV(txtTenNV.getText());
        nv.setGioiTinh(rdoNam.isSelected());
        nv.setIdCard(txtCMND.getText());
        nv.setNgaySinh(XDate.toDate(txtNgaySinh.getText()));
        nv.setDiaChi(txtDiaChi.getText());
        nv.setPhoneNumber(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setTrangThai(rdoHoatDong.isSelected());
        nv.setHinhAnh(lblhinhanh.getToolTipText());
        nv.setChucVu(txtChucVu.getText());
        nv.setKinhNghiem(Float.parseFloat(txtKinhNghiem.getText()));
        return nv;
    }

    void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblhinhanh.setIcon(icon);
            lblhinhanh.setToolTipText(file.getName());
        }
    }
    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblNhanVien.getRowCount() - 1);
        //Trang thai form
        txtMaNV.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        //Trang thai dieu huong
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    void  FindByID(){
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0); // xoa tat ca cac hang
        try {
            String keyword = txtTimKiem.getText();
            Boolean kq = false;
            List<NhanVien> list = dao.selectByKeyword(keyword); //truy vấn người học có tên chứa từ khóa tìm kiếm
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getMatKhau(),
                    nv.getTenNV(),
                    nv.isGioiTinh() ? "Nam" : "Nữ",
                    nv.getIdCard(),
                    XDate.toString(nv.getNgaySinh()),
                    nv.getDiaChi(),
                    nv.getPhoneNumber(),
                    nv.getEmail(),
                    nv.getChucVu(),
                    nv.getKinhNghiem(),
                    nv.isTrangThai() ? "Đang hoạt động" : " Nghỉ"};
                model.addRow(row); //them 1 hang vao table
                kq = true;
            }
            if (kq) {
                MsgBox.alert(this, "Tìm thấy nhân viên cần tìm!");
            } else {
                MsgBox.alert(this, "Không tìm thấy nhân viên cần tìm , vui lòng tìm kiếm lại!");
                txtTimKiem.requestFocus();
                return;
            }
            
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0); // xoa tat ca cac hang
        try {
            String keyword = txtTimKiem.getText();
            List<NhanVien> list = dao.selectByKeyword(keyword); //truy vấn người học có tên chứa từ khóa tìm kiếm
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getMatKhau(),
                    nv.getTenNV(),
                    nv.isGioiTinh() ? "Nam" : "Nữ",
                    nv.getIdCard(),
                    XDate.toString(nv.getNgaySinh()),
                    nv.getDiaChi(),
                    nv.getPhoneNumber(),
                    nv.getEmail(),
                    nv.getChucVu(),
                    nv.getKinhNghiem(),
                    nv.isTrangThai() ? "Đang hoạt động" : " Nghỉ"};
                model.addRow(row); //them 1 hang vao table
            }
            
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    
    public boolean validateForm(boolean chk) {
        // Mã nhân viên
        if (txtMaNV.getText().length() == 0) {
            MsgBox.alert(this, "Không được phép để trống mã nhân viên!");
            txtMaNV.requestFocus();
            return false;
        } else if (txtMaNV.getText().length() > 7) {
            MsgBox.alert(this, "Mã nhân viên không được quá 7 ký tự!");
            txtMaNV.requestFocus();
            return false;
        }
        // check mật khẩu 
        if (txtMatKhau.getText().length() == 0) {
            MsgBox.alert(null, "Mật khẩu không được để trống");
            txtMatKhau.setText("");
            txtMatKhau.requestFocus();
            return false;

        } 
        // check TênNV
        if (txtTenNV.getText().length()==0) {
            MsgBox.alert(null, "Tên nhân viên không được để trống");
            txtTenNV.setText("");
            txtTenNV.requestFocus();
            return false;
        } 
        
     
         if (txtCMND.getText().length() == 0) {
            MsgBox.alert(this, "Không được phép để trống căn cước công dân !");
            txtCMND.requestFocus();
            return false;
        }
       
        
        if (txtNgaySinh.getText().length() == 0) {
            MsgBox.alert(this, "Không được phép để trống Ngày Sinh !");
            txtNgaySinh.setText("");
            txtNgaySinh.requestFocus();
            return false;
        }
        if (txtChucVu.getText().length() == 0) {
            MsgBox.alert(this, "Không được phép để trống chức vụ !");
            txtChucVu.setText("");
            txtChucVu.requestFocus();
            return false;
        }

        //check ngày
        try {
            String ns = txtNgaySinh.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            Date d = sdf.parse(ns);
        } catch (Exception e) {
            MsgBox.alert(this, "Vui lòng nhập đúng định dạng dd/MM/YYYY");
            txtNgaySinh.setText("");
            txtNgaySinh.requestFocus();

            return false;
        }

        if (txtDiaChi.getText().length() == 0) {
            MsgBox.alert(this, "Không được phép để trống địa chỉ!");
            txtDiaChi.requestFocus();
            return false;
        } else if (txtSDT.getText().length() == 0) {
            MsgBox.alert(this, "Không được để trống số điện thoại!");
            txtSDT.requestFocus();
            return false;
        } else if (!txtSDT.getText().matches("(03|05|07|08|09)\\d{8,9}")) {
            MsgBox.alert(this, "Không đúng định dạng số điện thoại!");
            txtSDT.requestFocus();
            return false;
        } else if (txtEmail.getText().length() == 0) {
            MsgBox.alert(this, "Bạn chưa nhập Email!");
            txtEmail.requestFocus();
            return false;
        } else if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
            MsgBox.alert(this, "Email không đúng định dạng!");
            txtEmail.requestFocus();
            return false;
        } else if (txtChucVu.getText().length() == 0) {
            MsgBox.alert(this, "Không được để trống chức vụ!");
            txtChucVu.requestFocus();
            return false;
        } else if (txtKinhNghiem.getText().length() == 0) {
            MsgBox.alert(this, "Không được để trống kinh nghiệm làm việc!");
            txtKinhNghiem.requestFocus();
            return false;
        } 
        else if (lblhinhanh.getToolTipText() == null) {
            MsgBox.alert(this, "Bạn chưa chọn ảnh");              
            return false;
        }

        List<NhanVien> list = dao.selectAll();
        if (chk) {
            for (NhanVien cd : list) {
                if (txtMaNV.getText().equals(cd.getMaNV())) {
                    MsgBox.alert(this, "Mã nhân viên đã tồn tại");
                    txtMaNV.requestFocus();
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

        btngphai = new javax.swing.ButtonGroup();
        jSpinner1 = new javax.swing.JSpinner();
        btngtrangthai = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        tabs = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblhinhanh = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rdoNghi = new javax.swing.JRadioButton();
        rdoHoatDong = new javax.swing.JRadioButton();
        txtMatKhau = new javax.swing.JPasswordField();
        txtKinhNghiem = new javax.swing.JTextField();
        txtChucVu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Nhân Viên");
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Pass", "Tên NV", "Giới Tính", "CMND", "Ngày Sinh", "Địa Chỉ", "SDT", "Email", "Chức Vụ", "Kinh Nghiệm", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách", jPanel2);

        tabs.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setText("ID :");

        jLabel4.setText("Mật khẩu:");

        jLabel5.setText("Giới Tính");

        btngphai.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.setToolTipText("");

        btngphai.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel6.setText("Chức vụ:");

        jLabel7.setText("Tên NV");

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/previous_button.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/next_button.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/first_button.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/last_button.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        jLabel3.setText("CMND");

        jLabel9.setText("Ngày Sinh");

        jLabel10.setText("Địa Chỉ");

        jLabel11.setText("SDT :");

        jLabel12.setText("Email");

        lblhinhanh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 51)));
        lblhinhanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhinhanhMouseClicked(evt);
            }
        });

        jLabel8.setText("Kinh Nghiệm :");

        jLabel14.setText("Trạng Thái ");

        btngtrangthai.add(rdoNghi);
        rdoNghi.setText("Nghỉ");

        btngtrangthai.add(rdoHoatDong);
        rdoHoatDong.setText("Hoạt Động");

        javax.swing.GroupLayout tabsLayout = new javax.swing.GroupLayout(tabs);
        tabs.setLayout(tabsLayout);
        tabsLayout.setHorizontalGroup(
            tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabsLayout.createSequentialGroup()
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tabsLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblhinhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(tabsLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(29, 29, 29)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabsLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtKinhNghiem)))
                        .addGap(120, 120, 120)
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)))
                    .addGroup(tabsLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(20, 20, 20)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoi))
                    .addGroup(tabsLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoHoatDong)))
                .addGap(55, 55, 55)
                .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addGap(32, 32, 32)
                        .addComponent(btnPrev)
                        .addGap(37, 37, 37)
                        .addComponent(btnNext)
                        .addGap(34, 34, 34)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabsLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(tabsLayout.createSequentialGroup()
                                    .addComponent(rdoNam)
                                    .addGap(26, 26, 26)
                                    .addComponent(rdoNu))
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDiaChi))
                            .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        tabsLayout.setVerticalGroup(
            tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabsLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(17, 17, 17)
                .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(tabsLayout.createSequentialGroup()
                        .addComponent(lblhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tabsLayout.createSequentialGroup()
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNam)
                                .addComponent(rdoNu)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabsLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel9))
                            .addGroup(tabsLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(5, 5, 5)))
                .addGap(10, 10, Short.MAX_VALUE)
                .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabsLayout.createSequentialGroup()
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtKinhNghiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118))
                    .addGroup(tabsLayout.createSequentialGroup()
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(69, 69, 69)))
                .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(btnFirst)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
            .addGroup(tabsLayout.createSequentialGroup()
                .addGap(417, 417, 417)
                .addGroup(tabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoHoatDong)
                    .addComponent(rdoNghi)
                    .addComponent(jLabel14))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        tabsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtKinhNghiem, txtSDT});

        jTabbedPane1.addTab("Cập nhật", tabs);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 51));
        jLabel13.setText("Trở Lại");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(315, 315, 315)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timKiem();

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (validateForm(true)) {
            insert();
            jTabbedPane1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (validateForm(false)) {
            update();
            jTabbedPane1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblNhanVien.rowAtPoint(evt.getPoint());
            if (this.row >= 0) {
                this.edit();
                jTabbedPane1.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void lblhinhanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhinhanhMouseClicked
        // TODO add your handling code here:
        this.chonAnh();
    }//GEN-LAST:event_lblhinhanhMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

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
            java.util.logging.Logger.getLogger(NhanvienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanvienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanvienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanvienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhanvienJDialog dialog = new NhanvienJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup btngphai;
    private javax.swing.ButtonGroup btngtrangthai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblhinhanh;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JPanel tabs;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtKinhNghiem;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

   
}
