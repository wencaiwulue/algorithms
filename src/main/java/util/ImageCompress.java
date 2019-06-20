package util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;
import java.net.URL;

/**
 * @author fengcaiwen
 * @since 6/20/2019
 */
public class ImageCompress {
    public static void main(String[] args) throws IOException {
        String path = "https://morgoth-aman.ixiaolu.com/etc/9028573";
        URL url = new URL(path);
        Thumbnails.of(url)
                .scale(1f)
                .outputQuality(0.5f)
                .toFile("D:\\a.jpg");
    }
}
