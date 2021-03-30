package multiplethread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier barrier = new CyclicBarrier(5);//创建CyclicBarrier对象并设置3个公共屏障点
        for (int i = 0; i < 5; i++) {
            Runnable runnable = () -> {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将到达集合地点1，当前已有" + barrier.getNumberWaiting() + "个已经到达，正在等候" + barrier.getParties());
                    barrier.await();//到此如果没有达到公共屏障点，则该线程处于等待状态，如果达到公共屏障点则所有处于等待的线程都继续往下运行

                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将到达集合地点2，当前已有" + barrier.getNumberWaiting() + "个已经到达，正在等候" + barrier.getParties());
                    barrier.await();
                    barrier.reset();
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将到达集合地点3，当前已有" + barrier.getNumberWaiting() + "个已经到达，正在等候" + barrier.getParties());
                    barrier.await();
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将到达集合地点4，当前已有" + barrier.getNumberWaiting() + "个已经到达，正在等候" + barrier.getParties());
                    barrier.await();
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将到达集合地点5，当前已有" + barrier.getNumberWaiting() + "个已经到达，正在等候" + barrier.getParties());
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}