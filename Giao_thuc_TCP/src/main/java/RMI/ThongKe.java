/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.registry.*;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class ThongKe {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String ma = "B23DCCN628";
        String qCode = "KK2TyH7k";
        String s = (String) sv.requestData(ma, qCode);
        
        String a[] = s.split(",");
        int n = a.length;
        double b[] = new double[n];
        for(int i = 0; i < n; i++){
            b[i] = Double.parseDouble(a[i].trim());
        }
        double sum = 0;
        for(double x : b){
            sum += x;
        }
        double avg = sum / n;
        double tong = 0;
        for(double x : b){
            tong += (x - avg)*(x - avg);
        }
        double stddev = Math.sqrt(tong/n);
        
        Arrays.sort(b);
        int idx = (int)Math.ceil(n * 0.95) - 1;
        double p95 = b[idx];
        String kq = String.format("average=%.2f;stddev=%.2f;p95=%.2f", avg, stddev, p95);
        sv.submitData(ma, qCode, kq);
    }
    
}
