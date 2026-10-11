/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WB;

import java.util.*;
import ptit.dblab.judge.network.server.soap.CharacterService;
import ptit.dblab.judge.network.server.soap.SoapCharacterService;

/**
 *
 * @author ADMIN
 */
public class XoayVongKyTu {
    public static void main(String[] args) throws Exception{
        CharacterService sv = new CharacterService();
        SoapCharacterService port = sv.getSoapCharacterServicePort();
        
        String ma = "B23DCCN628";
        String code = "JqAcg6ui";
        
        List <Integer> a = port.requestCharacter(ma, code);
        
        int n = a.size();
        int k = a.get(0);
        List<Integer> ans = new ArrayList<>(a);
        for(int i = 0; i < n; i++){
            ans.set((i+k) % n, a.get(i));
        }
        port.submitCharacterCharArray(ma, code, ans);
    }
    
}
