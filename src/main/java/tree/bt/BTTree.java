package tree.bt;

import java.util.concurrent.atomic.AtomicReference;

/**
 * B tree base on inner static class Bt-node
 *
 * @author fengcaiwen
 * @since 7/5/2019
 */
//todo
public class BTTree<T extends Comparable<T>> {
    private int size; // 关键码总数
    private int order;// B-树阶次
    private BTNode<T> root;
    private AtomicReference<BTNode<T>> hot = new AtomicReference<>(null);//search方法最后访问的节点

    public BTTree(int order) {
        this.order = order;
        size = 0;
        root = new BTNode<>();
    }

    public int getOrder() {
        return order;
    }

    public int getSize() {
        return size;
    }

    public BTNode<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public BTNode<T> search(T e) {//查找关键码e
        BTNode<T> v = root;// 从根出发
        hot.set(null);
        while (v != null) {// 逐层查找
            int r = v.getKey().search(e);//在当前节点中，找到不大于e的最大关键码
            if (r >= 0 && e == v.getKey().get(r)) return v;// 成功

            hot.set(v);// 否则转向对应子树
            v = v.getChild().elementAt(r + 1);
        }//这里在向量内是二分查找，但对通常的_order可直接顺序查找
        return null;
    }

    public boolean insert(T e) {
        BTNode<T> v = search(e);
        if (v != null) return false;

        int r = hot.get().getKey().search(e);
        hot.get().getKey().add(r + 1, e);
        hot.get().getChild().add(r + 2, null);
        size++;
        overFlow(hot.get());
        return true;
    }

    private void overFlow(BTNode<T> v) {
        if (order >= v.getChild().size()) return;// 不满足上浮条件

        int s = order / 2;
        BTNode<T> u = new BTNode<>();
        for (int j = 0; j < order - s - 1; j++) {
            u.getChild().add(j, v.getChild().remove(s + 1));
            u.getKey().add(j, v.getKey().remove(s + 1));
        }
        u.getChild().add(order - s - 1, v.getChild().remove(s + 1));
        if (u.getChild().get(0) != null) {
            for (int j = 0; j < order - s; j++) {
                u.getChild().get(j).setParent(u);
            }
        }
        BTNode<T> p = v.getParent();
        if (p == null) {
            root = p = new BTNode<>();
            p.getChild().add(0, v);
            v.setParent(p);
        }
        int r = 1 + p.getKey().search(v.getKey().get(0));
        p.getKey().add(r, v.getKey().remove(s));
        p.getChild().add(r + 1, u);
        u.setParent(p);
        overFlow(p);
    }

    public boolean remove(T e) {
        BTNode<T> v = search(e);
        if (v == null) return false;

        int r = v.getKey().search(e);
        if (v.getChild().get(0) != null) {
            BTNode<T> u = v.getChild().get(r + 1);
            while (u.getChild().get(0) != null) {
                u = u.getChild().get(0);
            }
            v.getKey().add(r, u.getKey().get(0));
            v = u;
            r = 0;
        }
        v.getKey().remove(r);
        v.getChild().remove(r + 1);
        size--;
        underFlow(v);
        return true;
    }

    private void underFlow(BTNode<T> v) {
        if ((order + 1) / 2 <= v.getChild().size()) return;

        BTNode<T> p = v.getParent();
        if (p == null) {
            if (v.getKey().size() == 0 && v.getChild().get(0) != null) {
                root = v.getChild().get(0);
                root.setParent(null);
                v.getChild().add(0, null);
            }
            return;
        }

        int r = 0;
        while (p.getChild().get(r) != v) {
            r++;
        }

        //1, 向左兄弟借
        if (0 < r) {
            BTNode<T> ls = p.getChild().get(r - 1);
            if ((order + 1) / 2 < ls.getChild().size()) {
                System.out.println("case 1");
                v.getKey().add(0, p.getKey().get(r - 1));
                p.getKey().add(r - 1, ls.getKey().remove(ls.getKey().size() - 1));
                v.getChild().add(0, ls.getChild().remove(ls.getChild().size() - 1));
                if (v.getChild().get(0) != null) {
                    v.getChild().get(0).setParent(v);
                }
                return;
            }
        }

        // 2, 向有兄弟借
        if (p.getChild().size() - 1 > r) {
            BTNode<T> rs = p.getChild().get(r + 1);
            if ((order + 1) / 2 < rs.getChild().size()) {
                System.out.println("case 2");
                v.getKey().add(v.getKey().size(), p.getKey().get(r));
                p.getKey().add(r, rs.getKey().remove(0));
                v.getChild().add(v.getChild().size(), rs.getChild().remove(0));
            }
            if (v.getChild().get(v.getChild().size() - 1) != null) {
                v.getChild().get(v.getChild().size() - 1).setParent(v);
            }
            return;
        }

        if (0 < r) {
            System.out.println("case 3");
            BTNode<T> ls = p.getChild().get(r - 1);
            ls.getKey().add(ls.getKey().size(), p.getKey().remove(r - 1));
            p.getChild().remove(r);
            ls.getChild().add(ls.getChild().size(), v.getChild().remove(0));
            if (ls.getChild().get(ls.getChild().size() - 1) != null) {
                ls.getChild().get(ls.getChild().size() - 1).setParent(ls);
            }
            while (!v.getKey().isEmpty()) {
                ls.getKey().add(ls.getKey().size(), v.getKey().remove(0));
                ls.getChild().add(ls.getChild().size(), v.getChild().remove(0));
                if (ls.getChild().get(ls.getChild().size() - 1) != null) {
                    ls.getChild().get(ls.getChild().size() - 1).setParent(ls);
                }
            }
        } else {
            System.out.println("case 4");
            BTNode<T> rs = p.getChild().get(r + 1);
            rs.getKey().add(0, v.getKey().remove(v.getChild().size() - 1));
            p.getChild().remove(r);
            rs.getChild().add(0, v.getChild().remove(v.getChild().size() - 1));
            if (rs.getChild().get(0) != null) {
                rs.getChild().get(0).setParent(rs);
            }
            while (!v.getKey().isEmpty()) {
                rs.getKey().add(0, v.getKey().remove(v.getKey().size() - 1));
                rs.getChild().add(0, v.getChild().remove(v.getChild().size() - 1));
                if (rs.getChild().get(0) != null) {
                    rs.getChild().get(0).setParent(rs);
                }
            }
        }
        underFlow(p);
        return;
    }

    public static void main(String[] args) {
        BTTree<Integer> tree = new BTTree<>(3);
        for (int i = 0; i < 1000; i++) {
            tree.insert(i);
        }

        tree.remove(1000 / 2);
        System.out.println(tree.size);
    }
}
