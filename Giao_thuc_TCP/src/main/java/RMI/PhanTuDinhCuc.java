package RMI;

import java.rmi.registry.*;
import java.util.*;

public class PhanTuDinhCuc {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String ma = "B23DCCN628";
        String qCode = "GRfAuV6m";

        int[] a = (int[]) sv.requestData(ma, qCode);   // chỉ cần ép kiểu

        List<Integer> ans = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            boolean trai = (i == 0) || a[i] > a[i - 1];
            boolean phai = (i == n - 1) || a[i] > a[i + 1];
            if (trai && phai) {
                ans.add(i + 1);
            }
        }

        sv.submitData(ma, qCode, ans);
    }
}