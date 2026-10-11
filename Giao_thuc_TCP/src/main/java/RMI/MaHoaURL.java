/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.*;
import java.util.*;
import java.rmi.registry.*;
import java.net.URI;

/**
 *
 * @author ADMIN
 */
public class MaHoaURL {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService sv = (CharacterService)rg.lookup("RMICharacterService");
        String ma = "B23DCCN628";
        String qCode = "VMPDvjZX";
        String s = sv.requestCharacter(ma, qCode);
        String ans = new URI(null, null, s, null).toASCIIString();
        sv.submitCharacter(ma, qCode, ans);
    }
    
}
