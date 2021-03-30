package storage;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author naison
 * @since 3/30/2020 17:18
 */
public class SerializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int m = 1/*0000000*/;
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>(m);
        long start = System.nanoTime();
        for (int i = 0; i < m; i++) {
            map.put(String.valueOf(i), i);
        }
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(new File("C:\\Users\\89570\\Documents\\test1.txt")));
        outputStream.writeObject(map);
        outputStream.flush();
        outputStream.close();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));


        start = System.nanoTime();
        ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(new File("C:\\Users\\89570\\Documents\\test1.txt")));
        @SuppressWarnings("all")
        ConcurrentHashMap<String, Object> hashMap = (ConcurrentHashMap<String, Object>) inputStream.readObject();
        inputStream.close();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
        System.out.println();

        byte[] bytes = "abc".getBytes();
        System.out.println();


    }
}
