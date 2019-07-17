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
                test.test1(true);
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
                run.set(test, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("end set");
        });
        thread.start();
        thread1.start();

//        unsafe.staticFieldOffset(run);

    }

    public static class Test {
        private static boolean run = true;


//        public Test() throws InterruptedException {
//            test1(run);
//            System.out.println("end1");
//        }
//
//        static {
//            try {
//                test0(run);
//                System.out.println("end1");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        public static void test0(boolean flag) throws InterruptedException {
            while (flag) {
                System.out.println("running");
                Thread.sleep(1000);
                test0(flag);
            }
        }

        public void test1(boolean flag) throws InterruptedException {
            while (flag) {
                System.out.println("running");
                Thread.sleep(1000);
                test1(flag);
            }
        }
    }
}
