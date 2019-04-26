package multiple_thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fengcaiwen
 * @since 4/25/2019
 */
public class CountDownLatchTest01 {
    public static void main(String[] args) {

    }
    public static void test(){
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(3);

    }
}
