package util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @author naison
 * @since 5/5/2020 17:28
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Supplier<Integer> supplier = () -> {
            int i = 0;
            while (i++ < Integer.MAX_VALUE) {
            }
            return 2;
        };
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(supplier);
        assert completableFuture.get() != null : "win";
        System.out.println(completableFuture.get());
    }
}
