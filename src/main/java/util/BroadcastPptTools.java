package util;


import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @author fengcaiwen
 * @since 10/9/2019
 */
public class BroadcastPptTools {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\89570\\Desktop\\ppt.pdf";
        File file = new File(path);
        FileInputStream i = new FileInputStream(file);
        pdf2Images(i, 30);
    }


    private static ArrayList<byte[]> pdf2Images(InputStream inputStream, int size) {
        try {
            PDDocument pdDocument = PDDocument.load(inputStream, MemoryUsageSetting.setupMainMemoryOnly());
            PDFRenderer renderer = new PDFRenderer(pdDocument);

            int threadNum = 1;
            CountDownLatch latch = new CountDownLatch(threadNum);
            ExecutorService pool = Executors.newSingleThreadExecutor();
            int pages = pdDocument.getNumberOfPages();
            int step = BigDecimal.valueOf(Math.min(pages, size)).divide(BigDecimal.valueOf(threadNum), RoundingMode.UP).intValue();
            ArrayList<byte[]> result = new ArrayList<>(pages);
            for (int i = 0; i < pages; i++) {
                result.add(null);
            }

            long start = System.nanoTime();
            for (int i = 0; i < threadNum; i++) {
                int from = i * step;
                int to = Math.min((i + 1) * step, pages);
                if (from >= to || from > size) {
                    latch.countDown();
                    continue;
                }
                // 2, 将一组用一个线程跑
                pool.execute(() -> {
                    for (int j = from; j < to; j++) {
                        try {
                            /* dpi越大转换后越清晰，相对转换速度越慢 */
                            BufferedImage image = renderer.renderImageWithDPI(j, 144);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(image, "png", baos);
                            result.set(j, baos.toByteArray());
                            baos.close();
                        } catch (Exception ignored) {
                        }
                    }
                    latch.countDown();
                });
            }
            latch.await(5, TimeUnit.MINUTES);
            System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start) + "ms");

            return result;
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            return new ArrayList<>(0);
        }
    }
}
