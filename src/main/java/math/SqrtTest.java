package math;

/**
 * @author fengcaiwen
 * @since 8/16/2019
 */
public class SqrtTest {
    public static void main(String[] args) {
        double a = 1.414d;
        System.out.println(Math.sqrt(2));
        System.out.println(2 - a * a);
        System.out.println(2 - 1.4142d * 1.4142d);
        System.out.println(1.4142d * 1.4142d);
        System.out.println(1.414d * 1.414d);
        System.out.println(1.414d * 1.414d - 1.4142d * 1.4142d);
        System.out.println(Math.sqrt(5.6 * Math.pow(10, -4)));
        double sqrt = Math.sqrt(2 * 1.414 * 1.414 - 4 * (1.414 * 1.414 - 0.5));
        double v = (-2 * 1.414 - sqrt) / 2d;
        System.out.println(v);
        System.out.println(Math.pow(0.0002135623730951, 2));
        System.out.println(1.414 + 2);
        System.out.println(1.414 - 2);
    }
}
