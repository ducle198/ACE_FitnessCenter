/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACE_FitnessCenter.helper;

import com.FitnessCenter.entity.NhanVien;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author ACER
 */
public class ShareHelper {

    public static final Image APP_ICON;
    public static final ImageIcon APP_ICON_1;

    static {

        String file = "/com/edusys/icon/fpt.png";      //icon là thư mục con của src
        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
        APP_ICON_1 = new ImageIcon(ShareHelper.class.getResource(file));
    }

    public static boolean saveLogo(File file) {
        File dir = new File("logos");  //khai báo thư mục logos ngang hàng với src
        // Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            // Copy vào thư mục logos (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static ImageIcon readLogo(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
    }

    public static NhanVien USER = null;

    public static void logoff() {
        ShareHelper.USER = null;
    }

    public static boolean authenticated() {
        return ShareHelper.USER != null;
    }
}
