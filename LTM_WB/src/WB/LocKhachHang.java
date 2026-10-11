/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WB;

import ptit.dblab.judge.network.server.soap.*;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class LocKhachHang {
    public static void main(String[] args) {
        ObjectService sv = new ObjectService();
        SoapObjectService port = sv.getSoapObjectServicePort();
        
        
        String ma = "B23DCCN628";
        String qCode = "4IMXYXNZ";
        
        List <Customer> list = port.requestListCustomer(ma, qCode);
        List <Customer> ans = new ArrayList<>();
        for(Customer c : list){
            if(c.getTotalSpent() > 500 && c.getPurchaseCount() >= 3){
                ans.add(c);
            }
        }
        port.submitListCustomer(ma, qCode, ans);
    }
    
}
