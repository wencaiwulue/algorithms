package algorithm;

import com.google.common.io.BaseEncoding;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Queue;

/**
 * @author fengcaiwen
 * @since 5/29/2019
 * <p>
 * 电梯运行算法，
 * 电梯调度算法
 */
public class ElavatorTest {

    public static void main(String[] args) {

    }

    public int byElevator(List<Elevator> elevatorList, int target) {
        return -1;
    }

    public int getNextTarget(int current, Queue<Integer>[] targets) {
        return -1;
    }

    public int getNextTarget(int current, Queue<Integer> target) {
        return -1;
    }



    public static class Elevator {
        private String name;
        private volatile int current;
        private int target;
        private Queue<Integer>[] targets;
    }
}
