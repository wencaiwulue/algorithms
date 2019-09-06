package tree;

import org.junit.Assert;

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
            BinNode<T> temp = hot;
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

    public void rotate(BinNode<T> node) {
        BinNode<T> a, b, c;
        BinNode<T> t1, t2, t3, t4;

        BinNode<T> node1 = node;
        BinNode<T> node2 = node.parent;
        BinNode<T> node3 = node.parent.parent;
        /*
         *           |        |          |         |
         *           3        3          3         3
         *          / \      / \        / \       / \
         *         2        2              2         2
         *        / \      / \            / \       / \
         *       1            1          1             1
         *      / \          / \        / \           / \
         *
         *       (1)         (2)         (3)         (4)
         *
         *  中序遍历顺序就是从小到大的顺序
         */
        if (node3.lChild == node2) {
            if (node2.lChild == node1) { // circumstance 1
                a = node1;
                b = node2;
                c = node3;
                t1 = node1.lChild;
                t2 = node1.rChild;
                t3 = node2.rChild;
                t4 = node3.rChild;
            } else { // circumstance 2
                a = node2;
                b = node1;
                c = node3;
                t1 = node2.lChild;
                t2 = node1.lChild;
                t3 = node1.rChild;
                t4 = node3.rChild;
            }
        } else {
            if (node2.lChild == node1) { // circumstance 3
                a = node3;
                b = node1;
                c = node2;
                t1 = node3.lChild;
                t2 = node1.lChild;
                t3 = node1.rChild;
                t4 = node2.rChild;

            } else { // circumstance 4
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

        if (node3.parent == null) root = b;
        else if (node3.parent.lChild == node3) node3.parent.lChild = b;
        else node3.parent.rChild = b;

        updateAndGetHeight(node1);
        updateAndGetHeight(node2);
        updateAndGetHeight(node3);

    }

    /*
     *        b
     *      /   \
     *     a     c
     *   /  \   /  \
     *  t1  t2 t3  t4
     */
    public void connect34(BinNode a, BinNode b, BinNode c, BinNode t1, BinNode t2, BinNode t3, BinNode t4) {
        b.lChild = a;
        b.rChild = c;
        a.lChild = t1;
        a.rChild = t2;
        c.lChild = t3;
        c.rChild = t4;

        a.parent = b;
        c.parent = b;
        if (t1 != null) t1.parent = a;
        if (t2 != null) t2.parent = a;
        if (t3 != null) t3.parent = c;
        if (t4 != null) t4.parent = c;

    }

    /*
     *     |                  |
     *     a                  b
     *    / \       ->       / \
     *   t1  b              a   t3
     *      / \            / \
     *     t2  t3         t1  t2
     */
    public void leftRotate(BinNode a) {
        Assert.assertNotNull(a.rChild);
        BinNode b = a.rChild;
        BinNode t1 = a.lChild;
        BinNode t2 = b.lChild;
        BinNode t3 = b.rChild;

        a.lChild = t1;
        a.rChild = t2;
        b.lChild = a;
        b.rChild = t3;

        if (a.parent == null) b = root;
        else if (a.parent.lChild == a) a.parent.lChild = b;
        else a.parent.rChild = b;
        b.parent = a.parent;
        a.parent = b;

        if (t1 != null) t1.parent = a;
        if (t2 != null) t2.parent = a;
        if (t3 != null) t3.parent = b;

    }

    /*
     *       |              |
     *       a              b
     *      / \     ->     / \
     *     b   t3         t1  a
     *    / \                / \
     *   t1 t2              t2  t3
     */
    public void rightRotate(BinNode a) {
        Assert.assertNotNull(a.lChild);
        BinNode b = a.lChild;
        BinNode t1 = b.lChild;
        BinNode t2 = b.rChild;
        BinNode t3 = a.rChild;

        b.lChild = t1;
        b.rChild = a;
        a.lChild = t2;
        a.rChild = t3;

        if (a.parent == null) b = root;
        else if (a.parent.lChild == a) a.parent.lChild = b;
        else a.parent.rChild = b;
        b.parent = a.parent;
        a.parent = b;

        if (t1 != null) t1.parent = b;
        if (t2 != null) t2.parent = a;
        if (t3 != null) t3.parent = a;
    }

    public void connect() {
    }

    public BinNode<T> tallerChild(BinNode<T> node) {
        if (node.lChild == null) return node.rChild;
        if (node.rChild == null) return node.lChild;
        int i = updateAndGetHeight(node.lChild);
        int j = updateAndGetHeight(node.rChild);
        return i > j ? node.lChild : node.rChild;
    }

}
