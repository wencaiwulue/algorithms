package util;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author fengcaiwen
 * @since 7/17/2019
 */
public class UnsafeTest {

    private static Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeStaticField() throws NoSuchFieldException, IllegalAccessException {
        TestRun testRun = new TestRun();
        Field number = testRun.getClass().getDeclaredField("number");
        number.setAccessible(true);
        number.set(testRun, 12);
        System.out.println(number.get(testRun));//success
    }

    @Test
    public void changeStaticFinalField() throws NoSuchFieldException, IllegalAccessException {
        TestRun testRun = new TestRun();
        Field str = testRun.getClass().getDeclaredField("str");
        str.setAccessible(true);
//        str.set(testRun, "modified");

        long offset = unsafe.staticFieldOffset(str);
        Object modified = unsafe.getAndSetObject(testRun, offset, "modified");

        System.out.println(str.get(testRun));//fail
    }

    @Test
    public void changeFinalField() throws NoSuchFieldException, IllegalAccessException {
        TestRun testRun = new TestRun();
        Field string = testRun.getClass().getDeclaredField("string");
        string.setAccessible(true);
        string.set(testRun, "modified");
        System.out.println(string.get(testRun));//success
    }


    @Test
    public void changeThreadTaskStatus() throws Exception {
        TestRun testRun = new TestRun();

        Thread eventThread = new Thread(() -> {
            testRun.run();
            System.out.println("end0");
        });

        Thread changeThread = new Thread(() -> {
            try {
                Field run = testRun.getClass().getDeclaredField("sign");
//                run.setAccessible(true);
//                run.set(test, 0);
                long l = unsafe.objectFieldOffset(run);
                unsafe.getAndSetObject(testRun, l, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        eventThread.start();
        changeThread.start();
        Thread.sleep(5000);
    }


    public static class TestRun {
        private int sign = 1;

        private static int number = 0;

        private static String string = "string";

        private static final String str = "unmodifiable";

        public void run() {
            while (true) {
                if (sign == 1) {
                    System.out.println("old");
                } else {
                    System.out.println("new");
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
