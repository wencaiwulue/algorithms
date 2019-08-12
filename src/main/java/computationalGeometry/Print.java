package computationalGeometry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fengcaiwen
 * @since 8/9/2019
 */
public class Print {
    public static void main(String[] args) {
        printHeart();
        System.out.println("-----");
        printHeart3(10);
    }

    public static void print(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = n - i; j > 0; j--) {
                System.out.print("  ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print(" *  ");
            }
            System.out.println();
        }
    }

    // 2: B(t) = (1-t)Pm(t) + tPn(t) = (1-t)^2 P0 + 2(1-t)tP1+ t^2P2
    // 3: B(t) = P0*(1-t)^3 + 3*P1*t(1-t)^2 + 3 * P2 * t^2(1-t) + P3 * t^3
    public static void printHeart() {
        int p0 = 2, p1 = 4, p2 = 2;
        for (int i = 0; i < 10; i++) {
            double t = i * 0.1;
            double y = Math.pow(1 - t, 2) * p0 + 2 * (1 - t) * t * p1 + Math.pow(t, 2) * p2;
            System.out.println(y);
        }
    }

    public static void printHeart3(double n) {
        Map<Integer, Integer> map = new HashMap<>();
        int p0 = 0, p1 = 4, p2 = 2, p3 = 0;
        for (int i = 0; i < n; i++) {
            double t = i * (1 / n);
            double y = Math.pow(1 - t, 3) * p0 + 3 * Math.pow(1 - t, 2) * t * p1 + 3 * p2 * Math.pow(t, 2) * (1 - t) + Math.pow(t, 3) * p3;
            map.put(i, BigDecimal.valueOf(y).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP).intValue());
        }
    }


}
