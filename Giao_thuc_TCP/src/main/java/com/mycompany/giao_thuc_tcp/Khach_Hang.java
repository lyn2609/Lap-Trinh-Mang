/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giao_thuc_tcp;

import TCP.Customer;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author ADMIN
 */
public class Khach_Hang {
    public static String chuan_Hoa_Ten(String s){
        s = s.trim().toLowerCase();
        String a[] = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        a[a.length - 1] = a[a.length -1].toUpperCase();
        sb = sb.append(a[a.length -1]).append(", ");
        for(int i = 0; i < a.length - 1; i++){
            String x = a[i];
            sb.append(Character.toUpperCase(x.charAt(0)))
                    .append(x.substring(1))
                    .append(" ");
        }
        return sb.toString().trim();
    }
    
    public static String chuan_Hoa_Ngay(String s){
        String a[] = s.trim().split("-");
        int d = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);
        int y = Integer.parseInt(a[2]);
        return String.format("%02d/%02d/%04d", m,d,y);
    }
    public static String chuan_Tai_Khoan(String s){
        s = s.trim().toLowerCase();
        String a[] = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length - 1; i++){
            sb.append(a[i].charAt(0));
        }
        sb.append(a[a.length - 1]);
        return sb.toString().trim();
    }
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2209);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        
        String ma = "B23DCCN628;8wfhQ5jh";
        out.writeObject(ma);
        out.flush();
        
        Customer customer = (Customer) in.readObject();
        String s = customer.name;
        customer.name = chuan_Hoa_Ten(customer.name);
        customer.dayOfBirth = chuan_Hoa_Ngay(customer.dayOfBirth);
        customer.userName = chuan_Tai_Khoan(s);
        
        out.writeObject(customer);
        out.flush();
        
        in.close();
        out.close();
        socket.close();
        
    }
    
}
