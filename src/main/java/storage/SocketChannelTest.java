package storage;

import org.nustaq.serialization.FSTConfiguration;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author naison
 * @since 4/20/2020 11:14
 */
public class SocketChannelTest {
    private static final FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    public static void main(String[] args) throws Exception {

        Thread t = new Thread(() -> {
            try {
                Selector selector = Selector.open();
                ServerSocketChannel open = ServerSocketChannel.open();
                open.bind(new InetSocketAddress("localhost", 9000));
                open.configureBlocking(false);
                open.register(selector, SelectionKey.OP_ACCEPT);
                while (true) {
                    try {
                        if (selector.selectNow() == 0) continue;
                        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                            SelectionKey next = iterator.next();
                            iterator.remove();
                            if (next.isAcceptable()) {
                                SocketChannel channel = ((ServerSocketChannel) next.channel()).accept();
                                channel.configureBlocking(false);
                                channel.register(selector, SelectionKey.OP_READ);
                            } else if (next.isReadable()) {
                                SocketChannel channel = (SocketChannel) next.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(4);
                                int read = channel.read(byteBuffer);
                                if (read == 4) {
                                    byte[] anInt = byteBuffer.array();
                                    int i = byteArrayToInt(anInt);
                                    System.out.println(i);
                                    ByteBuffer allocate = ByteBuffer.allocate(i);
                                    if (channel.read(allocate) == i) {
                                        Object o = conf.asObject(allocate.array());
                                        if (o != null) {
                                            System.out.println(o);
                                        }
                                    }
                                }
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        t.start();

        Thread thread = new Thread(() -> {
            try {
                SocketChannel localhost = SocketChannel.open(new InetSocketAddress("localhost", 9000));

                while (true) {
                    U u = new U();
                    byte[] array = conf.asByteArray(u);

                    ByteBuffer byteBuffer = ByteBuffer.allocate(array.length + 4);
                    byteBuffer.putInt(array.length);
                    byteBuffer.put(array);

                    localhost.socket().getOutputStream().write(byteBuffer.array());
//                    localhost.getOutputStream().flush();
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();

    }

    static class U implements Serializable {
    }


    public static int byteArrayToInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF));
    }
}
