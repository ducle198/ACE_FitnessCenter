/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ACE_FitnessCenter.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class utilityHelper {

    public static String getRank(double diem) {
        String xepLoai = "Xuất sắc";
        if (diem < 0) {
            xepLoai = "Chưa nhập";

        } else if (diem < 3) {
            xepLoai = "Kém";

        } else if (diem < 5) {
            xepLoai = "Yếu";

        } else if (diem < 6.5) {

            xepLoai = "Trung bình";

        } else if (diem < 7.5) {
            xepLoai = "Khá";

        } else if (diem < 9) {
            xepLoai = "Giỏi";

        }
        return xepLoai;
    }

    public static boolean isValidDate(String inDate) {

        if (inDate == null) {
            return false;
        }

        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }

        dateFormat.setLenient(false);

        try {
            //parse the inDate parameter
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    //định dạng dd/MM/yyyy (hoặc d/M/yyyy nếu là số 0 đứng trước)
    public static boolean checkDate(JTextField txt) {
        String id = txt.getText();
//        String rgx = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
//        if (id.matches(rgx)) {
//            return true;
//        } else {
//            txt.setBackground(pink);
//            dialogHelper.alert(txt.getRootPane(), txt.getName() + " không đúng định dạng Date.");
//            return false;
//        }
        if (isValidDate(id)) {
            return true;
        } else {

            DialogHelper.alert(txt.getRootPane(), "Ngày không đúng định dạng dd/MM/yyyy !");
            return false;
        }
    }

    public static boolean checkMoTaCD(JTextArea txt) {

        String id = txt.getText();
        String rgx = ".{3,255}";
        if (id.matches(rgx)) {
            return true;
        } else {

            DialogHelper.alert(txt.getRootPane(), "Mô tả, ghi chú phải từ 3-255 kí tự!");
            return false;
        }
    }

    public static boolean checkEmail(JTextField txt) {

        String id = txt.getText();
        String rgx = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-zA-Z0-9]{2,10}(\\.[a-zA-Z0-9]{2,4}){1,2}$";
        if (id.matches(rgx)) {
            return true;
        } else {

            DialogHelper.alert(txt.getRootPane(), "Email không đúng định dạng!");
            return false;
        }
    }

    //gio là int >0
    public static boolean checkThoiLuong(JTextField txt) {

        try {
            int hour = Integer.parseInt(txt.getText());
            if (hour > 0) {
                return true;
            } else {

                DialogHelper.alert(txt.getRootPane(), txt.getName() + " Phải lớn hơn 0!");
                return false;
            }
        } catch (NumberFormatException e) {

            DialogHelper.alert(txt.getRootPane(), " Phải là số nguyên!");
            return false;
        }
    }

    //học phí là float >0
    public static boolean checkHocPhi(JTextField txt) {

        try {
            float hp = Float.parseFloat(txt.getText());
            if (hp > 0) {
                return true;
            } else {

                DialogHelper.alert(txt.getRootPane(), " Phải lớn hơn 0!");
                return false;
            }
        } catch (NumberFormatException e) {

            DialogHelper.alert(txt.getRootPane(), " Phải là số thực!");
            return false;
        }
    }

    public static boolean checkDonGia(JTextField txt) {

        try {
            float hp = Float.parseFloat(txt.getText());
            if ((hp >= 0 && hp <= 10000000) ) {
                return true;
            } else {

                DialogHelper.alert(txt.getRootPane(), "Đơn Giá Phải >0");
                return false;
            }
        } catch (NumberFormatException e) {

            DialogHelper.alert(txt.getRootPane(), "Đơn Giá Phải là số thực!");
            return false;
        }
    }

    public static boolean checkNullText(JTextField txt) {

        if (txt.getText().trim().length() > 0) {
            return true;
        } else {

            DialogHelper.alert(txt.getRootPane(), "Không được để trống ô nhập thông tin nào!");
            return false;
        }
    }

    public static boolean checkNullPass(JPasswordField txt) {
        if (txt.getPassword().length > 0) {
            return true;
        } else {
            DialogHelper.alert(txt.getRootPane(), "Không được để trống mật khẩu!");
            return false;
        }
    }
}
