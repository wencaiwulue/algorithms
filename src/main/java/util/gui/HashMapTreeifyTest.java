package util.gui;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author fengcaiwen
 * @since 9/18/2019
 */
public class HashMapTreeifyTest {
    public static void main(String[] args) {
        HashMap<Key, Object> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            map.put(new Key(i), i);
        }
        System.out.println(map.keySet());
    }

    public static class Key {
        public Integer value;

        public Key(Integer value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Key key = (Key) o;
//            return Objects.equals(value, key.value);
            return false;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "value=" + value +
                    '}';
        }
    }
}
