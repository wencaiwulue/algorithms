package util;

import java.nio.ByteBuffer;

/**
 * @author naison
 * @since 5/5/2020 14:51
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        byteBuffer.putInt(1);
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
    }
}
