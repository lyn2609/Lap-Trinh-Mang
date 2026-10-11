/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.giao_thuc_tcp;

import TCP.Laptop;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author ADMIN
 */
public class Sua_Laptop{
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2209);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        
        String ma = "B23DCCN628;WZjs6Zij";
        out.writeObject(ma);
        out.flush();
        
        Laptop laptop = (Laptop) in.readObject();
        String s[] = laptop.name.split("\\s+");
        String tmp = s[0];
        s[0] = s[s.length - 1];
        s[s.length - 1] = tmp;
        
        laptop.name = String.join(" ", s);
        
        String quantity = Integer.toString(laptop.quantity);
        quantity = new StringBuilder(quantity).reverse().toString();
        laptop.quantity = Integer.parseInt(quantity);
        
        out.writeObject(laptop);
        out.flush();
        
        in.close();
        out.close();
        socket.close();
        
        
        
    }
    
}
