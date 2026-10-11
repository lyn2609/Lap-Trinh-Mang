/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;

/**
 *
 * @author ADMIN
 */
public class XuLyTicket {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        String ma = "B23DCCN628";
        String qCode = "1uYWCcVz";
        
        TicketSla t = (TicketSla) sv.requestObject(ma, qCode);
        
        String p = t.getPriority();
        int h = t.getOpenedHoursAgo();      
        
        boolean breached = false;
        if(p.equals("CRITICAL")) breached = h > 2;
        else if(p.equals("HIGH")) breached = h > 8;
        else if(p.equals("MEDIUM")) breached = h > 24;
        else if(p.equals("LOW")) breached = h > 72;
        
        String action = "";
        if(!breached){
            action = "MONITOR";
        }
        else if(p.equals("CRITICAL") || h > 96){
            action = "ESCALATE_L2";
        }
        else{
            action = "ESCALATE_L1";
        }
        
        t.setBreached(breached);
        t.setAction(action);
        
        sv.submitObject(ma, qCode, t);
    }
}
