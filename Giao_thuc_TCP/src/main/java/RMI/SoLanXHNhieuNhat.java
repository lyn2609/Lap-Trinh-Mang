/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.*;
import java.util.*;
import java.rmi.registry.*;
/**
 *
 * @author ADMIN
 */
public class SoLanXHNhieuNhat{
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        String ma = "B23DCCN628";
        String qCode = "0430iXsc";
        byte a[] = sv.requestData(ma, qCode);
        
        byte maxValue = a[0];
        int maxCount = 0;
        for(int i = 0; i < a.length; i++){
            int cnt = 0;
            for(int j = 0; j < a.length; j++){
                if (a[i] == a[j]){
                    cnt++;
                }
            }
            if(cnt > maxCount){
                maxCount = cnt;
                maxValue = a[i];
            }
        }
        byte ans[] = {maxValue, (byte)maxCount};
        sv.submitData(ma, qCode, ans);
    }
}
