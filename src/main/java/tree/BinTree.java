package tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Bin tree, full name is binary tree, base on BinNode
 *
 * @author fengcaiwen
 * @since 6/13/2019
 */
public class BinTree<T extends Comparable<? super T>> {
    public BinNode<T> root;
    public Integer size;

    public BinTree() {
    }

    public void insert(T data) {
        if (root == null) {
            root = new BinNode<>(data);
        } else {
            BinNode<T> hot = new BinNode<>();
            root.insert(root, data, hot);
        }
    }

    public BinNode<T> search(T data) {
        BinNode<T> hot = new BinNode<>();
        return root.search(root, data, hot);
    }

    public void delete(T data) {
        BinNode<T> hot = new BinNode<>();
        root.delete(root, data, hot);
    }

    // recursion
    public Iterator<T> traversePreorder() {
        List<T> result = new ArrayList<>();
        return traversePreorder(result, root);
    }

    public Iterator<T> traversePreorder(List<T> result, BinNode<T> node) {
        if (node != null) {
            result.add(node.data);
            traversePreorder(result, node.lChild);
            traversePreorder(result, node.rChild);
        }
        return result.iterator();
    }

    public Iterator<T> traverseInorder() {
        List<T> result = new ArrayList<>();
        return traverseInorder(result, root);
    }

    public Iterator<T> traverseInorder(List<T> result, BinNode<T> node) {
        if (node != null) {
            traverseInorder(result, node.lChild);
            result.add(node.data);
            traverseInorder(result, node.rChild);
        }
        return result.iterator();
    }

    public Iterator<T> traversePostorder() {
        List<T> result = new ArrayList<>();
        return traversePostorder(result, root);
    }

    public Iterator<T> traversePostorder(List<T> result, BinNode<T> node) {
        if (node != null) {
            traversePostorder(result, node.lChild);
            traversePostorder(result, node.rChild);
            result.add(node.data);
        }
        return result.iterator();
    }

    // iteration
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

    public Iterator<T> traversePreorderIterator() {
        List<T> result = new ArrayList<>();
        Stack<BinNode<T>> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            BinNode<T> pop = stack.pop();
            // 1, visit this node
            result.add(pop.data);
            // 2, push this node's right node and left node into stack in order
            traversePreorderIterator(stack, pop);
        }
        return result.iterator();
    }

    public void traversePreorderIterator(Stack<BinNode<T>> stack, BinNode<T> node) {
        // this is previous traverse order is: root, left, right. when push into stack order is right left, poll order is left right
        // this mode is just like merge from bottom to up
        if (node.rChild != null) stack.add(node.rChild);
        if (node.lChild != null) stack.add(node.lChild);
    }

    /**
     * find the leftest node and return this node
     *
     * @param node go along with this node
     * @return return the leftest nonnull node
     */
    public BinNode<T> goAlongLeft(BinNode<T> node) {
        if (node.lChild != null) {
            goAlongLeft(node.lChild);
        }
        return node;
    }

    public static void main(String[] args) throws InterruptedException {
        BinTree<Integer> tree = new BinTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(6);
        tree.insert(13);
        tree.insert(16);

        tree.delete(3);
//        tree.delete(5);
//        tree.delete(55);

        System.out.print("traverse_preorder:\t");
        tree.traversePreorder().forEachRemaining(e -> System.out.print(e + "\t"));
        System.out.println();

        System.out.print("iteration_preorder:\t");
        tree.traversePreorderIterator().forEachRemaining(e -> System.out.print(e + "\t"));
        System.out.println();

        System.out.print("traverse_inorder:\t");
        tree.traverseInorder().forEachRemaining(e -> System.out.print(e + "\t"));
        System.out.println();

        System.out.print("traverse_postorder:\t");
        tree.traversePostorder().forEachRemaining(e -> System.out.print(e + "\t"));
        System.out.println();

        System.out.print("traverse_level:\t\t");
        tree.traverseLevel().forEachRemaining(e -> System.out.print(e + "\t"));
        System.out.println();

        Optional.ofNullable(tree.search(5)).ifPresentOrElse(e -> System.out.println("exist"), () -> System.out.println("not exist"));
        Optional.ofNullable(tree.search(55)).ifPresentOrElse(e -> System.out.println("exist"), () -> System.out.println("not exist"));
    }

}
