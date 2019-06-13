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

    public Iterator travLevel() throws InterruptedException {
        LinkedBlockingQueue<BinNode> queue = new LinkedBlockingQueue<>();
        List<T> list = new LinkedList<>();
        queue.put(root);
        while (!queue.isEmpty()) {
            BinNode poll = queue.poll();
            list.add((T) poll.data);
            queue.put(poll.lChild);
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
                result.add(pop.data);
                travPre0(stack, pop);
            }
        }
        return result.iterator();
    }

    public void travPre0(Stack<BinNode> stack, BinNode node) {
        if (node != null) {
            // 当问node.data
            if (node.rChild != null) stack.add(node.rChild);
            if (node.lChild != null) stack.add(node.lChild);
        }
    }


}
