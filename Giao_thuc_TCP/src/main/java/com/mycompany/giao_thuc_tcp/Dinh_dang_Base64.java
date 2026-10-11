package com.mycompany.giao_thuc_tcp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Dinh_dang_Base64 {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("36.50.135.242", 2210);
        socket.setSoTimeout(5000);

        // =========================================
        // 1. Gửi mã sinh viên + mã câu hỏi
        // =========================================

        GZIPOutputStream gzipOut =
                new GZIPOutputStream(socket.getOutputStream());

        BufferedWriter writer =
                new BufferedWriter(
                        new OutputStreamWriter(
                                gzipOut,
                                StandardCharsets.UTF_8
                        )
                );

        String ma = "B23DCCN628;CtWh7fsb";

        writer.write(ma);
        writer.newLine();
        writer.flush();

        // Kết thúc GZIP message thứ nhất
        gzipOut.finish();

        System.out.println("Đã gửi: " + ma);


        // =========================================
        // 2. Nhận dữ liệu từ server
        // =========================================

        GZIPInputStream gzipIn =
                new GZIPInputStream(socket.getInputStream());

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                gzipIn,
                                StandardCharsets.UTF_8
                        )
                );

        String s = reader.readLine();

        System.out.println("Server gửi: " + s);


        // =========================================
        // 3. Đảo ngược chuỗi
        // =========================================

        String rev =
                new StringBuilder(s)
                        .reverse()
                        .toString();

        System.out.println("Đảo ngược: " + rev);


        // =========================================
        // 4. Base64
        // =========================================

        String ans =
                Base64.getEncoder()
                        .encodeToString(
                                rev.getBytes(StandardCharsets.UTF_8)
                        );

        System.out.println("Base64: " + ans);


        // =========================================
        // 5. Tạo kết quả
        // =========================================

        String res = rev + "|" + ans;

        System.out.println("Kết quả: " + res);


        // =========================================
        // 6. Gửi kết quả bằng GZIP mới
        // =========================================

        GZIPOutputStream gzipOut2 =
                new GZIPOutputStream(socket.getOutputStream());

        BufferedWriter writer2 =
                new BufferedWriter(
                        new OutputStreamWriter(
                                gzipOut2,
                                StandardCharsets.UTF_8
                        )
                );

        writer2.write(res);
        writer2.newLine();
        writer2.flush();

        // Kết thúc GZIP message thứ hai
        gzipOut2.finish();


        // =========================================
        // 7. Đóng kết nối
        // =========================================

        socket.close();
    }
}