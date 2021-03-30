package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 7/9/2019
 */
public class BTreeTest {

    @Test
    public void insert() {
        BTree<Integer> tree = new BTree<>(new BTree.BTNode<>(431, null, null), 4);
        tree.insert(830);
        tree.insert(160);
        tree.insert(330);
        tree.insert(575);
        tree.insert(693);
        tree.insert(911);
        tree.remove(911);
        System.out.println(1);
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
