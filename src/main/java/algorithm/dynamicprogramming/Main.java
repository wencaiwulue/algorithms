package algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>(10);
        while (sc.hasNextInt()) {
            list.add(sc.nextInt());
        }

        List<Integer> collect = list.stream().sorted().collect(Collectors.toList());

        int z = collect.get(0);
        int min = Integer.MAX_VALUE;
        for (int x : collect) {
            for (int y : collect) {
                min = Math.min(min, test(x, y, z));
            }
        }
        System.out.println(min);
    }

    // 输入为十个整数，整数范围为[-512, 512]，从中随机挑选三个，求下面公式的最小值。
    public static int test(int x, int y, int z) {
        return x * x + x * y - y * y + z;
    }
}