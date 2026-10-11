/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WB;

import ptit.dblab.judge.network.server.soap.*;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class DemNguyenAm {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B23DCCN628", qCode = "pygcvLGo";

        // Tạo đối tượng dịch vụ từ web service
        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();

        // a. Nhận danh sách chuỗi từ server
        List<String> a = port.requestStringArray(msv, qCode);

        // b1. Sắp xếp: theo số nguyên âm, nếu bằng nhau thì theo từ điển
        Collections.sort(a, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (dem(s1) != dem(s2)) {
                    return dem(s1) - dem(s2);
                }
                return s1.compareTo(s2);
            }
        });

        // b2. Gom các từ đứng liền nhau có cùng số nguyên âm thành 1 nhóm
        List<String> kq = new ArrayList<>();
        String nhom = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (dem(a.get(i)) == dem(a.get(i - 1))) {
                nhom += "," + a.get(i);      // cùng nhóm thì nối thêm
            } else {
                kq.add(nhom);                // khác nhóm thì lưu nhóm cũ
                nhom = a.get(i);             // rồi bắt đầu nhóm mới
            }
        }
        kq.add(nhom);                        // lưu nhóm cuối cùng

        // c. Gửi kết quả
        port.submitCharacterStringArray(msv, qCode, kq);
        System.out.println(kq);
    }

    // Hàm đếm số nguyên âm trong chuỗi
    private static int dem(String str) {
        int cnt = 0;
        String ngAm = "aeiouAEIOU";
        for (int i = 0; i < str.length(); i++) {
            if (ngAm.indexOf(str.charAt(i)) != -1) cnt++;
        }
        return cnt;
    }
}
