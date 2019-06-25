package trie;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fengcaiwen
 * @since 6/24/2019
 */
public class DiyTrieTest {
    private int R = 26;
    private Node root = new Node(new Node[R], 0);

    public void addWord(String word) {
        addWord(word, word);
    }

    public void addWord(String word, String val) {
        // 1, if not null, then add value to this key
        Node found = findWord(root, word);
        if (found != null) {
            found.value.add(val);
            return;
        }

        // 2, if null, then find the last match node
        char[] chars = word.toCharArray();
        Node pre = root;
        int i = 0;
        while (i < chars.length) {
            Node last = find(pre, chars[i]);
            if (last == null) break;
            i++;
            pre = last;
        }

        // 3, form last match node, add new char
        for (int j = i; j < chars.length; j++) {
            Node add;
            // make last node become leaf node todo optimize
            if (j == chars.length - 1)
                add = add(pre, chars[j], val, true);
            else
                add = add(pre, chars[j], val, false);

            if (add == null) return;
            pre = add;
        }
    }

    /**
     * @param node add key after this node
     * @param key  key
     * @param val  value
     * @param leaf is ledf or not
     */
    public Node add(Node node, char key, String val, boolean leaf) {
        if (node == null || node.key == key) return null;

        int index = (int) key - 97;
        Node node1 = node.next[index];
        if (node1 == null) {
            Node newNode = new Node(new Node[R], key, val, 0);
            if (leaf)
                newNode.isLeaf = 1;
            node.next[index] = newNode;
            return newNode;
        } else {
            node1.value.add(val);
            return node1;
        }
    }

    public Node findWord(String str) {
        return findWord(root, str);
    }

    /**
     * return
     */
    private Node findWord(Node node, String word) {
        if (node == null) return null;

        char[] chars = word.toCharArray();
        int i = 0;
        Node last = node;
        while (i < chars.length) {
            last = find(last, chars[i++]);
            if (last == null)
                return null;
        }
        return last;
    }

    private Node find(Node node, char key) {
        if (root == null || node.key == key) return node;

        int index = (int) key - 97;
        Node next = node.next[index];
        if (next == null)
            return null;
        else
            return find(node.next[index], key);

    }

    public static class Node {
        /**
         * point to next reference, means have one path
         */
        public Node[] next;
        public char key;
        public int isLeaf;
        public Set<String> value;

        Node(Node[] next, char key, String value, int isLeaf) {
            this.next = next;
            this.key = key;
            this.isLeaf = isLeaf;
            this.value = new HashSet<>();
            this.value.add(value);
        }

        Node(Node[] next, int isLeaf) {
            this.next = next;
            this.isLeaf = isLeaf;
        }
    }

    public static void main(String[] args) {

        DiyTrieTest trie = new DiyTrieTest();
        String str = "fc";
        trie.addWord("fcw");
        trie.addWord("hjh");
        trie.addWord("asdf");
        trie.addWord("fd");
        trie.addWord("dfdf");
        trie.addWord("sdf");
        trie.addWord("asdffsdf");
        trie.addWord("asdfdfdfsd");
        for (int i = 1; i <= str.length(); i++) {
            String s = str.substring(0, i);
            Node f = trie.findWord(s);
            System.out.println(f == null || f.isLeaf == 0 ? "not found: " + s : "found: " + s + " value: " + f.value);
        }
    }
}
