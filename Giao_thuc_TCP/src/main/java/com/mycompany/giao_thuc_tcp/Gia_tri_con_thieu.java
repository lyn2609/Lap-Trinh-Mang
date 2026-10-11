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
public class Gia_tri_con_thieu{
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("36.50.135.242");
        int p = 2207;

        String code = ";B23DCCN628;Kjiyoo4X";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), ip, p);
        socket.send(dpGui);

        byte buffer[] = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);

        String s1 = new String(dpNhan.getData(), 0, dpNhan.getLength());
        String a[] = s1.split(";");
        String requestId = a[0];
        int n = Integer.parseInt(a[1]);
        String b[] = a[2].split(",");
        boolean check[] = new boolean[n+1];
        for(int i = 0; i < b.length; i++){
            int x = Integer.parseInt(b[i]);
            if(x >= 1 && x <= n){
                check[x] = true;
            }
        }
        String res = "";
        for(int i = 1; i <= n; i++){
            if(check[i] == false){
                if(res.length() > 0){
                    res = res + ",";
                }
                res = res + i;
            }
        }
        String ans = requestId + ";" + res;
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), ip, p);
        socket.send(dpGui1);
        socket.close();
        
    }
    
}
