import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TaskPortion implements Runnable {
    private Random random = new Random(47);
    private CountDownLatch countDownLatch;

    TaskPortion(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            countDownLatch.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitingTask implements Runnable {

    private CountDownLatch countDownLatch;

    WaitingTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CountDownLatchTest {
    private static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new WaitingTask(countDownLatch));
        }
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortion(countDownLatch));
        }
        System.out.println("start all tasks");
        executorService.shutdown();
    }
}
