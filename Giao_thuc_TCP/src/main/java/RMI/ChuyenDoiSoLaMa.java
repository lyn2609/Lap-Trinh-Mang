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
public class ChuyenDoiSoLaMa {
    public static String LaMa(int n){
        int[] giaTri = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4,1
        };
        String kyTu[] = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "XI", "V", "IV", "I"
        };
        String ans = "";
        for(int i = 0; i < giaTri.length; i++){
            while(n >= giaTri[i]){
                ans += kyTu[i];
                n -= giaTri[i];
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService sv = (CharacterService)rg.lookup("RMICharacterService");
        String ma = "B23DCCN628";
        String qCode = "t2uTXuGO";
        String s = sv.requestCharacter(ma, qCode);
        String ans = LaMa(Integer.parseInt(s));
        sv.submitCharacter(ma, qCode, ans);
    }
}
