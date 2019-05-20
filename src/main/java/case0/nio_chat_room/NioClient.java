package case0.nio_chat_room;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NioClient {

    public static void main(String[] args) throws Throwable {
        new NioClient().start();
    }

    public void start() throws Throwable {
        // 1, 创建Selector
        Selector selector = Selector.open();
        // 2,创建socketChannel,并创建连接
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8000));
        // 3,是否需要设置为异步？？
        channel.configureBlocking(false);
        // 4, 将channel注册到Selector
        channel.register(selector, SelectionKey.OP_READ);
        // 5，循环selector.select()方法
        // 6,调用selector的SelectorKeys方法，获取事件
        // 7,按照不同类型的时间，调用不同的业务逻辑
        // 8,goto step 3
        new Thread(new NioClientReadHandler(selector)).start();


        channel.write(ByteBuffer.wrap("i am client".getBytes()));
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.equals("q")) {
                break;
            }
            channel.write(Charset.forName("UTF-8").encode(str));
        }


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (channel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            System.out.println("response:" + Charset.forName("UTF-8").decode(byteBuffer));
        }

    }

}
