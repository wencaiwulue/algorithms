package tree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 7/5/2019
 */
@SuppressWarnings("unchecked")
public class BTree<T extends Comparable> {

    // root
    public BTNode root;

    // record search api last visit node
    public BTNode hot = new BTNode();

    // m: the max number of branch of inner node hold,
    // ⌈m/2⌉: the min number of branch of inner node hold,
    // m-1: the max keys hold
    // ⌈m/2⌉-1: the min keys hold
    // if n set to 7 it called (4,7)-tree, 4-->7/2 ceiling
    // (⌈m/2⌉,m)-tree
    public int m;

    public BTree(BTNode root, int m) {
        this.root = root;
        this.m = m;
    }

    // this node needs to overflow
    // first: take the min key and node to it's parent
    // second: if it's parent also needs to flow, try step first
    // third: until to root, maybe all tree grow one level
    //
    // [][][][]                   [][][][][]                      []
    //         \              -->           \           -->      /  \
    //          [][][][][]                   [][][][]         [][]   [][]
    //                                                                   \
    //                                                                    [][][][]
    public void overflow(BTNode node) {
        T o = (T) node.keys.remove(0);
        BTNode b = (BTNode) node.child.remove(1);

        int middle = BigDecimal.valueOf(node.keys.size()).divide(BigDecimal.valueOf(2), RoundingMode.CEILING).intValue();
        BTNode parent = node.parent;
        if (parent != null) {
            parent.keys.add(o);
            parent.child.add(b);
            if (parent.keys.size() > m - 1) overflow(parent);
        } else {
            // root node, split into two parts
            BTNode lc = new BTNode();
            lc.parent = b;
            lc.keys.addAll(node.keys.subList(0, middle));
            lc.child.addAll(node.child.subList(0, middle + 1));

            BTNode rc = new BTNode();
            rc.parent = b;
            rc.keys.addAll(node.keys.subList(middle, node.keys.size() - 1));
            rc.child.addAll(node.child.subList(middle + 1, node.child.size() - 1));

            b.parent = null;
            // todo check it
            b.child.add(0, lc);
            b.child.add(1, rc);
        }
    }

    // this node needs to underflow
    // try to borrow node from left or right brother, if brother do not have enough node to borrow(bigger than ⌈m/2⌉ or not)
    // try to combine this node parent and right or left node become a big node
    //   [][][][][]         [][][][]
    //          /\    -->         \
    //        []  []               [][][]
    public void underflow(BTNode node) {

        int i = node.parent.child.indexOf(node);
        BTNode lb = (BTNode) node.parent.child.get(i - 1);
        BTNode rb = (BTNode) node.parent.child.get(i + 1);
        int minBranch = BigDecimal.valueOf(m).divide(BigDecimal.valueOf(2), RoundingMode.CEILING).subtract(BigDecimal.ONE).intValue();
        // combine
        if ((lb.keys.size() - 1 < minBranch) && (rb.keys.size() - 1 < minBranch)) {
            T o = (T) node.parent.keys.get(i - 1);

        } else if (lb.keys.size() - 1 < minBranch) {

            // borrow
        } else if (rb.keys.size() - 1 < minBranch) {
            // borrow
        } else {
            // borrow
        }

        // todo
    }

    // maybe need to overflow
    public boolean insert(T key, BTNode lc, BTNode rc) {
        BTNode search = search(root, key);
        if (search != null) return false;

        // should insert at hot node
        for (int i = 0; i < hot.keys.size(); i++) {
            int cmp = key.compareTo(hot.keys.get(i));
            if (cmp < 0) {
                hot.keys.add(i, key);
                hot.child.add(i + 1, new BTNode(key, (BTNode) hot.child.get(i), (BTNode) hot.child.get(i + 1)));
                break;
            }
        }

        if (hot.keys.size() > m - 1) overflow(hot);

        return true;
    }

    // maybe need to underflow
    // first: use successor to change this key map to node, note: successor is leaf node --keep balance
    // second: remove the leaf node --easy
    // if size() < ⌈m/2⌉-1, needs to underflow
    public void remove(T key) {
        BTNode search = search(root, key);
        if (search == null) return;

        // find successor, it is hot
        while (hot.child != null && hot.child.size() != 0) {
            hot = (BTNode) hot.child.get(0);
        }

        // exchange
        search.parent = hot.parent;
        int index = search.keys.indexOf(key);
        search.keys.set(index, hot.keys.get(0));
        // remove leaf
        hot.keys.remove(0);
        hot.child.remove(1);

        // ⌈m/2⌉-1
        int minBranch = BigDecimal.valueOf(m).divide(BigDecimal.valueOf(2), RoundingMode.CEILING).subtract(BigDecimal.ONE).intValue();
        if (hot.keys.size() < minBranch) underflow(hot);
    }

    public BTNode search(BTNode node, T key) {
        while (true) {
            hot = node;
            int m = 0;
            // find key match or not
            for (int i = 0; i < node.keys.size(); i++)
                if (node.keys.get(i) != null) {
                    int cmp = key.compareTo(node.keys.get(i));
                    if (cmp == 0)
                        return node;
                    else if (cmp < 0)
                        m = i;
                    else
                        break;
                }


            BTNode child = (BTNode) node.child.get(m + 1);
            if (child == null) return null;
            node = child;
        }
    }

    public static class BTNode<T extends Comparable> {
        public BTNode parent;
        public List<T> keys = new ArrayList<>(); // node keys
        public List<BTNode> child = new ArrayList<>(); // map to child

        // root node
        public BTNode() {
            parent = null;
            child.add(1, null);
        }

        // another part node
        public BTNode(T key, BTNode lc, BTNode rc) {
            parent = null;
            keys.add(0, key);
            child.add(0, lc);
            child.add(1, rc);
            if (lc != null) lc.parent = this;
            if (rc != null) rc.parent = this;
        }


    }
}
