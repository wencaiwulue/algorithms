package case0.nio_chat_room;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws Throwable {
        new NioServer().start();
    }

    public void start() throws Throwable {
        // 第一步：创建Selector
        Selector selector = Selector.open();
        // 第二步：创建ServerSocketChannel，并监听端口
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress("localhost", 8000));
        // 第三步：设置channel为非阻塞模式
        channel.configureBlocking(false);
        // 第四步：将channel注册到Selector上，并监听连接事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
        for (; ; ) {
            // 第五步：调用Selector的select方法，
            int select = selector.select();
            if (select == 0) {
                return;
            }
            // 第六步：调用selectKeys方法，获取到事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                // 第七步：调用业务逻辑handler，处理业务
                SelectableChannel channel1 = selectionKey.channel();
                if (selectionKey.isAcceptable()) {
                    acceptHandler(selectionKey, selector);
                } else if (selectionKey.isConnectable()) {
                    acceptHandler((ServerSocketChannel) channel1, selector);
                } else if (selectionKey.isReadable()) {
                    readHandler((SocketChannel) channel1, selector);
                } else if (selectionKey.isWritable()) {
                    writeHandler((SocketChannel) channel1, selector, "sorry");
                }
            }
        }
    }

    public void acceptHandler(SelectionKey selectionKey, Selector selector) throws Exception {
        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void acceptHandler(ServerSocketChannel channel, Selector selector) throws Exception {
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void readHandler(SocketChannel channel, Selector selector) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuilder request = new StringBuilder();
        while (channel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            request.append(Charset.forName("UTF-8").decode(byteBuffer));
        }
        System.out.println("request:" + request);
        channel.register(selector, SelectionKey.OP_WRITE);
    }

    public void writeHandler(SocketChannel channel, Selector selector, String response) throws Exception {
        channel.write(Charset.forName("UTF-8").encode(response));
        channel.register(selector, SelectionKey.OP_READ);
    }

}

