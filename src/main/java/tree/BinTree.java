package tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 6/13/2019
 */
@SuppressWarnings("all")
public class BinTree {
    public BinNode root;
    public Integer size;

    public BinTree(BinNode root) {
        this.root = root;
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

    public Iterator travLevel_test(BinNode node, BinNode node0) throws InterruptedException {
        LinkedBlockingQueue<BinNode> queue = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<BinNode> queue0 = new LinkedBlockingQueue<>();
        List<Object> list = new LinkedList<>();
        queue.put(node);
        queue0.put(node0);
        while (!queue.isEmpty() && !queue0.isEmpty()) {
            BinNode poll = queue.poll();
            BinNode poll0 = queue0.poll();
//            list.add(poll.data);
//            list.add(poll0.data);
            if (poll.data != poll0.data) {
                System.out.println("not match");
                return null;
            }
            if (poll.lChild != null) queue.put(poll.lChild);
            if (poll.rChild != null) queue.put(poll.rChild);

            if (poll0.lChild != null) queue0.put(poll0.lChild);
            if (poll0.rChild != null) queue0.put(poll0.rChild);
        }
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
        Stack<BinNode> stack = new Stack<>();
        List<Object> result = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty()) {

        }

        return null;
    }

    public Iterator travIn0(Stack<BinNode> stack, BinNode node) {
//        while (node.lChild=null){}
        return null;
    }

    public Iterator travPost0() {
        Stack<BinNode> stack = new Stack<>();
        List<Object> result = new ArrayList<>();
        stack.add(root);

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
        BinNode node = new BinNode(10);
        node.insertAsLc(5);
        node.insertAsRc(15);
        node.lChild.insertAsLc(3);
        node.lChild.insertAsRc(6);

        node.rChild.insertAsLc(13);
        node.rChild.insertAsRc(16);

        BinNode node0 = new BinNode(10);
        node0.insertAsLc(5);
        node0.insertAsRc(15);
        node0.lChild.insertAsLc(3);
        node0.lChild.insertAsRc(6);
        node0.rChild.insertAsLc(13);
        node0.rChild.insertAsRc(16);

        BinTree tree = new BinTree(node);
        Iterator iterator = tree.travPre();
        System.out.println("this is pre: ");
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();


        System.out.println("this is iteration pre:");
        Iterator iterator3 = tree.travPre0();
        while (iterator3.hasNext())
            System.out.print(iterator3.next() + " ");
        System.out.println();

        System.out.println("this is in: ");
        Iterator iterator0 = tree.travIn();
        while (iterator0.hasNext())
            System.out.print(iterator0.next() + " ");
        System.out.println();

        System.out.println("this is post: ");
        Iterator iterator1 = tree.travPost();
        while (iterator1.hasNext())
            System.out.print(iterator1.next() + " ");
        System.out.println();

        System.out.println("this is level: ");
        Iterator iterator2 = tree.travLevel();
        while (iterator2.hasNext())
            System.out.print(iterator2.next() + " ");

        new BinTree(null).travLevel_test(node, node0);

    }


}
