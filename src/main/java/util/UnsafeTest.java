package util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author fengcaiwen
 * @since 7/17/2019
 */
@SuppressWarnings("all")
public class UnsafeTest {
    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        Test test = new Test();
        Thread thread = new Thread(() -> {
            try {
                test.test1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("end0");
            }

        });
        Thread thread1 = new Thread(() -> {
            Field run = null;
            try {
                run = test.getClass().getDeclaredField("run");
                run.setAccessible(true);
                run.set(test, 0);
                long l = unsafe.objectFieldOffset(run);
                System.out.println(l);
                unsafe.getAndSetObject(test, l, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("end set");
        });
        thread.start();
        thread1.start();


    }

    public static class Test {
        private int run = 1;

        public void test1() throws InterruptedException {
            while (run == 1) {
                System.out.println("running");
                Thread.sleep(1000);
            }
        }
    }
}
