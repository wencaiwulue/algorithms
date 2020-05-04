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
        for (int i = 0; i < 5190; i++) {
            map.put(new Key(i), i);// 总共放入1000个元素，会被分成65个bin(bucket)。
        }
        System.out.println(map.entrySet().toArray()[0].getClass());
    }

    public static class Key {
        public Integer value;

        public Key(Integer value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            // 对65取余，会落在0-64之间
            return Objects.hash(value) % 65;// 这里决定了要使用的桶的个数，也就是bin(bucket)的个数
        }
    }
}
