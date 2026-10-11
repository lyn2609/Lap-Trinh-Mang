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
public class TCP_Character_Stream {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter (new OutputStreamWriter(socket.getOutputStream()));
        
        String ma = "B23DCCN628;G3sxKMlq";
        out.write(ma);
        out.newLine();
        out.flush();
        
        String s = in.readLine();
        String a[] = s.split(",");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++){
            if(a[i].endsWith(".edu")){
                if(sb.length() > 0){
                    sb.append(",");
                }
                sb.append(a[i]);
            }
        }
        out.write(sb.toString());
        out.newLine();
        out.flush();
        
        in.close();
        out.close();
        socket.close();
                
           
    }
    
}
