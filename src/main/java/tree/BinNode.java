package tree;

import java.util.Iterator;

/**
 * 二叉树结构
 *
 * @author fengcaiwen
 * @since 6/12/2019
 */
@SuppressWarnings("all")
public class BinNode<T> {
    public BinNode parent;
    public BinNode lChild;
    public BinNode rChild;
    public T data;
    public Integer n;

    public int size() {
        return 0;
    }

    public BinNode insertAsLc() {
        return null;
    }

    public BinNode insertAsRc() {
        return null;
    }

    public Iterator travLevel() {
        return null;
    }

    public Iterator travPre() {
        return null;
    }

    public Iterator travIn() {
        return null;
    }

    public Iterator travPost() {
        return null;
    }


}
