import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.charset.Charset;

public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("abc".getBytes(Charset.forName("UTF8")));
        System.out.println(buffer.position());
        SelectionKey key;

        ByteBuffer byteBuffer = ByteBuffer.wrap("abc".getBytes());
        System.out.println(byteBuffer);
        System.out.println(Charset.forName("UTF-8").encode("abc"));

    }
}
