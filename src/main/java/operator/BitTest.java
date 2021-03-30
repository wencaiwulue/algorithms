package operator;

import java.util.BitSet;

/**
 * @author fengcaiwen
 * @since 7/12/2019
 */
public class BitTest {
    public static void main(String[] args) {
        System.out.println((byte) 2581);
        System.out.println(2581 % 256);
        System.out.println(2581 & (255));
        BitSet bitSet = new BitSet(Integer.MAX_VALUE);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            bitSet.set(i, true);
        }
        System.out.println(bitSet.size());
//        System.out.println(Byte.MAX_VALUE);
    }
}
