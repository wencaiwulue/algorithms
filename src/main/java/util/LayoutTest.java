package util;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author naison
 * @since 5/4/2020 13:59
 */
public class LayoutTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
    }
}
