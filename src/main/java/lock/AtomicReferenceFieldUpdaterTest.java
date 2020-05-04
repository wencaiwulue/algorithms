package lock;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.UnaryOperator;

/**
 * @author naison
 * @since 4/25/2020 22:24
 */
public class AtomicReferenceFieldUpdaterTest {
    private volatile String str;
    private static final AtomicReferenceFieldUpdater<AtomicReferenceFieldUpdaterTest, String> atomicReferenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterTest.class, String.class, "str");

    public static void main(String[] args) {
        AtomicReferenceFieldUpdaterTest t = new AtomicReferenceFieldUpdaterTest();
        atomicReferenceFieldUpdater.getAndUpdate(t, o -> "string");
        AtomicReferenceFieldUpdaterTest t1 = new AtomicReferenceFieldUpdaterTest();
        System.out.println(atomicReferenceFieldUpdater.get(t1));
        System.out.println(atomicReferenceFieldUpdater.get(t));
    }
}
