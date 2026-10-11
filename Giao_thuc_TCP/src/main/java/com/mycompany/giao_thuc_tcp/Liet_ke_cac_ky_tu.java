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
public class Liet_ke_cac_ky_tu {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2208);
        BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter (new OutputStreamWriter(socket.getOutputStream()));
        
        String ma = "B23DCCN628;vaGL2B8C";
        out.write(ma);
        out.newLine();
        out.flush();
        
        String s = in.readLine();
        s = s.trim();
        int dd[] = new int[256];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)){
                dd[c]++;
            }    
        } 
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)){
                if(dd[c] > 1){
                    boolean check = false;
                    for(int j = 0; j < i; j++){
                        if(s.charAt(j) == c){
                            check = true;
                            break;
                        }
                    }
                    if(!check){
                        ans.append(c).append(":").append(dd[c]).append(",");
                    }
                   
                } 
            }
        }
        out.write(ans.toString());
        out.newLine();
        out.flush();
        
        in.close();
        out.close();
        socket.close();
    }
        
}
