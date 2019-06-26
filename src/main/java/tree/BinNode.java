package tree;

/**
 * 二叉树结构
 *
 * @author fengcaiwen
 * @since 6/12/2019
 */
//@SuppressWarnings("all")
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

    public BinNode search(BinNode<T> node, T date) {
        if (node == null || node.data == date) return node;
        return search((date.compareTo(node.data) == 1 ? node.rChild : node.lChild), date);
    }

    public BinNode searchLastNotMatch(BinNode<T> node, T date) {
        if (node.rChild == null || node.rChild == null) return node;
        return searchLastNotMatch((date.compareTo(node.data) == 1 ? node.rChild : node.lChild), date);
    }

    public BinNode<T> insert(BinNode<T> node, T date) {
        BinNode search = search(node, date);
        if (search == null) {
            BinNode parent = searchLastNotMatch(node, date);
            // todo optimze
            if (date.compareTo(parent.data) == 1)
                parent.rChild = new BinNode<T>(parent, date);
            else
                parent.lChild = new BinNode<T>(parent, date);
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
    public BinNode delete(BinNode node, T date) {
        BinNode del = search(node, date);
        BinNode backup = del;
        if (del == null) return null;

        if (del.lChild == null && del.rChild == null) {
            if (del.parent.lChild == del) del.parent.lChild = null;
            if (del.parent.rChild == del) del.parent.rChild = null;
            del = null;
        } else if (del.lChild == null) {
            BinNode maybe = goAlongWithLeft(del.rChild);
            BinNode bp = maybe.parent;
            if (bp.lChild == maybe) bp.lChild = null;
            if (bp.rChild == maybe) bp.rChild = null;
            maybe.parent = del.parent;
            if (del.parent.lChild == del) {
                del.parent.lChild = maybe;
            } else if (del.parent.rChild == del) {
                del.parent.rChild = maybe;
            }
            maybe.rChild = backup.rChild;
        } else {
            BinNode maybe = goAlongWithLeft(del.rChild);
            BinNode bp = maybe.parent;
            if (bp.lChild == maybe) bp.lChild = null;
            if (bp.rChild == maybe) bp.rChild = null;
            maybe.parent = del.parent;
            if (del.parent.lChild == del) {
                del.parent.lChild = maybe;
            } else if (del.parent.rChild == del) {
                del.parent.rChild = maybe;
            }
            maybe.lChild = backup.lChild;
        }


        return backup;
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
