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
public class LockSupportTest {
    public static void main(String[] args) {
//        Runnable r = () -> {
//            long start = System.nanoTime();
//            LockSupport.parkUntil(2000);
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
//        };
//        new Thread(r).start();
//        Thread.sleep(3000);

//        System.out.println(TimeUnit.NANOSECONDS.toMillis(1000000));
        System.out.println(Math.ceil(1.00));
    }
}
