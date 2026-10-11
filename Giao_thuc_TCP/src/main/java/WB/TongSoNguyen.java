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
public class TongSoNguyen {
    public static void main(String[] args) throws Exception{
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        
        String ma = "B23DCCN628";
        String qCode = "v9RMSzwj";
        
        List <Integer> s = port.getData(ma, qCode);
        int sum = 0;
        for(int i = 0; i < s.size(); i++){
            sum += s.get(i);
        }
        port.submitDataInt(ma, qCode, sum);
    }
    
}
