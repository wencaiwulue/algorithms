package cooperateprint;

public class PrintTest {
    public static void main(String[] args) {
        String str = "";
        new Thread(new Print(new Object[]{'a', 'b', 'c', 'd', 'e'}, str)).start();
        new Thread(new Print(new Object[]{1, 2, 3, 4, 5}, str)).start();
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
                        System.out.print(o);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.exit(0);
        }
    }
}
