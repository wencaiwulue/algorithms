package tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 6/13/2019
 */
@SuppressWarnings("all")
public class BinTree<T> {
    public BinNode root;
    public Integer size;

    public BinTree(BinNode root) {
        this.root = root;
    }

    public Iterator travLevel() throws InterruptedException {
        LinkedBlockingQueue<BinNode> queue = new LinkedBlockingQueue<>();
        List<T> list = new LinkedList<>();
        queue.put(root);
        while (!queue.isEmpty()) {
            BinNode poll = queue.poll();
            list.add((T) poll.data);
            if (poll.lChild != null)
                queue.put(poll.lChild);
            if (poll.rChild != null)
                queue.put(poll.rChild);
        }
        return list.iterator();
    }

    // --------------------recursion----------------------------
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
            travPost(result, node.rChild);
            travPost(result, node.lChild);
            result.add(node.data);
        }
        return result.iterator();
    }

    // -------------------iteration----------------------
    public Iterator travPre0() {
        List<Object> result = new ArrayList<>();
        Stack<BinNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            BinNode pop = stack.pop();
            if (pop != null) {
                // 1, visit this node
                result.add(pop.data);
                // 2, push this node's right node and left node into stack in order
                travPre0(stack, pop);
            }
        }
        return result.iterator();
    }

    public void travPre0(Stack<BinNode> stack, BinNode node) {
        if (node != null) {
            // this is previous trave order is: root, left, right. when push into stack order is right left, poll order is left right
            // this modle is just like merge from button to up
            if (node.rChild != null) stack.add(node.rChild);
            if (node.lChild != null) stack.add(node.lChild);
        }
    }

    public Iterator travIn0() {
        Stack<BinNode> stack = new Stack<>();
        stack.add(root);

        return null;
    }

    public Iterator travIn0(Stack<BinNode> stack, BinNode node) {
        stack.add(root);

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

    }


}
