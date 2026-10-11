/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.giao_thuc_tcp;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author ADMIN
 */
public class Giao_thuc_TCP{

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        
        String ma = "B23DCCN628;7SGsylbe";
//        System.out.println(ma);
        out.write(ma.getBytes());
        out.flush();
        
        byte buffer[] = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len);
        s = s.replace(',', ' ').trim();
        String a[] = s.split("\\s+");
        ArrayList<Long> b = new ArrayList<>();
        for(String x : a){
            b.add(Long.parseLong(x));
        }
        Long res = Long.MAX_VALUE;
        Long a1 = 1L * (-1);
        Long a2 = 1L * (-1);
        Collections.sort(b);
        for(int i = 1; i < b.size(); i++){
            Long temp = b.get(i) - b.get(i-1);
            if(temp <= res){
                res = temp;
                a1 = b.get(i-1);
                a2 = b.get(i);
            }
        }
        String ans = res + "," + a1 + "," + a2;
//        System.out.println(ans);
        out.write(ans.getBytes());
        out.flush();
        
        in.close();
        out.close();
        socket.close();
        
        
    }
}
