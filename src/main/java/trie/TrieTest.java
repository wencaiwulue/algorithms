package trie;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.junit.Test;

import java.util.Collection;

/**
 * @author fengcaiwen
 * @since 6/18/2019
 */
public class TrieTest {
    @Test
    public void main() {
        Trie trie = Trie.builder().addKeywords("feng", "cai", "wen").build();
        Collection<Emit> emits = trie.parseText("asdfasdfasffengasdfafcaiasdfasfdqwenasdfasfd");
        emits.forEach(System.out::println);
    }
}
