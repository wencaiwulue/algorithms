package tree;

/**
 * 二叉树结构
 *
 * @author fengcaiwen
 * @since 6/12/2019
 */
@SuppressWarnings("all")
public class BinNode<T extends Comparable> {
    public BinNode<T> parent;
    public BinNode<T> lChild;
    public BinNode<T> rChild;
    public T data;
    public Integer n;

    public BinNode(BinNode parent, T data) {
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
        this.lChild = new BinNode(this, data);
        return this.lChild;
    }

    public BinNode insertAsRc(T data) {
        this.rChild = new BinNode(this, data);
        return this.rChild;
    }

    public BinNode search(BinNode node, T date) {
        if (node == null || node.data == date) return node;
        return search((date.compareTo(node.data) == 1 ? node.rChild : node.lChild), date);
    }

    public BinNode searchLastNotMatch(BinNode node, T date) {
        if (node.rChild == null || node.rChild == null) return node;
        return searchLastNotMatch((date.compareTo(node.data) == 1 ? node.rChild : node.lChild), date);
    }

    public BinNode<T> insert(BinNode<T> node, T date) {
        BinNode search = search(node, date);
        if (search == null) {
            BinNode parent = searchLastNotMatch(node, date);
            // todo optimze
            if (date.compareTo(parent.data) == 1)
                parent.rChild = new BinNode(parent, date);
            else
                parent.lChild = new BinNode(parent, date);
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

    public BinNode delete(BinNode node, T date) {
        BinNode search = search(node, date);
        BinNode backup = search;
        if (search == null || (search.lChild == null && search.rChild == null)) return null;

        BinNode boss;
        if (search.lChild == null) {
            boss = goAlongWithLeft(search.rChild);
//            boss.parent = search.parent;
//            search.parent.lChild = boss;
            search.data = boss.data;
        } else {
            boss = goAlongWithRight(search.lChild);
//            boss.parent = search.parent;
//            search.parent.rChild = boss;
            search.data = boss.data;
        }

        if (boss.parent.lChild == search) boss.parent.lChild = null;
        if (boss.parent.rChild == search) boss.parent.rChild = null;

        boss = null;

        return backup;
    }

    public static void main(String[] args) {
        BinNode node = new BinNode(10);
        node.insertAsLc(5);
        node.insertAsRc(15);
        node.lChild.insertAsLc(3);
        node.lChild.insertAsRc(6);

        node.rChild.insertAsLc(13);
        node.rChild.insertAsRc(16);

        System.out.println(node);

    }


}
