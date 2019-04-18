package Thread;

/**
 * @author fengcaiwen
 * @since 4/18/2019
 */
public class ThreadHandlerTest implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught one error");
        e.printStackTrace();
    }
}
