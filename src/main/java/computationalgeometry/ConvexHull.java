package computationalgeometry;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author fengcaiwen
 * @since 8/12/2019
 */
public class ConvexHull {

    /*
     * 描述: 给定n个二维平面上的点，求他们的凸包。
     * 输入: 第一行包含一个正整数n。接下来n行，每行包含两个整数x,y，表示一个点的坐标。
     * 输出: 令所有在凸包极边上的点依次为p1,p2,...,pm（序号），其中m表示点的个数，请输出以下整数：(p1 × p2 × ... × pm × m) mod (n + 1)
     */
    public static void test() throws URISyntaxException, IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("convex/convex.txt");
        Path of = Path.of(Objects.requireNonNull(resource).toURI());

        Point[] points = Files.lines(of).skip(1).map(e -> {
            String[] s = e.split(" ");
            return new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }).toArray(Point[]::new);
        List<Point> hull = PointPosition.grahamScan(points, points.length);

        for (Point point : hull) {
            System.out.println(String.format("(%s, %s)", point.x, point.y));
        }

    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        test();
    }

}
