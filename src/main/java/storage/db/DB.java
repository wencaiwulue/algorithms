package storage.db;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author naison
 * @since 4/1/2020 10:53
 */
public class DB {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\89570\\Documents\\test4.txt";
        File file = new File(path);
        if (!file.exists()) file.createNewFile();

        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        FileChannel channel = raf.getChannel();
        byte[] bytes = "i am the son of sun".getBytes();
        MappedByteBuffer mapped = channel.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);
        mapped.put(bytes);
        mapped.force();
        mapped = channel.map(FileChannel.MapMode.READ_WRITE, bytes.length, bytes.length);
        mapped.put(bytes);
        mapped.force();
        System.out.println();
    }
}
