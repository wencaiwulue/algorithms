package trie;

import java.util.Collections;
import java.util.Set;

/**
 * @author fengcaiwen
 * @since 6/24/2019
 */
public class DingDingSearchTest {
    private DiyTrieTest trie;

    public DingDingSearchTest(DiyTrieTest trie) {
        this.trie = trie;
    }

    public Set<String> findName(String name) {
        DiyTrieTest.Node node = trie.findWord(name);
        if (node != null)
            return node.value;
        return Collections.emptySet();
    }

    public void addName(String name) {
        for (int i = 0, j = name.length(); i < name.length(); i++, j--) {
            String s = name.substring(0, i + 1);
            String s1 = name.substring(j - 1);
//            System.out.println(s);
//            System.out.println(s1);
            trie.addWord(s, name);
            trie.addWord(s1, name);
        }
    }

    public static void main(String[] args) {

        DiyTrieTest trie = new DiyTrieTest();
        DingDingSearchTest searcher = new DingDingSearchTest(trie);
        String str = "jh";
        searcher.addName("fcw");
        searcher.addName("hjh");
        searcher.addName("ljw");
        searcher.addName("ljh");
        Set<String> node = searcher.findName(str);
        node.forEach(System.out::println);
    }
}
