package Thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

/**
 * @author fengcaiwen
 * @since 4/18/2019
 */
public class ThreadErrorHandlerTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadFactory threadFactoryBuilder = new ThreadFactoryBuilder().setNameFormat("name").setPriority(7).setThreadFactory(new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler(new ThreadHandlerTest());
                return thread;
            }
        })/*.setUncaughtExceptionHandler(new ThreadHandlerTest())*/.build();
        ExecutorService service = Executors.newFixedThreadPool(5, threadFactoryBuilder);
        Future<Object> create_a_error = service.submit(() -> {
            System.out.println("create a error");
            throw new RuntimeException();
        });
        for (;;){
            if (create_a_error.isDone()){
                create_a_error.get();
                break;
            }
        }
        service.shutdown();
    }
}
