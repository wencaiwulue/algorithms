package multiple_thread;

import java.util.concurrent.Callable;

/**
 * @author fengcaiwen
 * @since 6/20/2019
 */
public class FutureTest {
    private Callable callable;
    private Object result;

    public FutureTest(Callable callable) {
        this.callable = callable;
    }

    public Callable getCallable() {
        return callable;
    }

    public void setCallable(Callable callable) {
        this.callable = callable;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object get() throws Exception {
        Thread.sleep(1000);
        if (result == null) {
            result = callable.call();
        }
        return (String) result + " " + Thread.currentThread().getId();
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolTest01.ThreadPool threadPool = new ThreadPoolTest01.ThreadPool(10);
        Callable callable = () -> {
            return "normal";
        };

        for (int i = 0; i < 20; i++) {
            FutureTest submit = threadPool.submit(callable);
//        Thread.sleep(5 * 1000);
            Object o = submit.get();
            System.out.println(submit.get());
        }
    }
}
