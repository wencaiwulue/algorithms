package util;


import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author fengcaiwen
 * @since 10/9/2019
 */
public class PdfTools {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\89570\\Desktop\\ppt.pdf";
        File file = new File(path);
        FileInputStream i = new FileInputStream(file);
        int size = 30;
        pdf2Images(i, size);
    }

    private static ArrayList<byte[]> pdf2Images(InputStream inputStream, int size) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp_pdf", ".pdf");
            String absolutePath = tempFile.getAbsolutePath();

            RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
            byte[] bytes = new byte[2048];
            while (inputStream.read(bytes) != -1) {
                raf.write(bytes);
            }
            raf.close();

            int threadNum = 5;
            ExecutorService pool = Executors.newFixedThreadPool(threadNum);
            PDDocument pdDocument = PDDocument.load(tempFile);
            int pages = pdDocument.getNumberOfPages();
            CountDownLatch latch = new CountDownLatch(threadNum);
            int step = BigDecimal.valueOf(Math.min(pages, size)).divide(new BigDecimal(threadNum), RoundingMode.UP).intValue();
            // init with null
            ArrayList<ArrayList<byte[]>> result = new ArrayList<>(threadNum);
            for (int i = 0; i < size; i++) {
                result.add(null);
            }
            int i1 = Runtime.getRuntime().availableProcessors();
            // 这里需要注意的一点是，要形成[1~2，3~4, 5-6],左闭右闭的区间
            for (int i = 0; i < threadNum; i++) {
                int from = Math.max(i * step, 1);
                int to = Math.min(i != threadNum - 1 ? (i + 1) * step - 1 : (i + 1) * step, pages);
                if (from > to) {
                    latch.countDown();
                    continue;
                }
                // 2, 将一组用一个线程跑
                int finalI = i;
                pool.execute(() -> {
                    File tempDir = null;
                    try {
                        Path tempDirectory = Files.createTempDirectory(String.valueOf(finalI));
                        // 命令使用参见：https://www.ghostscript.com/doc/current/Use.htm#PDF
                        //C:\Program Files\gs\gs9.50\bin\gswin64c.exe
                        String temp = "gs -dQUIET -dNumRenderingThreads=THREADNUM -dNOSAFER -r144 -dBATCH -sDEVICE=pngalpha -dFirstPage=FROM -dLastPage=TO -dNOPAUSE -dNOPROMPT -sOutputFile=OUTPUT/%d.png -f INPUT";
                        String cmd = temp.replace("FROM", String.valueOf(from)).replace("TO", String.valueOf(to)).replace("OUTPUT", tempDirectory.toString()).replace("INPUT", absolutePath).replace("THREADNUM", String.valueOf(i1));
                        Process exec = Runtime.getRuntime().exec(cmd);
                        int status = exec.waitFor();
                        if (status != 0) {
                            System.err.println(cmd);
                        }

                        tempDir = new File(tempDirectory.toUri());
                        File[] files = tempDir.listFiles();
                        if (files != null) {
                            ArrayList<byte[]> arrayList = new ArrayList<>(files.length);
                            for (File file1 : files) {
                                FileChannel channel = new RandomAccessFile(file1, "rw").getChannel();
                                ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
                                while (channel.read(buffer) > 0) {
                                }
                                channel.close();
                                arrayList.add(buffer.array());
                            }
                            result.set(finalI, arrayList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (tempDir != null) {
                            removeFile(tempDir);
                        }
                        latch.countDown();
                    }
                });
            }
            latch.await(5, TimeUnit.MINUTES);
            return result.stream().filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        } finally {
            if (tempFile != null) {
                tempFile.deleteOnExit();
            }
        }
    }

    @SuppressWarnings("all")
    private static void removeFile(File file) {
        if (file == null) return;

        if (!file.isDirectory()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null || file.length() == 0) {
                file.delete();
                return;
            }
            for (File file1 : files) {
                removeFile(file1);
            }
        }

    }
}
