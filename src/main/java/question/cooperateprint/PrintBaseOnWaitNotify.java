package question.cooperateprint;

public class PrintBaseOnWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        String str = "";
        new Thread(new Print(new Object[]{'a', 'b', 'c', 'd', 'e'}, str)).start();
        new Thread(new Print(new Object[]{1, 2, 3, 4, 5}, str)).start();
        Thread.sleep(2000);
    }

    static class Print implements Runnable {
        private final Object[] objects;
        private final String lock;

        Print(Object[] objects, String lock) {
            this.objects = objects;
            this.lock = lock;
        }

        @Override
        public void run() {
            for (Object o : objects) {
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
