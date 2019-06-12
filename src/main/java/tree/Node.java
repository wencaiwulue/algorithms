package tree;

/**
 * @author fengcaiwen
 * @since 6/12/2019
 */
@SuppressWarnings("all")
public class Node<K extends Comparable, V> {
    public Node root;
    // todo
    public Node parent;
    public Node lch;
    public Node rch;

    // todo
    private Integer n;

    public K key;
    public V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void insert(K key, V value) {
        insert(root, key, value);
    }

    public void insert(Node node, K key, V value) {
        if (root == null) {
            root = new Node(key, value);
        } else {
            int cmp = key.compareTo(root.key);
            if (cmp > 0) {
                insert(node.rch, key, value);
            } else if (cmp < 0) {
                insert(node.lch, key, value);
            } else {
                node.value = value;
            }
        }
    }

    public Node search(K key) {
        return search(root, key);
    }

    public Node search(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return search(node.rch, key);
        } else if (cmp < 0) {
            return search(node.lch, key);
        } else {
            return node;
        }
    }

    public void remove(K key) {
        remove(root, key);
    }

    // todo
    public boolean remove(Node node, K key) {
        Node search = search(node, key);
        if (search == null) {
            return false;
        }

        Node left = goAlongAsLeft(search.rch);
        search.parent = left;
        return true;

    }

    public Node goAlongAsLeft(Node node) {
        if (node.lch != null) {
            return goAlongAsLeft(node.lch);
        }
        return node;
    }

    public Node goAlongAsRight(Node node) {
        if (node.rch != null) {
            return goAlongAsRight(node.rch);
        }
        return node;
    }


}
