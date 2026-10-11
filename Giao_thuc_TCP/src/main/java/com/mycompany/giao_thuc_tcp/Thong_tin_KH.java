/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giao_thuc_tcp;

import UDP.Customer;
import java.io.*;
import java.net.*;

/**
 *
 * @author ADMIN
 */
public class Thong_tin_KH {
    public static String chuanHoaTen(String s){
        s = s.toLowerCase();
        String a[] = s.trim().split("\\s+");
        String hoTen = "";
        String ten = a[a.length - 1].toUpperCase();
        for(int i = 0; i < a.length - 1; i++){
            String x = a[i];
            x = x.substring(0,1).toUpperCase() + x.substring(1);
            hoTen = hoTen + x + " ";
        }
        return ten + ", " + hoTen.trim();
    }
    
    public static String chuyenNgaySinh(String s){
        String a[] = s.split("-");
        return a[1] + "/" + a[0] + "/" + a[2];
    }
    
    public static String taoUserName(String s){
        String a[] = s.toLowerCase().trim().split("\\s+");
        String res = "";
        for(int i = 0; i < a.length - 1; i++){
            res = res + a[i].substring(0,1);
        }
        res = res + a[a.length - 1];
        return res;
    }
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("36.50.135.242");
        int p = 2209;
        
        String code = ";B23DCCN628;PN7Za5Ps";
        DatagramPacket dpGui = new DatagramPacket(
                code.getBytes(),
                code.length(),
                ip,
                p
        );
        socket.send(dpGui);
        byte buffer[] = new byte[4096];

        DatagramPacket dpNhan = new DatagramPacket(
                buffer,
                buffer.length
        );

        socket.receive(dpNhan);
        String requestId = new String(
                dpNhan.getData(), 0, 8
        );
        ByteArrayInputStream bais = new ByteArrayInputStream(
                dpNhan.getData(),
                8,
                dpNhan.getLength() - 8
        );
        ObjectInputStream ois = new ObjectInputStream(bais);

        Customer customer = (Customer) ois.readObject();
        String originalName = customer.getName();
        String name = chuanHoaTen(customer.getName());
        customer.setName(name);

        String dayOfBirth = chuyenNgaySinh(customer.getDayOfBirth());
        customer.setDayOfBirth(dayOfBirth);

        String userName = taoUserName(originalName);
        customer.setUserName(userName);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(customer);
        oos.flush();
        
        byte objectData[] = baos.toByteArray();
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        output.write(requestId.getBytes());
        output.write(objectData);

        byte sendData[] = output.toByteArray();

        // B6: Gửi lại server
        DatagramPacket dpGui1 = new DatagramPacket(
                sendData,
                sendData.length,
                ip,
                p
        );

        socket.send(dpGui1);

        // B7: Đóng socket
        socket.close();
        
        
        
    }
    
}
