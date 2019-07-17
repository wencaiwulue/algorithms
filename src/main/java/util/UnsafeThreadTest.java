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
            System.out.println("run end");
        });
        thread.run();

        Field field = Thread.class.getDeclaredField("target");
        field.setAccessible(true);
        long offset = unsafe.objectFieldOffset(field);
        Runnable runnable = () -> {
            System.out.println("new end");
        };
        unsafe.getAndSetObject(thread, offset, runnable);
        thread.run();
    }


}
