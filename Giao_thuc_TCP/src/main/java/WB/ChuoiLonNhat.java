package WB;

import ptit.dblab.judge.network.server.soap.*;
import java.util.*;

public class ChuoiLonNhat {
    public static void main(String[] args) throws Exception {
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();

        String ma = "B23DCCN628";
        String qCode = "xqz03F26";

        List<Integer> list = port.getData(ma, qCode);

        List<String> s = new ArrayList<>();
        for (int x : list) s.add(String.valueOf(x));

        for (int i = 0; i < s.size(); i++) {
            for (int j = i + 1; j < s.size(); j++) {
                String ab = s.get(i) + s.get(j);
                String ba = s.get(j) + s.get(i);
                if (ba.compareTo(ab) > 0) {
                    String tmp = s.get(i);
                    s.set(i, s.get(j));
                    s.set(j, tmp);
                }
            }
        }

        String kq = String.join("", s);
        if (kq.startsWith("0")) kq = "0";

        port.submitDataString(ma, qCode, kq);
    }
}