/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giao_thuc_tcp;

import java.util.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author ADMIN
 */
public class Ky_Tu_Xuat_Hien_Nhieu_Nhat {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("36.50.135.242");
        int p = 2208;
        
        String code = ";B23DCCN628;8v0rLKIa";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), ip, p);
        socket.send(dpGui);
        
        byte buffer[] = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        
        String s = new String(dpNhan.getData(), 0, dpNhan.getLength());
        String a[] = s.split(";");
        String requestId = a[0];
        String b = a[1];
        int max = 0;
        char res = b.charAt(0);
        
        for(int i = 0; i < b.length(); i++){
            char c = b.charAt(i);
            int cnt = 0;
            for(int j = 0; j < b.length(); j++){
                if(b.charAt(j) == c){
                    cnt++;
                }
            }
            if(cnt > max){
                max = cnt;
                res = c;
            }
        }
        String idex = "";
        for(int i = 0; i < b.length(); i++){
            if(b.charAt(i) == res){
                idex = idex + (i + 1) + ",";
            }
        }
        String ans = requestId + ";" + res + ":" + idex;
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), ip, p);
        socket.send(dpGui1);
        socket.close();
    }
    
}
