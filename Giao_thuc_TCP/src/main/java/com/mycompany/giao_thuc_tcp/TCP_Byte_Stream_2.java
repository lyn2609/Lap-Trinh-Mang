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
public class TCP_Byte_Stream_2 {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        
        String ma = "B23DCCN628;714hV13c";
        out.write(ma.getBytes());
        out.flush();
        
        byte buffer[] = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len);
        s = s.trim();
        String a[] = s.split(",");
        ArrayList <Integer> b = new ArrayList<>();
        for(String x : a){
            b.add(Integer.parseInt(x));
        }
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int idx = -1;
        for(int i = 0; i < b.size(); i++){
            if(b.get(i) >= max1){
                max2 = max1;
                max1 = b.get(i);       
            }
            else if(b.get(i) >= max2 && b.get(i) != max1){
                max2 = b.get(i);
                idx = i;
            }
        }
        String ans = max2 + "," + idx;
        out.write(ans.getBytes());
        out.flush();
        
        in.close();
        out.close();
        socket.close();
    }
     
     
    
}
