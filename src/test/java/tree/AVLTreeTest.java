package tree;

import org.junit.Test;


/**
 * @author fengcaiwen
 * @since 6/28/2019
 */
public class AVLTreeTest {

    @Test
    public void insert() {
        BinNode<Integer> node = new BinNode<>(2);
        AVLTree<Integer> tree = new AVLTree<>(node);
//        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);

        tree.insert(6);
        tree.insert(7);

        tree.insert(8);
//        tree.insert(18);
//        tree.insert(22);
//        tree.insert(20);
//        tree.insert(19);

        System.out.println(node);
        System.out.println(node.size());

        node.updateAndGetHeight();

        System.out.println(node.n);
    }

    @Test
    public void delete() {
    }
}