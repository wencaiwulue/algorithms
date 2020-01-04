package algorithm;

import org.jetbrains.annotations.NotNull;
import sort.PQTest;

import java.util.HashMap;

/**
 * 最好的lru就是LinkedHashMap
 */
public class LRU<K, V> {

    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new LRU<>(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
        lru.put(4, 4);
        System.out.println(1);
        System.out.println(3);
        System.out.println(4);
    }

    private int max;
    private int capacity;
    private PQTest<Entity> tree;
    private HashMap<K, Entity> keys;

    private LRU(int capacity) {
        this.capacity = capacity;
        tree = new PQTest<>(capacity);
        keys = new HashMap<>(capacity);
    }


    public V get(K key) {
        if (!keys.containsKey(key)) return null;

        Entity entity = keys.get(key);
        tree.del(entity);
        entity = new Entity(key, max++, entity.value);
        tree.insert(entity);
        return entity.value;
    }

    public void put(K k, V v) {
        if (!keys.containsKey(k)) {
            // if capacity is less, then delete the fewest used key
            if (tree.size() >= capacity) {
                Entity poll = tree.delMin();
                assert poll != null;
                keys.remove(poll.key);
            }
        } else {
            Entity entity = keys.get(k);
            tree.del(entity);
        }
        Entity entity = new Entity(k, max++, v);
        tree.insert(entity);
        keys.put(k, entity);
    }

    class Entity implements Comparable<Entity> {
        private K key;
        private long priority;
        private V value;

        Entity(K key, int priority, V value) {
            this.key = key;
            this.priority = priority;
            this.value = value;
        }

        @Override
        public int compareTo(@NotNull Entity o) {
            return Long.compare(priority, o.priority);
        }
    }
}
