package operator;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
@SuppressWarnings("all")
public class XorTest {
    public static void main(String[] args) {
        System.out.println(7 ^ 7 ^ 7 ^ 5 ^ 5 ^ 3 ^ 7);
        System.out.println(7 | 5);
        System.out.println(7 & 5);
        System.out.println(~-9);
        System.out.println(7 >> 4);
        System.out.println(7 << 2);
        System.out.println(7 >>> 4);
    }
}
