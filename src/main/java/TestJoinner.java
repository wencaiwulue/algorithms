import com.google.common.base.Joiner;

import java.util.Arrays;

/**
 * @author fengcaiwen
 * @since 4/16/2019
 */
public class TestJoinner {
    public static void main(String[] args) {
        String subSql = Joiner.on("','").appendTo(new StringBuilder("('"), Arrays.asList(1, 2, 3)).append("')").toString();
        System.out.println(subSql);
    }
}
