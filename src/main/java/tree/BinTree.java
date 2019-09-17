package tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 6/13/2019
 */
public class BinTree<T extends Comparable<? super T>> {
    public BinNode<T> root;
    public Integer size;

    public BinTree() {
    }

    //todo
    public void insert(T data) {
        if (root == null) {
            root = new BinNode<>(data);
        } else {
            BinNode<T> hot = new BinNode<>();
            root.insert(root, data, hot);
        }
    }

    // todo
    public BinNode<T> search(T data) {
        BinNode<T> hot = new BinNode();
        return root.search(root, data, hot);
    }

    // todo
    public void delete(T data) {
        BinNode<T> hot = new BinNode();
        root.delete(root, data, hot);
    }

    // --------------------1, recursion----------------------------
    public Iterator<T> traversePre() {
        List<T> result = new ArrayList<>();
        return traversePre(result, root);
    }

    public Iterator<T> traversePre(List<T> result, BinNode<T> node) {
        if (node != null) {
            result.add(node.data);
            traversePre(result, node.lChild);
            traversePre(result, node.rChild);
        }
        return result.iterator();
    }

    public Iterator<T> traverseIn() {
        List<T> result = new ArrayList<>();
        return traverseIn(result, root);
    }

    public Iterator<T> traverseIn(List<T> result, BinNode<T> node) {
        if (node != null) {
            traverseIn(result, node.lChild);
            result.add(node.data);
            traverseIn(result, node.rChild);
        }
        return result.iterator();
    }

    public Iterator<T> traversePost() {
        List<T> result = new ArrayList<>();
        return traversePost(result, root);
    }

    public Iterator<T> traversePost(List<T> result, BinNode<T> node) {
        if (node != null) {
            traversePost(result, node.lChild);
            traversePost(result, node.rChild);
            result.add(node.data);
        }
        return result.iterator();
    }

    // -------------------2, iteration----------------------

    public Iterator<T> traverseLevel() throws InterruptedException {
        LinkedBlockingQueue<BinNode<T>> queue = new LinkedBlockingQueue<>();
        List<T> list = new LinkedList<>();
        queue.put(root);
        while (!queue.isEmpty()) {
            BinNode<T> poll = queue.poll();
            list.add(poll.data);
            if (poll.lChild != null) queue.put(poll.lChild);
            if (poll.rChild != null) queue.put(poll.rChild);
        }
        return list.iterator();
    }


    public Iterator<T> traversePre0() {
        List<T> result = new ArrayList<>();
        Stack<BinNode<T>> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            BinNode<T> pop = stack.pop();
            // 1, visit this node
            result.add(pop.data);
            // 2, push this node's right node and left node into stack in order
            traversePre0(stack, pop);
        }
        return result.iterator();
    }

    public void traversePre0(Stack<BinNode<T>> stack, BinNode<T> node) {
        // this is previous traverse order is: root, left, right. when push into stack order is right left, poll order is left right
        // this mode is just like merge from bottom to up
        if (node.rChild != null) stack.add(node.rChild);
        if (node.lChild != null) stack.add(node.lChild);
    }

    public Iterator traverseIn0() {
//        Stack<BinNode<T>> stack = new Stack<>();
//        List<Object> result = new ArrayList<>();
//        stack.add(root);
//        while (!stack.isEmpty()) {
//
//        }

        return null;
    }

    public Iterator travIn0(Stack<BinNode> stack, BinNode node) {
        return null;
    }

    public Iterator travPost0() {
//        Stack<BinNode> stack = new Stack<>();
//        List<Object> result = new ArrayList<>();
//        stack.add(root);

        return null;
    }

    public Iterator travPost0(Stack<BinNode> stack, BinNode node) {
        return null;
    }

    /**
     * find the leftest node and return this node
     *
     * @param node go along with this node
     * @return return the leftest nonnull node
     */
    public BinNode goAlongLeft(BinNode node) {
        if (node.lChild != null) {
            goAlongLeft(node.lChild);
        }
        return node;
    }

    public static void main(String[] args) throws InterruptedException {
        BinNode<Integer> node = new BinNode<>(10);
        node.insertAsLc(5);
        node.insertAsRc(15);
        node.lChild.insertAsLc(3);
        node.lChild.insertAsRc(6);
        node.rChild.insertAsLc(13);
        node.rChild.insertAsRc(16);

        BinTree<Integer> tree = new BinTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(6);
        tree.insert(13);
        tree.insert(16);

        tree.delete(3);
        tree.delete(5);
//        tree.delete(55);

        Iterator<Integer> i0 = tree.traversePre();
        System.out.println("trav_pre:");
        i0.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("iteration_pre:");
        Iterator<Integer> i1 = tree.traversePre0();
        i1.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("trav_in: ");
        Iterator<Integer> i2 = tree.traverseIn();
        i2.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("trav_post:");
        Iterator<Integer> i3 = tree.traversePost();
        i3.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("trav_level:");
        Iterator<Integer> i4 = tree.traverseLevel();
        i4.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println(Optional.ofNullable(tree.search(5)).orElse(new BinNode(-1)).data);
        System.out.println(Optional.ofNullable(tree.search(55)).orElse(new BinNode()).data);
    }

}
