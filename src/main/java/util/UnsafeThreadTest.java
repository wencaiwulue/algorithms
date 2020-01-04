package util;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author fengcaiwen
 * @since 7/17/2019
 */
public class UnsafeThreadTest {

    /**
     * 线程一旦提交到线程池，就无法更改了。但是开始修改任务的状态，不可替换任务
     */
    @Test
    public void changeRunnable(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("old");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Runnable printNew = () -> {
            while (true)
                try {
                    System.out.println("new");
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        };
        Thread thread1 = new Thread(() -> {
            try {
                Field field = Thread.class.getDeclaredField("target");
                field.setAccessible(true);
                long offset = unsafe.objectFieldOffset(field);
                unsafe.getAndSetObject(thread, offset, printNew);
                thread.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        Thread.sleep(3000);
    }
}
