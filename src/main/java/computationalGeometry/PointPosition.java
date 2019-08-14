package computationalGeometry;

import java.util.*;

/**
 * @author fengcaiwen
 * @since 7/15/2019
 */
public class PointPosition {

    /**
     * step:
     * 1, find the start point
     * 2, from s to each elements, find the next point t, make t's  right have no point
     */
    public static List<Point> run(Point[] s, int n) {
        List<Point> list = new ArrayList<>();
        int start = LTL(s, n);
        for (int i = 0; i < s.length; i++) {
            int counter = 0;
            for (int j = 0; j < s.length; j++)
                if (start != i && start != j && i != j)
                    if (isLeft(s[start], s[i], s[j])) counter++;

            if (counter >= s.length - 1 - 1 - 1 - 1) {
                s[i].isEdge = true;
                list.add(s[i]);
            }
        }

        return list;

    }

    /*
     *  1, find the lowest amd the leftest point as start
     *  2, sort array s by degree asc
     *  3, from start and start[0], check start[1]
     * */
    public static Stack<Point> grahamScan(Point[] s, int n) {
        int start = LTL(s, n);
        Point sp = s[start];
        Point[] sorted = sort(s, n, sp);

        Stack<Point> visited = new Stack<>();
        Stack<Point> unvisited = new Stack<>();

        visited.push(sp);
        visited.push(sorted[0]);

        // attention: sort by degree base on start point
        // cut head and tail, the smallest and the biggest
        for (int i = 1; i <= s.length - 1; i++) {
            unvisited.push(sorted[i]);
        }

        while (!unvisited.empty()) {
            Point p1 = visited.get(visited.size() - 2);
            Point p2 = visited.get(visited.size() - 1);
            System.out.println(String.format("(%s, %s)->(%s, %s)", p1.x, p1.y, p2.x, p2.y));

            if (isLeft(visited.get(visited.size() - 2), visited.get(visited.size() - 1), unvisited.get(0))) {
                visited.push(unvisited.remove(0));
            } else {
                visited.pop();
            }
        }
        return visited;
    }

    /**
     * cosφ=a*b/|a||b|= (x1x2+y1y2)/(√(x1^2+y1^2)√(x2^2+y2^2))
     */
    private static Point[] sort(Point[] s, int n, Point base) {
        // cos = a*
        return Arrays.stream(s).sorted((o1, o2) -> {
            double v1 = calculateDegree(new Point(o1.x - base.x, o1.y - base.y), Point.X);
            double v2 = calculateDegree(new Point(o2.x - base.x, o2.y - base.y), Point.X);
            return Double.compare(v1, v2);
        }).toArray(Point[]::new);
    }

    /**
     * ->  ->
     * a   b
     * cosφ=a*b/|a||b|=（x1y1+x2y2）/(根号（x1^2+y1^2）根号（x2^2+y1^2）)
     * 注意: 这里是点乘
     */
    private static double calculateDegree(Point a, Point b) {
        double v = (a.x * b.x + a.y * b.y) / (Math.pow(Math.pow(a.x, 2) + Math.pow(a.y, 2), 0.5) * Math.pow(Math.pow(b.x, 2) + Math.pow(b.y, 2), 0.5));
        return Math.acos(v);
    }

    public static void main(String[] args) {
        double v = calculateDegree(new Point(1, 1), new Point(1, 0));
        System.out.println(v / (2 * 3.1415926) * 360);
        System.out.println(v);
    }

    /**
     * find min y from s, if is a list, return min x of list
     */
    private static int LTL(Point[] s, int n) {
        int rank = 0;
        for (int i = 0; i < s.length; i++)
            if (s[rank].y > s[i].y || (s[rank].y == s[i].y && s[rank].x > s[i].x))
                rank = i;

        return rank;
    }

    /**
     * ->
     * a：(x1, y1), point A:(x2, y2)
     */
    private static boolean isLeft(double x1, double y1, double x2, double y2) {
        // a'=(-y1, x1) or (y1, -x1), return a' * A = (-y1, x1) * (x2, y2)
        // todo: 点乘是对应位相乘的数量和，叉乘是相交乘的数量差
        // todo: 二维向量叉乘公式a（x1，y1），b（x2，y2），则 a × b=（x1y2 - x2y1）
        // todo: 二维向量点乘公式a（x1，y1），b（x2，y2），则 a * b=（x1x2 + y1y2）
        // 为什么要找a'向量呢？其实这个向量和(x1, y1)的点乘为0，也就是cosφ = 0, 所以φ = 90°,也就是说, a'向量是a向量的一个垂直向量。
        return (-y1 * x2 + x1 * y2) > 0;
    }

    private static double area2(Point p, Point q, Point s) {
        return p.x * q.y - p.y * q.x + q.x * s.y - q.y * s.x + s.x * p.y - s.y * p.x;
    }

    /**
     * ->
     * pq and point s
     */
    public static boolean isLeft(Point p, Point q, Point s) {
        return isLeft(q.x - p.x, q.y - p.y, s.x - p.x, s.y - p.y);
    }

}
