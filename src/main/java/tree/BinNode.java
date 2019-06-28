package tree;

/**
 * 二叉树结构
 *
 * @author fengcaiwen
 * @since 6/12/2019
 */
@SuppressWarnings("unchecked")
public class BinNode<T extends Comparable> {
    public BinNode<T> parent;
    public BinNode<T> lChild;
    public BinNode<T> rChild;
    public T data;
    public Integer n;

    public BinNode(BinNode<T> parent, T data) {
        this.parent = parent;
        this.data = data;
    }

    public BinNode(T data) {
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
        this.lChild = new BinNode<T>(this, data);
        return this.lChild;
    }

    public BinNode insertAsRc(T data) {
        this.rChild = new BinNode<T>(this, data);
        return this.rChild;
    }

    public BinNode search(BinNode<T> node, T date, BinNode hot) {
        if (node == null || node.data == date) return node;
        hot.parent = node;
        return search((date.compareTo(node.data) > 0 ? node.rChild : node.lChild), date, hot);
    }

    public BinNode<T> insert(BinNode<T> node, T data) {

        BinNode hot = new BinNode(-1);
        BinNode search = search(node, data, hot);
        hot = hot.parent;
        if (search == null) {
            // todo optimze
            if (data.compareTo(hot.data) > 0)
                hot.rChild = new BinNode<T>(hot, data);
            else
                hot.lChild = new BinNode<T>(hot, data);
        }
        return search;
    }

    public BinNode goAlongWithLeft(BinNode node) {
        if (node.lChild == null) return node;
        return goAlongWithLeft(node.lChild);
    }

    public BinNode goAlongWithRight(BinNode node) {
        if (node.rChild == null) return node;
        return goAlongWithRight(node.rChild);
    }

    // todo
    public BinNode delete(BinNode<T> node, T date) {
        BinNode hot = new BinNode(-1);
        BinNode remove = search(node, date, hot);
        if (remove == null) return null;

        // 1, if node to be deleted is leaf node, the make it's parent point to null, then done
        if (remove.lChild == null && remove.rChild == null) {
            if (remove.parent.lChild == remove) remove.parent.lChild = null;
            if (remove.parent.rChild == remove) remove.parent.rChild = null;
        } else {
            // 2, if not, use alternate node to replace the node which to be delete
            // 1), find alternate, make it's parent point to alternate node to be null
            // 2), make to be deleted node's parent point to alternate
            // 3), make to be deleted node's left and right node point to alternate
            BinNode alternate;
            if (remove.rChild == null)
                alternate = goAlongWithRight(remove.lChild);
            else
                alternate = goAlongWithLeft(remove.rChild);
            BinNode bp = alternate.parent;
            if (bp.lChild == alternate) bp.lChild = null;
            if (bp.rChild == alternate) bp.rChild = null;
            alternate.parent = remove.parent;
            if (remove.parent.lChild == remove)
                remove.parent.lChild = alternate;
            else if (remove.parent.rChild == remove)
                remove.parent.rChild = alternate;

            alternate.lChild = remove.lChild;
            alternate.rChild = remove.rChild;
        }
        return remove;
    }

    public static void main(String[] args) {
        BinNode<Integer> node = new BinNode<>(10);
        node.insertAsLc(5);
        node.insertAsRc(15);
        node.lChild.insertAsLc(3);
        node.lChild.insertAsRc(6);

        node.rChild.insertAsLc(13);
        node.rChild.insertAsRc(16);

        System.out.println(node);

    }


}
