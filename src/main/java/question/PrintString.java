package question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrintString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] ss = s.split(" ");
            List<String> result = new ArrayList<>();
            for (int j = 1; j < ss.length; j++) {
                String sss = ss[j];
                if (sss.length() % 8 != 0)
                    sss = sss + "00000000";

                while (sss.length() >= 8) {
                    result.add(sss.substring(0, 8));
                    sss = sss.substring(8);
                }
            }
            Collections.sort(result);
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if (i != result.size() - 1)
                    System.out.print(" ");
            }
        }


    }
}

