package util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author fengcaiwen
 * @since 7/17/2019
 */
public class UnsafeThreadTest {
    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("old");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        thread.start();
        thread.start();

        Thread thread2 = new Thread(() -> {
            thread.run();
        });

        Thread thread1 = new Thread(() -> {
            Field field = null;
            try {
                field = Thread.class.getDeclaredField("target");
                field.setAccessible(true);
                long offset = unsafe.objectFieldOffset(field);
                Runnable runnable = () -> {
                    while (true)
                        try {
                            System.out.println("new");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                };
                unsafe.getAndSetObject(thread, offset, runnable);
                thread.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread2.start();
        Thread.sleep(3000);
        thread1.start();


    }


}
