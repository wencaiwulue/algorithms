package util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author fengcaiwen
 * @since 9/18/2019
 */
public class EncodeChangeTools {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "D:\\dsa\\dsa\\src\\avl";
        change(Path.of(path));
    }

    private static void change(Path path) throws IOException, ClassNotFoundException {
        if (!Files.isDirectory(path)) {
            String fileName = path.getFileName().toString();
            System.out.println(fileName);
            if (!fileName.endsWith(".h") && !fileName.endsWith(".cpp") && !fileName.endsWith(".java")) return;

            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("java.nio.charset.StandardCharsets");
            Field[] fields = aClass.getFields();

            List<String> strings = new ArrayList<>();
//            for (Field field : fields) {
//                try {
//                    field.setAccessible(true);
//                    Charset charset = (Charset) field.get(field.getName());
//                    strings = Files.readAllLines(path, charset);
//                    break;
//                } catch (Throwable ignored) {
//                }
//            }
            strings = Files.readAllLines(path, Charset.forName("GBK"));


            Files.delete(path);
            Files.createFile(path);
            Files.write(path, strings, StandardCharsets.UTF_8);
        } else {
            Stream<Path> list = Files.list(path);
            list.forEachOrdered(e -> {
                try {
                    change(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
}
