/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giao_thuc_tcp;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author ADMIN
 */
public class Xy_ly_chuan_hoa {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("36.50.135.242");
        int p = 2208;
        
        String code = ";B23DCCN628;Caavambv";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), ip, p);
        socket.send(dpGui);
        
        byte buffer[] = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        String s = new String(dpNhan.getData(),0, dpNhan.getLength());
        String a[] = s.split(";");
        String requestId = a[0];
        String b = a[1];
        String word[] = b.split(" ");
        String res = "";
        for(int i = 0; i < word.length; i++){
            String x = word[i];
            if(x.length() > 0){
                x = x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase();
            }
            if(res.length() > 0){
                res = res + " ";
            }
            res = res + x;
        }
        String ans = requestId + ";" + res;
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), ip, p);
        socket.send(dpGui1);
        
        socket.close();
    }
    
}
