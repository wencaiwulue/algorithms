package storage;

import com.google.common.primitives.Bytes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author fengcaiwen
 * @since 9/24/2019
 */
public class StorageTest {
    public static void main(String[] args) throws IOException {
        String path = "D:\\test.txt";
        writeTest(path);
        readTest(path);
    }

    private static void writeTest(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) file.createNewFile();
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel channel = raf.getChannel();
        MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, Integer.MAX_VALUE);
        byteBuffer.put(Bytes.toArray(IntStream.range(0, 1000000).boxed().collect(Collectors.toList())));
        byteBuffer.force();
//        channel.transferTo();
        raf.close();
    }

    private static void readTest(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) throw new FileNotFoundException();
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        byte[] bytes = new byte[2];
        while (raf.read(bytes) != -1) {
        }
        System.out.println(bytes[0]);
        raf.read(bytes);
        raf.close();
        System.out.println(Integer.valueOf(bytes[0]));
    }
}
