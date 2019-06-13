package tree;

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

    public BinNode(BinNode parent, T data) {
        this.parent = parent;
        this.data = data;
    }

    public int size() {
        int s = 1;
        if (this.lChild != null) {
            s += this.lChild.size();
        }
        if (this.rChild != null) {
            s += this.rChild.size();
        }
        return s;
    }

    public BinNode insertAsLc(T data) {
        this.lChild = new BinNode(this, data);
        return this.lChild;
    }

    public BinNode insertAsRc(T data) {
        this.rChild = new BinNode(this, data);
        return this.rChild;
    }


}
