package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fengcaiwen
 * @since 8/30/2019
 */
public class PatternMatchTest {
    public static void main(String[] args) {
        String phone = "13512775062";
        String qq = "姓名：宝先生\\n年龄：24\\n婚龄：无\\n咨询问题：脱单（见到女孩第一句话怎么说才能把关系拉上  ）\\n电话号：18325460\"";
//        System.out.println(blockKeywords(""));
        System.out.println(qq);
        System.out.println(blockKey(qq));

//        String reger = "d{4}";
//        Xeger xeger = new Xeger(reger);
//        for (int i = 0; i < 10; i++) System.out.println(xeger.generate());


    }

    public static String blockKeywords(String source) {
        if (source.contains("@")) {// mail
            Pattern p = Pattern.compile("(.*?)(\\d{4}?@)");
            Matcher m = p.matcher(source);
            if (m.find())
                return source.replace(m.group(1), "*".repeat(Math.max(0, m.group(1).length())));
        } else if (source.length() == 11) { // phone
            Pattern p = Pattern.compile("^\\d{3}(.*?)\\d{4}$");
            Matcher m = p.matcher(source);
            if (m.find())
                return source.replace(m.group(1), "*".repeat(Math.max(0, m.group(1).length())));
        } else {// card
            Pattern p = Pattern.compile("^(.*?)\\d{4}$");
            Matcher m = p.matcher(source);
            if (m.find())
                return source.replace(m.group(1), "*".repeat(Math.max(0, m.group(1).length())));
        }
        return source;
    }

    public static String blockKey(String source) {
        Pattern p = Pattern.compile("(.*?)(\\d{4}?@)");
        Matcher m = p.matcher(source);
        while (m.find())
            source = source.replace(m.group(1), "*".repeat(Math.max(0, m.group(1).length())));

        Pattern p1 = Pattern.compile("\\d{3}(\\d{4})\\d{4}");
        Matcher m1 = p1.matcher(source);
        while (m1.find())
            source = source.replace(m1.group(1), "*".repeat(Math.max(0, m1.group(1).length())));
        return source;
    }
}
