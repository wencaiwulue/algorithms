package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 9/17/2019
 */
public class Tools {
    /**
     * evelate this two node is same or not
     *
     * @param node1 one
     * @param node2 other
     */
    private static <T extends Comparable<? super T>> boolean judgeIfTheSameByTravelLevel(BinNode<T> node1, BinNode<T> node2) throws InterruptedException {
        LinkedBlockingQueue<BinNode<T>> q1 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<BinNode<T>> q2 = new LinkedBlockingQueue<>();
        q1.put(node1);
        q2.put(node2);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            BinNode<T> p1 = q1.poll();
            BinNode<T> p2 = q2.poll();
            assert p1 != null;
            T data = p1.data;
            assert p2 != null;
            T data1 = p2.data;
            if (data.compareTo(data1) != 0) {
                return false;
            }
            if (p1.lChild != null) q1.put(p1.lChild);
            if (p1.rChild != null) q1.put(p1.rChild);

            if (p2.lChild != null) q2.put(p2.lChild);
            if (p2.rChild != null) q2.put(p2.rChild);
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        BinNode<Integer> tree1 = new BinNode<>(10);
        tree1.insertAsLc(5);
        tree1.insertAsRc(15);
        tree1.lChild.insertAsLc(3);
        tree1.lChild.insertAsRc(6);
        tree1.rChild.insertAsLc(13);
        tree1.rChild.insertAsRc(16);

        BinTree<Integer> tree2 = new BinTree<>();
        tree2.insert(10);
        tree2.insert(5);
        tree2.insert(15);
        tree2.insert(3);
        tree2.insert(6);
        tree2.insert(13);
//        tree2.insert(16);

//        tree.delete(3);

        System.out.println("two trees are the same?: " + judgeIfTheSameByTravelLevel(tree2.root, tree1));
    }
}
