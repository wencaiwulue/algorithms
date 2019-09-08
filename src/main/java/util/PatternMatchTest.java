package util;

//import nl.flotsam.xeger.Xeger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fengcaiwen
 * @since 8/30/2019
 */
public class PatternMatchTest {
    public static void main(String[] args) {
        String phone = "13512775062";
        String qq = "1231233584@qq.com";
        String card = "6215421131521321352";
        System.out.println(blockKeywords(""));
        ;

//        String replace = s1.replace();
        Pattern compile = Pattern.compile("(.*?)(\\d{4}?@)");
        Matcher qqMatcher = compile.matcher(qq);
        if (qqMatcher.find()) {
            System.out.println(qq);
            System.out.println(qq.replace(qqMatcher.group(1), "*".repeat(Math.max(0, qqMatcher.group(1).length()))));
        }

        compile = Pattern.compile("^\\d{3}(.*?)\\d{4}$");
        Matcher phoneMatcher = compile.matcher(phone);
        if (phoneMatcher.find()) {
            System.out.println(phone);
            System.out.println(phone.replaceAll(phoneMatcher.group(1), "*".repeat(Math.max(0, phoneMatcher.group(1).length()))));
        }

        compile = Pattern.compile("^(.*?)\\d{4}$");
        Matcher cardMatcher = compile.matcher(card);
        if (cardMatcher.find()) {
            System.out.println(card);
            System.out.println(card.replaceAll(cardMatcher.group(1), "*".repeat(Math.max(0, cardMatcher.group(1).length()))));
        }
//
//
//        String reger = "d{4}";
//        Xeger xeger = new Xeger(reger);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(xeger.generate());
//        }


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
}
