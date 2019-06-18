import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.Collection;

/**
 * @author fengcaiwen
 * @since 6/18/2019
 */
public class TrieTest {
    public static void main(String[] args) {
        Trie trie = Trie.builder().addKeywords("feng", "cai", "wen").build();
        Collection<Emit> emits = trie.parseText("asdfasdfasffengasdfafcaiasdfasfdqwenasdfasfd");
        emits.forEach(System.out::println);

    }
}
