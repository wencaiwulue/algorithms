package newfeature;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author fengcaiwen
 * @since 4/15/2019
 */
@Fork(1)
@Threads(1)
//@Warmup(iterations = 1, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(value = Mode.Throughput)
@OutputTimeUnit(value = TimeUnit.NANOSECONDS)
public class BenchmarkTest {
    @Benchmark
    public void testA() {
        for (int i = 0; i < 100000; i++) {
        }
    }

    @Benchmark
    public void testB() {

    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(BenchmarkTest.class.getName()).shouldDoGC(true).build();
        new Runner(options).run();
    }
}
