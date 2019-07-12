package collections;

/**
 * @author fengcaiwen
 * @since 4/24/2019
 */
@SuppressWarnings("all")
public class BitSetTest {

    public static void main(String[] args) {
        setRankReverse(123123L, 1);
    }

    /**
     * reverse the rank of bit binary a
     */
    private static long setRankReverse(long a, int rank) {
        System.out.println(Long.toBinaryString(~a));
        System.out.println(Long.toBinaryString((1L << rank)));
        System.out.printf("%s\n", Long.toBinaryString(a));
        System.out.printf("%s\n", Long.toBinaryString(~(~a ^ (1L << rank))));
        return ~((~a) ^ (1L << rank));
    }


}
