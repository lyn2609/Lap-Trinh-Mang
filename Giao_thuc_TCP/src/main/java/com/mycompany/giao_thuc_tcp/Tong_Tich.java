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
public class Tong_Tich {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        String ma = "B23DCCN628;qgSu69nv";
        out.writeUTF(ma);
        
        int a = in.readInt();
        int b = in.readInt();
        int tong = a + b;
        int tich = a * b;
        
        out.writeInt(tong);
        out.writeInt(tich);
        out.flush();
        
        in.close();
        out.close();
        socket.close();
    }
}
