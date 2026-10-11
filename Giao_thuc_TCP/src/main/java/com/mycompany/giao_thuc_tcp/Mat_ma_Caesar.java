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
public class Mat_ma_Caesar {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        String ma = "B23DCCN628;I9Dkrqox";
        out.writeUTF(ma);
        out.flush();
        
        String a = in.readUTF();
        StringBuilder sb = new StringBuilder();
        int s = in.readInt();
        for(int i = 0; i < a.length(); i++){
            char x = a.charAt(i);
            if(Character.isUpperCase(x)){
                x = (char) ('A' + (x - 'A' - s + 26) % 26);
            }
            else if(Character.isLowerCase(x)){
                x = (char) ('a' + (x - 'a' - s + 26) % 26);
            }
            sb.append(x);
        }
        out.writeUTF(sb.toString());
        out.flush();
        
        in.close();
        out.close();
        socket.close();
    }
    
}
