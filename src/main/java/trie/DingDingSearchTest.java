package trie;

/**
 * @author fengcaiwen
 * @since 6/24/2019
 */
public class DingDingSearchTest {
    private int R = 26;
    private Node root = new Node(new Node[R], 0);

    private void addStr(String str) {
        if (findStr(root, str) != null) return;

        char[] chars = str.toCharArray();
        Node last = root;
        Node pre = root;
        int i = 0;
        while (i < chars.length) {
            last = find(pre, chars[i]);
            if (last == null) break;
            i++;
            pre = last;
        }

        for (int j = i; j < chars.length; j++) {
            Node add;
            // make last node become leaf node todo optimize
            if (j == chars.length - 1) {
                add = add(pre, chars[j], true);
            } else {
                add = add(pre, chars[j], false);
            }
            if (add == null) return;
            pre = add;
        }
    }

    public Node add(Node node, char c, boolean leaf) {
        if (node == null || node.key == c) return null;

        int index = (int) c - 97;
        Node node1 = node.next[index];
        if (node1 == null) {
            Node newNode = new Node(new Node[R], c, 0);
            if (leaf) {
                newNode.value = 1;
            }
            node.next[index] = newNode;
            return newNode;
        } else {
            return node1;
        }
    }

    private Node findStr(String str) {
        return findStr(root, str);
    }

    /**
     * return
     */
    private Node findStr(Node node, String str) {
        if (node == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        int i = 0;
        Node last = node;
        while (i < chars.length) {
            last = find(last, chars[i++]);
            if (last == null) {
                return null;
            }
        }
        return last;
    }

    private Node find(Node node, char c) {
        if (root == null) return null;
        if (node.key == c) {
            return node;
        }

        int index = (int) c - 97;
        Node next = node.next[index];
        if (next == null) {
            return null;
        } else {
            return find(node.next[index], c);
        }

    }

    public static class Node {
        /**
         * point to next reference, means have one path
         */
        private Node[] next;
        private char key;
        private int value;

        Node(Node[] next, char key, int value) {
            this.next = next;
            this.key = key;
            this.value = value;
        }

        Node(Node[] next, int value) {
            this.next = next;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        DingDingSearchTest searcher = new DingDingSearchTest();
        String str = "f";
        searcher.addStr("fcw");
        searcher.addStr("hjh");
        searcher.addStr("asdf");
        searcher.addStr("fd");
        searcher.addStr("dfdf");
        searcher.addStr("sdf");
        searcher.addStr("asdffsdf");
        searcher.addStr("asdfdfdfsd");
        for (int i = 1; i <= str.length(); i++) {
            String s = str.substring(0, i);
            Node f = searcher.findStr(s);
            System.out.println(f == null || f.value == 0 ? "not found: " + s : "found: " + s);
        }
    }
}
