package question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrintString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] split = line.split(" ");
            List<String> result = new ArrayList<>();
            for (int j = 1; j < split.length; j++) {
                String str = split[j];
                if (str.length() % 8 != 0)
                    str = str + "00000000";

                while (str.length() >= 8) {
                    result.add(str.substring(0, 8));
                    str = str.substring(8);
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

