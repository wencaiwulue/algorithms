package tree;

import static tree.BinNode.updateAndGetHeight;

/**
 * @author fengcaiwen
 * @since 6/28/2019
 */
public class AVLTree<T extends Comparable> {
    private BinNode<T> root;

    public AVLTree(BinNode<T> root) {
        this.root = root;
    }

    public void insert(T data) {
        BinNode<Integer> fakeHot = new BinNode<>(-1);
        root.insert(root, data, fakeHot);
        BinNode hot = fakeHot.parent;
        if (hot == null) return;
        // rotate
        while (hot != null) {
            BinNode temp = hot;
            if (Math.abs(updateAndGetHeight(temp.lChild) - updateAndGetHeight(temp.rChild)) > 1) {
                rotate(tallerChild(tallerChild(temp)));
            }
            hot = hot.parent;
        }
    }

    public void delete(T data) {
        BinNode<Integer> fakeHot = new BinNode<>(-1);
        root.delete(root, data, fakeHot);
        BinNode hot = fakeHot.parent;
        if (hot == null) return;

        // rotate
        while (hot != null) {
            BinNode temp = hot.parent;
            if (Math.abs(updateAndGetHeight(temp.lChild) - updateAndGetHeight(temp.rChild)) > 1) {
                rotate(tallerChild(tallerChild(temp)));
            }
            hot = hot.parent;
        }

    }

    public void rotate(BinNode node) {
        BinNode a, b, c;
        BinNode t1, t2, t3, t4;

        BinNode node1 = node;
        BinNode node2 = node.parent;
        BinNode node3 = node.parent.parent;

        /*
         *      3        3          3         3
         *     /        /            \         \
         *    2        2              2         2
         *   /          \            /           \
         *  1            1          1             1
         *  (1)         (2)         (3)         (4)
         *
         *  中序遍历顺序就是从小到大的顺序
         */
        if (node3.lChild == node2) {
            if (node2.lChild == node1) {
                a = node1;
                b = node2;
                c = node3;
                t1 = node1.lChild;
                t2 = node1.rChild;
                t3 = node2.rChild;
                t4 = node3.rChild;
            } else {
                a = node2;
                b = node1;
                c = node3;
                t1 = node2.lChild;
                t2 = node1.lChild;
                t3 = node1.rChild;
                t4 = node3.rChild;
            }
        } else {
            if (node2.lChild == node1) {
                a = node2;
                b = node1;
                c = node3;
                t1 = node3.lChild;
                t2 = node1.lChild;
                t3 = node1.rChild;
                t4 = node2.rChild;

            } else {
                a = node3;
                b = node2;
                c = node1;
                t1 = node3.lChild;
                t2 = node2.lChild;
                t3 = node1.lChild;
                t4 = node1.rChild;
            }
        }
        connect34(a, b, c, t1, t2, t3, t4);

        updateAndGetHeight(node1);
        updateAndGetHeight(node2);
        updateAndGetHeight(node3);

    }

    /*
     *        b
     *      /  \
     *     a    c
     *   /  \  / \
     *  t1 t2 t3 t4
     */
    public void connect34(BinNode a, BinNode b, BinNode c, BinNode t1, BinNode t2, BinNode t3, BinNode t4) {
        b.lChild = a;
        b.rChild = c;
        a.lChild = t1;
        a.rChild = t2;
        c.lChild = t3;
        c.rChild = t4;
    }

    public BinNode tallerChild(BinNode node) {
        if (node.lChild == null) return node.rChild;
        if (node.rChild == null) return node.lChild;
        int i = updateAndGetHeight(node.lChild);
        int j = updateAndGetHeight(node.rChild);
        return i > j ? node.lChild : node.rChild;
    }

}
