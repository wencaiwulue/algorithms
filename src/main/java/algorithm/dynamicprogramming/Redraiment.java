//package algorithm.dynamicprogramming;
//
//import java.util.List;
//
///**
// * @author naison
// * @since 5/2/2020 20:11
// */
//public class Redraiment {
//   static int GetResult(int num, int[] pinput, List pResult) {
//        int[][] temp = new int[num + 1][num + 1];
//        for (int i = 1; i < num + 1; i++) {
//            temp[i][i] = pinput[i - 1];
//        }
//        for (int i = 1; i < num + 1; i++) {
//            temp[i][1] = 0
//        }
//
//        for (int i = 1; i < num + 1; i++) {
//            for (int j = 2; j < num + 1; j++) {
//                if (temp[i][j] > temp[i][j - 1]) {
//                    temp[i][j] = pinput[j - 1];
//                } else {
//                    temp[i][j] = temp[i][j - 1];
//                }
//            }
//        }
//        int max = 0;
//
//        for (int i = 2; i < num + 1; i++) {
//            if (temp[i] > temp[i - 1]) {
//                max++;
//            }
//
//        }
//
//    }
//}
