package niochatroom;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author fengcaiwen
 * @since 5/20/2019
 */
@SuppressWarnings("all")
public class NioClientReadHandler implements Runnable {

    private Selector selector;

    public NioClientReadHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                int select = selector.select();
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if (next.isReadable()) {
                        readHandler((SocketChannel) next.channel(), selector);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readHandler(SocketChannel channel, Selector selector) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuilder str = new StringBuilder();
        while (channel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            str.append(Charset.forName("UTF-8").decode(byteBuffer));
        }
        System.out.println("read from server: " + str);
        channel.register(selector, SelectionKey.OP_WRITE);
    }
}
