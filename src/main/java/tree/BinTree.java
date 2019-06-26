package tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 6/13/2019
 */
@SuppressWarnings("all")
public class BinTree<T extends Comparable> {
    public BinNode<T> root;
    public Integer size;

    public BinTree() {
    }

    //todo
    public void insert(T data) {
        if (root == null)
            root = new BinNode<>(data);
        else
            root.insert(root, data);
    }

    // todo
    public BinNode search(T data) {
        return root.search(root, data);
    }

    // todo
    public void delete(T data) {
        root.delete(root, data);
    }

    // --------------------1, recursion----------------------------
    public Iterator travPre() {
        List<Object> result = new ArrayList<>();
        return travPre(result, root);
    }

    public Iterator travPre(List<Object> result, BinNode node) {
        if (node != null) {
            result.add(node.data);
            travPre(result, node.lChild);
            travPre(result, node.rChild);
        }
        return result.iterator();
    }

    public Iterator travIn() {
        List<Object> result = new ArrayList<>();
        return travIn(result, root);
    }

    public Iterator travIn(List<Object> result, BinNode node) {
        if (node != null) {
            travIn(result, node.lChild);
            result.add(node.data);
            travIn(result, node.rChild);
        }
        return result.iterator();
    }

    public Iterator travPost() {
        List<Object> result = new ArrayList<>();
        return travPost(result, root);
    }

    public Iterator travPost(List<Object> result, BinNode node) {
        if (node != null) {
            travPost(result, node.lChild);
            travPost(result, node.rChild);
            result.add(node.data);
        }
        return result.iterator();
    }

    // -------------------2, iteration----------------------

    public Iterator travLevel() throws InterruptedException {
        LinkedBlockingQueue<BinNode> queue = new LinkedBlockingQueue<>();
        List<Object> list = new LinkedList<>();
        queue.put(root);
        while (!queue.isEmpty()) {
            BinNode poll = queue.poll();
            list.add(poll.data);
            if (poll.lChild != null) queue.put(poll.lChild);
            if (poll.rChild != null) queue.put(poll.rChild);
        }
        return list.iterator();
    }

    /**
     * evelate this two node is same or not
     *
     * @param node1 one
     * @param node2 other
     */
    public Iterator judgeIfTheSameByTravelLevel(BinNode<T> node1, BinNode<T> node2) throws InterruptedException {
        LinkedBlockingQueue<BinNode<T>> q1 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<BinNode<T>> q2 = new LinkedBlockingQueue<>();
        List<T> list = new LinkedList<>();
        q1.put(node1);
        q2.put(node2);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            BinNode<T> p1 = q1.poll();
            BinNode<T> p2 = q2.poll();
            list.add(p1.data);
            list.add(p2.data);
            if (p1.data != p2.data) {
                System.out.println("not match");
                return null;
            }
            if (p1.lChild != null) q1.put(p1.lChild);
            if (p1.rChild != null) q1.put(p1.rChild);

            if (p2.lChild != null) q2.put(p2.lChild);
            if (p2.rChild != null) q2.put(p2.rChild);
        }
        System.out.println("match");
        return list.iterator();
    }

    public Iterator travPre0() {
        List<Object> result = new ArrayList<>();
        Stack<BinNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            BinNode pop = stack.pop();
            // 1, visit this node
            result.add(pop.data);
            // 2, push this node's right node and left node into stack in order
            travPre0(stack, pop);
        }
        return result.iterator();
    }

    public void travPre0(Stack<BinNode> stack, BinNode node) {
        // this is previous trave order is: root, left, right. when push into stack order is right left, poll order is left right
        // this modle is just like merge from buttom to up
        if (node.rChild != null) stack.add(node.rChild);
        if (node.lChild != null) stack.add(node.lChild);
    }

    public Iterator travIn0() {
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

        Iterator i0 = tree.travPre();
        System.out.println("trav_pre:");
        i0.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("iteration_pre:");
        Iterator i1 = tree.travPre0();
        i1.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("trav_in: ");
        Iterator i2 = tree.travIn();
        i2.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("trav_post:");
        Iterator i3 = tree.travPost();
        i3.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("trav_level:");
        Iterator i4 = tree.travLevel();
        i4.forEachRemaining(e -> System.out.print(e + " "));
        System.out.println();
        new BinTree<Integer>().judgeIfTheSameByTravelLevel(tree.root, node);
    }

}
