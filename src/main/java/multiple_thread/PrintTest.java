package multiple_thread;

public class PrintTest {
    public static void main(String[] args) {
        String lock_str = "";
        new Thread(new Print(new Object[]{'a', 'b', 'c', 'd', 'e'}, lock_str)).start();
        new Thread(new Print(new Object[]{1, 2, 3, 4, 5}, lock_str)).start();
    }

    public static class Print implements Runnable {
        private Object[] a;
        private String lock;

        Print(Object[] a, String lock) {
            this.a = a;
            this.lock = lock;
        }

        @Override
        public void run() {
            for (Object o : a) {
                synchronized (lock.intern()) {
                    try {
                        lock.notify();
                        System.out.println(o);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
