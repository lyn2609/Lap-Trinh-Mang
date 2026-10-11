/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giao_thuc_tcp;

import java.io.*;
import java.net.*;
import java.util.*;
import UDP.Student;

/**
 *
 * @author ADMIN
 */
public class Chuan_hoa_ten {
    public static String chuanHoaTen(String s){
        s = s.toLowerCase();
        String a[] = s.trim().split("\\s+"); 
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < a.length; i++){
            String x = a[i];
            x = x.substring(0, 1).toUpperCase() + x.substring(1);
            if(res.length() > 0){
                res.append(" ");
            }
            res.append(x);
        }
        return res.toString();
    }
    
    public static String taoEmail(String s){
        String a[] = s.trim().split("\\s+");
        String ten = a[a.length - 1].toLowerCase();
        String vietTat = "";
        for(int i = 0; i < a.length - 1; i++){
            vietTat = vietTat + a[i].substring(0,1).toLowerCase();
        }
        return ten + vietTat + "@ptit.edu.vn";
    }
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("36.50.135.242");
        int p = 2209;
        
        String code = ";B23DCCN628;djaF9Onr";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), ip, p);
        socket.send(dpGui);
        
        byte buffer[] = new byte[4096];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        
        String requestId = new String(dpNhan.getData(), 0, 8);
        ByteArrayInputStream bais = new ByteArrayInputStream(dpNhan.getData(), 8, dpNhan.getLength() - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Student student = (Student) ois.readObject();
        
        String name = chuanHoaTen(student.getName());
        student.setName(name);
        
        String email = taoEmail(name);
        student.setEmail(email);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(student);
        oos.flush();
        byte objectData[] = baos.toByteArray();
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(requestId.getBytes());
        output.write(objectData);
        byte sendData[] = output.toByteArray();
        
        DatagramPacket dpGui1 = new DatagramPacket(sendData, sendData.length, ip, p);
        socket.send(dpGui1);
        socket.close();
        
    }
    
}
