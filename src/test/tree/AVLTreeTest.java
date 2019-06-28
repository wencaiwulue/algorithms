package tree;

import org.junit.Test;

import static org.junit.Assert.*;
import static tree.BinNode.updateAndGetHeight;

/**
 * @author fengcaiwen
 * @since 6/28/2019
 */
public class AVLTreeTest {

    @Test
    public void insert() {
        BinNode<Integer> node = new BinNode<>(10);
        AVLTree<Integer> tree = new AVLTree<>(node);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(6);

        tree.insert(13);
        tree.insert(16);

        tree.insert(17);
        tree.insert(18);
        tree.insert(22);
        tree.insert(20);
        tree.insert(19);

        System.out.println(node);
        System.out.println(node.size());

        updateAndGetHeight(node);

        System.out.println(node.n);
    }

    @Test
    public void delete() {
    }
}