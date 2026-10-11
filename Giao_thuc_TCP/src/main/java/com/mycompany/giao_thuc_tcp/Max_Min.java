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
public class Max_Min {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("36.50.135.242");
        int p = 2207;
        
        String code = ";B23DCCN628;t332iLDH";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), ip, p);
        socket.send(dpGui);
        
        byte buffer[] = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        String s = new String(dpNhan.getData(), 0, dpNhan.getLength());
        String a[] = s.split(";");
        String requestId = a[0];
        String b[] = a[1].split(",");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < b.length; i++){
            int x = Integer.parseInt(b[i]);
            if(x > max){
                max = x;
            }
            if(x < min){
                min = x;
            }
        }
        String ans = requestId + ";" + max + "," + min;
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), ip, p);
        socket.send(dpGui1);
        
        socket.close();
        
    }
    
}
