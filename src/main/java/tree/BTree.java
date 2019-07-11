package tree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 7/5/2019
 */
@SuppressWarnings("all")
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
    // first: split this node into three parts, left, this node, right
    // second: combine this node with it's parent, fix relationship
    // third: check parent needs to be overflow or not, if root, grow one level
    //
    //   [][]                       [][][]
    //   [][][]                     [][][][]                       []
    //         \              -->        /  \           -->       /  \
    //          [][][][]              [][]    []               [][]   []
    //          [][][][][]            [][][]  [][]             [][][] [][]
    //                                                                / \
    //                                                             [][]  [][]
    public void overflow(BTNode node) {
        // 1, if not reach threshold, then return
        if (node.keys.size() <= m - 1) return;

        // find middle of this node key
//        int ceil = (int) Math.ceil(node.keys.size() / 2);
        int middle = BigDecimal.valueOf(node.keys.size()).divide(BigDecimal.valueOf(2), RoundingMode.DOWN).intValue();

        BTNode left = new BTNode();
        BTNode right = new BTNode();
        left.keys.addAll(node.keys.subList(0, middle));
        right.keys.addAll(node.keys.subList(middle + 1, node.keys.size()));
        left.child.addAll(node.child.subList(0, middle + 1));
        right.child.addAll(node.child.subList(middle + 1, node.child.size()));

        // root
        if (node.parent == null) {
            node.parent = new BTNode();
            root = node.parent;
            right.parent = node.parent;
            left.parent = node.parent;
            node.parent.keys.add(node.keys.get(middle));
            node.parent.child.add(0, left);
            node.parent.child.add(1, right);
        } else {
            // find the index of this node's parent child
            int pi = node.parent.child.indexOf(node);
            right.parent = node.parent;
            left.parent = node.parent;
            node.parent.keys.add((pi - 1) + 1, node.keys.get(middle));
            node.parent.child.set(pi, left);
            node.parent.child.add(pi + 1, right);
        }

        overflow(node.parent);
    }

    // this node needs to underflow
    // try to borrow node from left or right brother, if brother do not have enough node to borrow(bigger than ⌈m/2⌉ or not)
    // try to combine this node parent and right or left node become a big node
    //    [][][][][]         [][][][]                          [][][][]         [][][][]
    //          /\    -->         \                 or             /  \   -->       / \
    //        []  []               [][][]                  [][][][]    []     [][][]   [][]
    public void underflow(BTNode node) {
        int min = BigDecimal.valueOf(m).divide(BigDecimal.valueOf(2), RoundingMode.CEILING).subtract(BigDecimal.ONE).intValue();

        if (node.keys.size() > min) return;

        int rank = node.parent.child.indexOf(node);
        BTNode lb = rank - 1 < 0 ? null : (BTNode) node.parent.child.get(rank - 1);
        BTNode rb = rank + 1 > node.parent.child.size() ? null : (BTNode) node.parent.child.get(rank + 1);
        if (lb != null && lb.keys.size() - 1 > min) {
            // borrow from left brother
            T bak = (T) node.parent.keys.get(rank - 1);
            node.parent.keys.set(rank - 1, (T) lb.keys.remove(lb.keys.size() - 1));
            node.keys.add(0, bak);
            node.child.add(0, lb.child.remove(lb.child.size() - 1));
        } else if (rb != null && rb.keys.size() - 1 > min) {
            // borrow from right brother
            T bak = (T) node.parent.keys.get(rank - 1);
            node.parent.keys.set(rank - 1, rb.keys.remove(0));
            node.keys.add(0, bak);
            node.child.add(0, rb.child.remove(0));
        } else {
            // combine
            T parent = (T) node.parent.keys.get(rank - 1);
            BTNode new0 = new BTNode<>(parent, lb, rb);
            if (lb != null) node.child.remove(lb);
            if (rb != null) node.child.remove(rb);
            // todo
            node.child.set(rank, new0);
        }

    }

    // maybe need to overflow
    public boolean insert(T key) {
        BTNode search = search(root, key);
        if (search != null) return false;

        // should insert at hot node, todo attention this line, because of java feature reference, we can't change reference
        BTNode realHot = hot.parent;

        int p = 0;
        for (int i = 0; i < realHot.keys.size(); i++) {
            int cmp = key.compareTo(realHot.keys.get(i));
            if (cmp > 0)
                p = i + 1;
            else
                break;
        }

        realHot.keys.add(p, key);
        realHot.child.add(p + 1, null);

        overflow(realHot);

        return true;
    }

    // maybe need to underflow
    // first: use successor to change this key map to node, note: successor is leaf node --keep balance
    // second: remove the leaf node --easy
    // if size() < ⌈m/2⌉-1, needs to underflow
    public void remove(T key) {
        BTNode search = search(root, key);
        if (search == null) return;

        BTNode realHot = hot.parent;

        // this is leaf node
        if (search.child.size() == 0) {
            int rank = search.keys.indexOf(key);
            search.keys.remove(key);
            search.child.remove(rank + 1);
        } else {
            // this is inner node, use successor to replace this node

            // find successor
            int rank = search.keys.indexOf(key);
            BTNode u = (BTNode) search.child.get(rank + 1);
            while (true) {
                if (u.child.size() != 0 && u.child.get(0) != null)
                    u = (BTNode) u.child.get(0);
                else
                    break;
            }

            // replace
            search.keys.set(rank, u.keys.get(0));
            u.keys.remove(0);
            u.child.remove(1);

            // maybe underflow
            underflow(u);
        }

        // find successor, it is hot
        while (hot.child != null && realHot.child.size() != 0) {
            realHot = (BTNode) realHot.child.get(0);
        }

        // exchange
        search.parent = realHot.parent;
        int index = search.keys.indexOf(key);
        search.keys.set(index, realHot.keys.get(0));
        // remove leaf
        realHot.keys.remove(0);
        realHot.child.remove(1);

        // ⌈m/2⌉-1
        int minBranch = BigDecimal.valueOf(m).divide(BigDecimal.valueOf(2), RoundingMode.CEILING).subtract(BigDecimal.ONE).intValue();
        if (realHot.keys.size() < minBranch) underflow(realHot);
    }

    public BTNode search(BTNode node, T key) {
        while (true) {
            hot.parent = node;
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
                        return null;
                }

            if (m + 1 > node.child.size()) return null;
            node = (BTNode) node.child.get(m + 1);
            if (node == null) return null;
        }
    }

    public static class BTNode<T extends Comparable> {
        public BTNode parent;
        public List<T> keys = new ArrayList<>(); // node keys
        public List<BTNode> child = new ArrayList<>(); // map to child

        // root node
        public BTNode() {
            parent = null;
            child.add(0, null);
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
