package collections;

import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 6/15/2019 16:03
 */
public class StackTest {
    public static void main(String[] args) {
        Queue<Integer> stack = new LinkedBlockingQueue<>();
        stack.add(0);
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        Iterator<Integer> iterator = stack.iterator();
        for (Integer integer : stack) {
            System.out.println(integer);
        }

//        while (!stack.isEmpty()){
//            System.out.println(stack.peek());
//        }

    }
}
