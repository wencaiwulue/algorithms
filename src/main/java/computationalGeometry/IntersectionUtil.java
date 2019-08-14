package computationalGeometry;

import hash.HashTest;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fengcaiwen
 * @since 8/6/2019
 */
public class IntersectionUtil {

    // 两点式直线公式：(x - x1) / (x2 - x1) = (y - y1) / (y2 - y1)
    // --> x(y2 - y1) + y(x1 - x2) + x1(y1 - y2) + y1(x2 - x1) = 0
    // A = y2-y1, B = x1-x2, C = x1(y1 - y2) + y1(x2 - x1)
    // (x,y) = d2/(d1+d2) * (x3,y3) + d1/(d1+d2) * (x4,y4)
    // d2/(d1+d2) = |A*x4 + B*y4 + C|/(|A*x4 + B*y4 + C|+|A*x3 + B*y3 + C|)
    // d1/(d1+d2) = |A*x3 + B*y3 + C|/(|A*x4 + B*y4 + C|+|A*x3 + B*y3 + C|)
    public static Point detectIntersection(Point a, Point b, Point c, Point d) {

        // whether intersect ?
        boolean intersect = (PointPosition.isLeft(a, b, c) ^ PointPosition.isLeft(a, b, d)) && (PointPosition.isLeft(c, d, a) ^ PointPosition.isLeft(c, d, b));
        if (!intersect) return null;

        double d2 = Math.abs((b.y - a.y) * d.x + (a.x - b.x) * d.y + a.x * (a.y - b.y) + a.y * (b.x - a.x));
        double d1 = Math.abs((b.y - a.y) * c.x + (a.x - b.x) * c.y + a.x * (a.y - b.y) + a.y * (b.x - a.x));
        double x = d2 / (d1 + d2) * c.x + d1 / (d1 + d2) * d.x;
        double y = d2 / (d1 + d2) * c.y + d1 / (d1 + d2) * d.y;
        return new Point(x, y);
    }

    /*
    输入: 一系列线段
    输出: 相交的点坐标
     */
    public static void test(String fullName) throws Exception {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fullName);
        assert resource != null;
        Path of = Path.of(resource.toURI());
        Stream<String> lines = Files.lines(of);
        List<Line> collect = lines.skip(1).map(e -> {
            String[] s = e.split(" ");
            return new Line(new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1])), new Point(Integer.parseInt(s[2]), Integer.parseInt(s[3])));
        }).collect(Collectors.toList());
        Collection<Point> main = sweepLines(collect);
        for (Point point : main) {
            System.out.println(point);
        }

    }

    /**
     * attention:
     * 这里不考虑退化情况，比如一条线段垂直, 或者两个线段一部分重叠
     * <p>
     * sweep liens
     * event: from left --> right
     * status: top --> bottom
     * 1, get all lines points value and sort it by x asc --represent sweep line, means this point, we need to stop and check
     * 2, sweep line position:
     * (1), p, check p with event stack top(1), intersection detect, if intersect, push one event to event stack
     * (2), q,
     *
     * @param lines lines
     * @return points
     */
    public static Collection<Point> sweepLines(List<Line> lines) {
        Set<Point> result = new HashSet<>();

        // all events
        Stack<Event> eventStack = lines.stream().flatMap(e -> Stream.of(new Event(e.p, e), new Event(e.q, e))).sorted(Comparator.comparingDouble(o -> -o.point.x)).collect(Collectors.toCollection(Stack::new));
        // Stack<Line> status = new Stack<>();
        Stack<Line> statusStack = new Stack<>();

        // sweep line moving
        while (!eventStack.isEmpty()) {
            Event pop = eventStack.pop();
            if (pop.isFrom()) {
                if (!statusStack.isEmpty()) {
                    Line line = statusStack.get(statusStack.size() - 1);
                    // detect intersection
                    Point point = detectIntersection(line.p, line.q, pop.line.p, pop.line.q);
                    if (point != null) {
                        Event eventIntersection = new Event(true, point, line, pop.line);
                        if (!eventStack.contains(eventIntersection)) {
                            eventStack.add(eventIntersection);
                            // todo: optimize
                            eventStack.sort(Comparator.comparingDouble(o -> -o.point.x));
                            result.add(point);
                        }
                    }
                }
                statusStack.add(pop.line);
            } else if (pop.isTo()) {
                // remove whole line
                statusStack.removeIf(e -> e.q.equals(pop.point));

            } else if (pop.isIntersect()) {
                // exchange the rank of line and line2 in statusStack
                Line line = pop.line;
                Line line2 = pop.line2;
                int i = statusStack.indexOf(line);
                int j = statusStack.indexOf(line2);
                statusStack.set(i, line2);
                statusStack.set(j, line);
                Line lastElementOfStatusStack = statusStack.get(statusStack.size() - 1);
                Point point = detectIntersection(lastElementOfStatusStack.p, lastElementOfStatusStack.q, pop.line.p, pop.line.q);
                if (point != null) {
                    Event eventIntersection = new Event(true, point, lastElementOfStatusStack, pop.line);
                    if (!eventStack.contains(eventIntersection)) {
                        eventStack.add(eventIntersection);
                        // todo: optimize
                        eventStack.sort(Comparator.comparingDouble(o -> -o.point.x));
                        result.add(point);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        Point point = detectIntersection(Point.of(2, 2), Point.of(4, 4), Point.of(4, 2), Point.of(2, 4));
        assert point != null;
        System.out.println(point.toString());

        String fullName = "segmentIntersection.txt";
        test(fullName);
    }

    public static class Event {
        public boolean isIntersection;
        public Point point;
        public Line line;
        // not null only when isIntersection is true
        public Line line2;

        public Event() {
        }

        public Event(boolean isIntersection, Point point, Line line, Line line2) {
            this.isIntersection = isIntersection;
            this.point = point;
            this.line = line;
            this.line2 = line2;
        }

        public Event(Point point, Line line) {
            this(false, point, line, null);
        }

        public boolean isFrom() {
            return this.line.isFrom(this.point);
        }

        public boolean isTo() {
            return this.line.isTo(this.point);
        }

        public boolean isIntersect() {
            return isIntersection;
        }
    }
}
