package tree;

import org.junit.Assert;

/**
 * red-block tree
 *
 * @author fengcaiwen
 * @since 9/6/2019
 */
public class RbTree<K, V> {
    private RbNode<K, V> root;
//    private static RbNode nil;

    /*
     *       |                       |
     *       x                       y
     *      / \          ->         / \
     *     a   y                   x   c
     *        / \                 / \
     *       b   c               a   b
     *
     * */
    public void leftRotate(RbTree t, RbNode x) {
        Assert.assertNotNull(x.r);
        RbNode y = x.r;
        RbNode a = x.l;
        RbNode b = y.l;
        RbNode c = y.r;

        x.l = a;
        x.r = b;
        y.l = x;
        y.r = c;

        if (x.p == null) y = t.root;
        else if (x.p.l == x) x.p.l = y;
        else x.p.r = y;
        y.p = x.p;
        x.p = y;

        if (a != null) a.p = x;
        if (b != null) b.p = x;
        if (c != null) c.p = y;
    }

    /*
     *        |                    |
     *        x                    y
     *       / \       ->         / \
     *      y   c                a   x
     *     / \                      / \
     *    a   b                    b   c
     *
     * */
    public void rightRotate(RbTree t, RbNode x) {
        Assert.assertNotNull(x.l);
        RbNode y = x.l;
        RbNode a = y.l;
        RbNode b = y.r;
        RbNode c = x.r;

        y.l = a;
        y.r = x;
        x.l = b;
        x.r = c;

        if (x.p == null) y = t.root;
        else if (x.p.l == x) x.p.l = y;
        else x.p.r = y;
        y.p = x.p;
        x.p = y;

        if (a != null) a.p = y;
        if (b != null) b.p = x;
        if (c != null) c.p = x;
    }

    /*
     *   black                  red
     *   /   \      ->         /   \
     * red   red          black    black
     *  |
     *  x
     * */
    public void turnColor(RbNode x) {
        if (x.color == RbNode.Color.red) {
            if (x.p != null && x.p.color == RbNode.Color.black) {
                if (x.p.r != null && x.p.r.color == RbNode.Color.red) {
                    x.color = RbNode.Color.black;
                    x.p.r.color = RbNode.Color.black;
                    x.p.color = RbNode.Color.black;
                }
            }
        }
    }


    public static class RbNode<K, V> {
        private Color color;
        private K key;
        private V val;
        private RbNode<K, V> p, l, r;

        public enum Color {
            red, black
        }
    }
}
