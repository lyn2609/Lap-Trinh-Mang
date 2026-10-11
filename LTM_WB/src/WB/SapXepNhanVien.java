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
public class SapXepNhanVien {
    public static void main(String[] args) {
        ObjectService sv = new ObjectService();
        SoapObjectService port = sv.getSoapObjectServicePort();
        
        String ma = "B23DCCN628";
        String qCode = "LcOSi4Y3";
        
        List <EmployeeY> list = port.requestListEmployeeY(ma, qCode);
        Collections.sort(list, new Comparator<EmployeeY>(){
            @Override
            public int compare(EmployeeY e1, EmployeeY e2){
                Date d1 = e1.getStartDate().toGregorianCalendar().getTime();
                Date d2 = e2.getStartDate().toGregorianCalendar().getTime();
                return d1.compareTo(d2);
            }
        });
        port.submitListEmployeeY(ma, qCode, list);
    }
}
