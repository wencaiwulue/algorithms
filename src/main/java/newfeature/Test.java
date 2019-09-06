package newfeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fengcaiwen
 * @since 4/9/2019
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("gender", "1");
        map.put("login_user_id", "12861553");
        map.put("session_id", "15535053828e85530dbac2ce691c1641");
        map.put("user_interest", "14");
        String parameters = Joiner.on("&").appendTo(new StringBuilder("?"), map.entrySet()).toString();

        System.out.println(Joiner.on(",").join(map.entrySet()));
        System.out.println(parameters);

        System.out.println(map.entrySet());
        System.out.println(map.toString());
        String url = "http://morgoth-aman.ixiaolu.com/audio_featured/14907805/20180723200914/5b55c56af1e4a_fix.mp3";
        Pattern p = Pattern.compile("://(.*?)\\.");
        Matcher matcher = p.matcher(url);
        boolean b = matcher.find();
        System.out.println(matcher.group(1));
        Pattern pp = Pattern.compile("com/(.*?)/");
        Matcher matcher1 = pp.matcher(url);
        matcher1.find();
        System.out.println(matcher1.group(1));
        Pattern ppp = Pattern.compile("http(.*?)com");
        Matcher mmm = ppp.matcher(url);
        mmm.find();
        System.out.println(mmm.group(0) + "666");


        String filePath = "D:\\data\\audio_featured_fix.log";
        File file = new File(filePath);
        if (!file.exists()) file.createNewFile();
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(raf.length());
        String content = String.format("[%s][CONTENT:%s]\n", "2019", "content11");
        raf.write(content.getBytes());
        raf.close();

        String s = Base64.getEncoder().encodeToString("qbucket:qkey ".getBytes());
        System.out.println(s);

        List<String> strings = Arrays.asList("a", "b", "c");
        System.out.println(strings);
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings1 = new String[]{"a", "b", "c"};
        System.out.println(strings1.toString());
    }
}
