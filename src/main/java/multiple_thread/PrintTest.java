package multiple_thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class PrintTest {
    public static void main(String[] args) {
        new Thread(new Print(new Object[]{1,2,3,4,5}, "lock")).start();
        new Thread(new Print(new Object[]{'a','b','c','d','e'}, "lock")).start();


//        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

//        new Thread(() -> {
//            int[] a = new int[]{1, 2, 3, 4, 5};
//            for (int i = 0; i < a.length; i++) {
//                while (true) {
//                    boolean b = atomicBoolean.compareAndSet(true, false);
//                    if (b) {
//                        System.out.println(a[i]);
//                        break;
//                    }
//                }
//                atomicBoolean.notify();
//            }
//        }).start();

//        new Thread(() -> {
//            char[] a = new char[]{'a', 'b', 'c', 'd', 'e'};
//            for (int i = 0; i < a.length; i++) {
//                while (true) {
//                    boolean b = atomicBoolean.compareAndSet(false, true);
//                    if (b) {
//                        System.out.println(a[i]);
//                        break;
//                    }
//                }
//            }
//        }).start();

    }

    public static class Print implements Runnable {
        public Object[] a;
        public String lock;

        public Print(Object[] a, String lock) {
            this.a = a;
            this.lock = lock;
        }

        @Override
        public void run() {
            for (Object o : a) {

                synchronized (lock) {
                    System.out.println(o);
                }
            }

        }
    }
}
