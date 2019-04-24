import java.util.concurrent.TimeUnit;


public class MathTest {
    @SuppressWarnings("all")
    public static void test() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            Math.sin(i);
            Math.cos(i);
            Math.log(i);
        }
        long endTime = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + "ms");

    }

    public static void main(String[] args) {
        test();
    }
}
