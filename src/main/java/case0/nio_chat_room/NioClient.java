package case0.nio_chat_room;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NioClient {
    public static void main(String[] args) throws Throwable {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8000));
//        channel.connect();
//        channel.write(ByteBuffer.wrap("i am client".getBytes()));
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String str = scanner.nextLine();
//            if (str.equals("q")) {
//                break;
//            }
        channel.write(Charset.forName("UTF-8").encode("abc"));
//        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (channel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            System.out.println("response:" + Charset.forName("UTF-8").decode(byteBuffer));
        }

    }
}
