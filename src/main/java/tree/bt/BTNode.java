package tree.bt;

import org.jetbrains.annotations.NotNull;

/**
 * @author fengcaiwen
 * @since 12/13/2019
 */
//todo
public class BTNode<T extends Comparable<T>> implements Comparable<BTNode<T>> {
    private BTNode<T> parent = null;
    private Vector<T> key = new Vector<>();
    private Vector<BTNode<T>> child = new Vector<>();

    public BTNode() {
        parent = null;
        child.add(0, null);
    }

    public BTNode(T e, BTNode<T> lc, BTNode<T> rc) {
        parent = null;
        key.add(0, e);
        child.add(0, lc);
        child.add(1, rc);
        if (lc != null) {
            lc.parent = this;
        }
        if (rc != null) {
            rc.parent = this;
        }
    }

    public BTNode<T> getParent() {
        return parent;
    }

    public void setParent(BTNode<T> parent) {
        this.parent = parent;
    }

    public Vector<T> getKey() {
        return key;
    }

    public void setKey(Vector<T> key) {
        this.key = key;
    }

    public Vector<BTNode<T>> getChild() {
        return child;
    }

    public void setChild(Vector<BTNode<T>> child) {
        this.child = child;
    }

    public static void main(String[] args) {
        Vector<String> vector = new Vector<>(4);
        vector.add(0, null);
        vector.setElementAt("", 0);
        System.out.println(vector.size());
    }

    @Override
    public int compareTo(@NotNull BTNode<T> o) {
        return 0;
    }
}
