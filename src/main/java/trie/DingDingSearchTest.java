package trie;

import java.util.Collections;
import java.util.Set;

/**
 * @author fengcaiwen
 * @since 6/24/2019
 */
public class DingDingSearchTest {
    private DiyTrie trie;

    public DingDingSearchTest(DiyTrie trie) {
        this.trie = trie;
    }

    public Set<String> findName(String name) {
        DiyTrie.Node node = trie.findWord(name);
        if (node != null)
            return node.value;
        return Collections.emptySet();
    }

    public void addName(String name) {
        for (int i = 0, j = name.length(); i < name.length(); i++, j--) {
            String s = name.substring(0, i + 1);
            String s1 = name.substring(j - 1);
            trie.addWord(s, name);
            trie.addWord(s1, name);
        }
    }

    public static void main(String[] args) {
        DiyTrie trie = new DiyTrie();
        DingDingSearchTest searcher = new DingDingSearchTest(trie);

        String str = "l";
        searcher.addName("fcw");
        searcher.addName("hjh");
        searcher.addName("ljw");
        searcher.addName("ljh");

        Set<String> node = searcher.findName(str);
        node.forEach(System.out::println);
    }
}
