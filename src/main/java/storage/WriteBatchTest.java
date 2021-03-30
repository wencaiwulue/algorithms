package storage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.WriteBatch;
import org.rocksdb.WriteOptions;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class mimics the db/write_batch_test.cc
 * in the c++ rocksdb library.
 */
public class WriteBatchTest {
    static {
        RocksDB.loadLibrary();
    }

    @Rule
    public TemporaryFolder dbFolder = new TemporaryFolder();

    @Test
    public void testWriteBatch() throws RocksDBException {
        String s = "/Users/naison/test/rocksdbtest";
        try (final RocksDB db = RocksDB.open(s)) {
            db.put("2".getBytes(), "3".getBytes());
        }
    }

    @Test
    public void testWriteBatchRestore() throws RocksDBException {
        String s = "/Users/naison/test/rocksdbtest";
        try (final RocksDB db = RocksDB.open(s)) {
            byte[] bytes = db.get("2".getBytes());
            System.out.println(new String(bytes));
        }
    }

    @Test
    public void deleteRange() throws RocksDBException {
        try (final RocksDB db = RocksDB.open(dbFolder.getRoot().getAbsolutePath());
             final WriteBatch batch = new WriteBatch();
             final WriteOptions wOpt = new WriteOptions()) {
            db.put("key1".getBytes(), "value".getBytes());
            db.put("key2".getBytes(), "12345678".getBytes());
            db.put("key3".getBytes(), "abcdefg".getBytes());
            db.put("key4".getBytes(), "xyz".getBytes());
            assertThat(db.get("key1".getBytes())).isEqualTo("value".getBytes());
            assertThat(db.get("key2".getBytes())).isEqualTo("12345678".getBytes());
            assertThat(db.get("key3".getBytes())).isEqualTo("abcdefg".getBytes());
            assertThat(db.get("key4".getBytes())).isEqualTo("xyz".getBytes());

            batch.deleteRange("key2".getBytes(), "key4".getBytes());
            db.write(wOpt, batch);

            assertThat(db.get("key1".getBytes())).isEqualTo("value".getBytes());
            assertThat(db.get("key2".getBytes())).isNull();
            assertThat(db.get("key3".getBytes())).isNull();
            assertThat(db.get("key4".getBytes())).isEqualTo("xyz".getBytes());
        }
    }

    @Test
    public void restorePoints() throws RocksDBException {
        try (final WriteBatch batch = new WriteBatch()) {

            batch.put("k1".getBytes(), "v1".getBytes());
            batch.put("k2".getBytes(), "v2".getBytes());

            batch.setSavePoint();

            batch.put("k1".getBytes(), "123456789".getBytes());
            batch.delete("k2".getBytes());

            batch.rollbackToSavePoint();

        }
    }

    @Test(expected = RocksDBException.class)
    public void restorePoints_withoutSavePoints() throws RocksDBException {
        try (final WriteBatch batch = new WriteBatch()) {
            batch.rollbackToSavePoint();
        }
    }

    @Test(expected = RocksDBException.class)
    public void restorePoints_withoutSavePoints_nested() throws RocksDBException {
        try (final WriteBatch batch = new WriteBatch()) {

            batch.setSavePoint();
            batch.rollbackToSavePoint();

            // without previous corresponding setSavePoint
            batch.rollbackToSavePoint();
        }
    }

    @Test
    public void popSavePoint() throws RocksDBException {
        try (final WriteBatch batch = new WriteBatch()) {

            batch.put("k1".getBytes(), "v1".getBytes());
            batch.put("k2".getBytes(), "v2".getBytes());

            batch.setSavePoint();

            batch.put("k1".getBytes(), "123456789".getBytes());
            batch.delete("k2".getBytes());

            batch.setSavePoint();

            batch.popSavePoint();

            batch.rollbackToSavePoint();

        }
    }

    @Test(expected = RocksDBException.class)
    public void popSavePoint_withoutSavePoints() throws RocksDBException {
        try (final WriteBatch batch = new WriteBatch()) {
            batch.popSavePoint();
        }
    }

    @Test(expected = RocksDBException.class)
    public void popSavePoint_withoutSavePoints_nested() throws RocksDBException {
        try (final WriteBatch batch = new WriteBatch()) {

            batch.setSavePoint();
            batch.popSavePoint();

            // without previous corresponding setSavePoint
            batch.popSavePoint();
        }
    }


    @Test
    public void walTerminationPoint() throws RocksDBException {
        try (final WriteBatch batch = new WriteBatch()) {
            WriteBatch.SavePoint walTerminationPoint = batch.getWalTerminationPoint();
            assertThat(walTerminationPoint.isCleared()).isTrue();

            batch.put("k1".getBytes(UTF_8), "v1".getBytes(UTF_8));

            batch.markWalTerminationPoint();

            walTerminationPoint = batch.getWalTerminationPoint();
            assertThat(walTerminationPoint.getSize()).isEqualTo(19);
            assertThat(walTerminationPoint.getCount()).isEqualTo(1);
            assertThat(walTerminationPoint.getContentFlags()).isEqualTo(2);
        }
    }

    @Test
    public void getWriteBatch() {
        try (final WriteBatch batch = new WriteBatch()) {
            assertThat(batch.getWriteBatch()).isEqualTo(batch);
        }
    }

    static byte[] getContents(final WriteBatch wb) {
        return getContents(wb.getNativeHandle());
    }

    static String getFromWriteBatch(final WriteBatch wb, final String key)
            throws RocksDBException {
        final WriteBatchGetter getter =
                new WriteBatchGetter(key.getBytes(UTF_8));
        wb.iterate(getter);
        if (getter.getValue() != null) {
            return new String(getter.getValue(), UTF_8);
        } else {
            return null;
        }
    }

    private static native byte[] getContents(final long writeBatchHandle);
}

/**
 * Package-private class which provides java api to access
 * c++ WriteBatchInternal.
 */
class WriteBatchTestInternalHelper {
    static void setSequence(final WriteBatch wb, final long sn) {
        setSequence(wb.getNativeHandle(), sn);
    }

    static long sequence(final WriteBatch wb) {
        return sequence(wb.getNativeHandle());
    }

    static void append(final WriteBatch wb1, final WriteBatch wb2) {
        append(wb1.getNativeHandle(), wb2.getNativeHandle());
    }

    private static native void setSequence(final long writeBatchHandle,
                                           final long sn);

    private static native long sequence(final long writeBatchHandle);

    private static native void append(final long writeBatchHandle1,
                                      final long writeBatchHandle2);
}
