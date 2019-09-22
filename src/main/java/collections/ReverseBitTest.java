package collections;

/**
 * @author fengcaiwen
 * @since 4/24/2019
 */
public class ReverseBitTest {

    public static void main(String[] args) {
        long a = 123123L;
        int rank = 1;
        System.out.println(setRankReverse(a, rank));
        System.out.println(isHighOrLow(a, rank));
    }

    /**
     * reverse the rank of bit of binary a
     */
    public static long setRankReverse(long a, int rank) {
//        System.out.println(Long.toBinaryString(~a));
//        System.out.println(Long.toBinaryString((1L << rank)));
//        System.out.printf("%s\n", Long.toBinaryString(a));
//        System.out.printf("%s\n", Long.toBinaryString(~((~a) ^ (1L << rank))));
        return ~((~a) ^ (1L << rank));
    }

    /**
     * judge the rank of bit of binary a is high or low
     */
    public static int isHighOrLow(long a, int rank) {
        return (int) (1L & (a >> rank));
    }


}
