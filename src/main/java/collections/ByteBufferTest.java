package collections;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.charset.StandardCharsets;

public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("abc".getBytes(StandardCharsets.UTF_8));
        System.out.println(buffer.position());

        ByteBuffer byteBuffer = ByteBuffer.wrap("abc".getBytes());
        System.out.println(byteBuffer);
        System.out.println(StandardCharsets.UTF_8.encode("abc"));

    }
}
