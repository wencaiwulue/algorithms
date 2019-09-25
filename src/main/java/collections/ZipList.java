package collections;

/**
 * like a generic array
 *
 * @author fengcaiwen
 * @since 2019年9月22日
 */
public class ZipList<T> {
    int total_bytes;
    int element_amount;
    T[] entries;
    int end = 0xff;


    public static class Entry {
        int length;
        int encoding;
        byte[] content;
    }

}


