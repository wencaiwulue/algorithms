package algorithm.leecode;

/**
 * https://leetcode-cn.com/problems/transform-to-chessboard/submissions/
 *
 * @author fengcaiwen
 * @since 11/11/2019
 */
public class TransformToChessboard {

    public static void main(String[] args) {
//        int[][] ints = {{0, 1, 1, 0}, {0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}};
//        int[][] ints = {{0, 1}, {1, 0}};
        int[][] ints = {{0, 1}, {0, 1}};
//        System.out.println(test(ints));
        System.out.println(answer(ints));
    }

    private static int cal(int[] ints) {
        int length = ints.length;
        int oneNum = 0;
        for (int anInt : ints) if (anInt == 1) oneNum++;
        int zeroNum = length - oneNum;
        // if the difference of zero amount and one amount is bigger than 1, then can't transform
        if (Math.max(oneNum, zeroNum) - Math.min(oneNum, zeroNum) > 1) return -1;

        // equals
        int min = Integer.MAX_VALUE;
        if (oneNum == zeroNum) {
            for (int num = 0; num < 2; num++) {
                int c = 0;
                for (int i = 0; i < length; i++) {
                    int anInt = ints[i];
                    if (i % 2 == 0) {
                        if (anInt == num) c++;
                    } else {
                        if (anInt == 1 - num) c++;
                    }
                }
                min = Math.min(min, c);
            }
            // not equals
        } else {
            // the right answer first number should be zero or one
            int first = 0;
            if (oneNum > zeroNum) first = 1;

            int c = 0;
            // zero more or one more
            for (int i = 0; i < length; i++) {
                int anInt = ints[i];
                if (i % 2 == 0) {
                    if (anInt != first) {
                        c++;
                    }
                } else {
                    if (anInt != 1 - first) {
                        c++;
                    }
                }
            }
            min = Math.min(min, c);
        }
        return min / 2;
    }

    /*
     * array a and b is the same or every bit is reverse
     * */
    private static boolean can(int[] a, int[] b) {
        int d = 0;
        for (int i1 = 0, aLength = a.length; i1 < aLength; i1++) {
            int i = a[i1];
            int j = b[i1];
            if (i != j) d++;
        }
        return d == 0 || d == a.length;
    }

    private static int answer(int[][] board) {
        int[] last = board[0];
        for (int i = 1, intsLength = board.length; i < intsLength; i++) {
            int[] anInt = board[i];
            if (!can(last, anInt)) return -1;
            last = anInt;
        }

        int r;
        if ((r = cal(board[0])) < 0) return -1;
        int c;
        int[] temp = new int[board.length];
        for (int i = 0; i < board.length; i++) {
            int[] anInt = board[i];
            temp[i] = anInt[0];
        }
        if ((c = cal(temp)) < 0) return -1;

        return r + c;
    }
}
