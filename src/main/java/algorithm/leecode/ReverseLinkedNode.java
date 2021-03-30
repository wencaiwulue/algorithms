package algorithm.leecode;

import org.junit.Test;

import java.util.Stack;

public class ReverseLinkedNode {
    Node node = new Node(null, 1);
    Node root = node;

    {
        node.next = new Node(null, 2);
        node = node.next;
        node.next = new Node(null, 3);
        node = node.next;
        node.next = new Node(null, 4);
    }

    @Test
    public void solution1() {
        Node result = reverse(root);
        while (result != null) {
            System.out.println(result.value);
            result = result.next;
        }
    }

    @Test
    public void solution2UsingStack() {
        Node result = reverseUsingStack(root);
        while (result != null) {
            System.out.println(result.value);
            result = result.next;
        }
    }

    public Node reverse(Node head) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node n = curr.next;
            curr.next = prev;
            prev = curr;
            curr = n;
        }
        return prev;
    }

    public Node reverseUsingStack(Node root) {
        Stack<Node> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.next;
        }
        Node r = stack.pop();
        Node b = r;
        while (!stack.isEmpty()) {
            r.next = stack.pop();
            r = r.next;
        }
        r.next = null;
        return b;
    }

    static class Node {
        private Node next;
        private final int value;

        public Node(Node node, int value) {
            this.next = node;
            this.value = value;
        }
    }
}
