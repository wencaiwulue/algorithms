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

    public BinNode search(BinNode<T> node, T date) {
        if (node == null || node.data == date) return node;
        return search((date.compareTo(node.data) > 0 ? node.rChild : node.lChild), date);
    }

    public BinNode searchLastNotMatch(BinNode<T> node, T date) {
        if (node.lChild == null || node.rChild == null) return node;
        return searchLastNotMatch((date.compareTo(node.data) > 0 ? node.rChild : node.lChild), date);
    }

    public BinNode<T> insert(BinNode<T> node, T data) {
        BinNode search = search(node, data);
        if (search == null) {
            BinNode parent = searchLastNotMatch(node, data);
            // todo optimze
            if (data.compareTo(parent.data) > 0)
                parent.rChild = new BinNode<T>(parent, data);
            else
                parent.lChild = new BinNode<T>(parent, data);
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
        BinNode tobedel = search(node, date);
        if (tobedel == null) return null;

        // 1, if node to be deleted is leaf node, the make it's parent point to null, then done
        if (tobedel.lChild == null && tobedel.rChild == null) {
            if (tobedel.parent.lChild == tobedel) tobedel.parent.lChild = null;
            if (tobedel.parent.rChild == tobedel) tobedel.parent.rChild = null;
        } else {
            // 2, if not, use alternate node to replace the node which to be delete
            // 1), find alternate, make it's parent point to alternate node to be null
            // 2), make to be deleted node's parent point to alternate
            // 3), make to be deleted node's left and right node point to alternate
            BinNode alternate;
            if (tobedel.rChild == null)
                alternate = goAlongWithRight(tobedel.lChild);
            else
                alternate = goAlongWithLeft(tobedel.rChild);
            BinNode bp = alternate.parent;
            if (bp.lChild == alternate) bp.lChild = null;
            if (bp.rChild == alternate) bp.rChild = null;
            alternate.parent = tobedel.parent;
            if (tobedel.parent.lChild == tobedel)
                tobedel.parent.lChild = alternate;
            else if (tobedel.parent.rChild == tobedel)
                tobedel.parent.rChild = alternate;

            alternate.lChild = tobedel.lChild;
            alternate.rChild = tobedel.rChild;
        }
        return tobedel;
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
