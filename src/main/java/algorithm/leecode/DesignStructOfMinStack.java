package algorithm.leecode;

import org.junit.Test;

import java.util.Stack;

public class DesignStructOfMinStack {
    @Test
    public void testStack() {
        MinStack stack = new MinStack();
        stack.push(-3);
        stack.push(1);
        stack.push(-4);
        System.out.println(stack.min());
        System.out.println(stack.pop());
        System.out.println(stack.min());
    }

    static class MinStack {
        Stack<Entry> stack = new Stack<>();

        public void push(int value) {
            Entry entry;
            if (!stack.isEmpty()) {
                int min = stack.peek().currentMin;
                entry = new Entry(value, Math.min(min, value));
            } else {
                entry = new Entry(value, value);
            }
            this.stack.push(entry);
        }

        public int pop() {
            return stack.pop().value;
        }

        public int min() {
            return stack.peek().currentMin;
        }
    }

    static class Entry {
        int value;
        int currentMin;

        public Entry() {
        }

        public Entry(int value, int currentMin) {
            this.value = value;
            this.currentMin = currentMin;
        }
    }
}
