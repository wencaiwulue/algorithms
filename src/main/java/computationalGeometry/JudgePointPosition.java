package computationalGeometry;

/**
 * @author fengcaiwen
 * @since 7/15/2019
 */
public class JudgePointPosition {
    /**
     * ->
     * a：(x1, y1), point A:(x2, y2)
     */
    private boolean isLeft(int x1, int y1, int x2, int y2) {
        // a'=(-y1, x1) or (y1, -x1), return a' x A = (-y1, x1) x (x2, y2)
        return (-y1 * x2 + x1 * y2) > 0;
    }


    public boolean isLeft(Point p, Point q, Point s) {
        return isLeft(q.x - p.x, q.y - p.y, s.x, s.y);
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Point of(int x, int y) {
            return new Point(x, y);
        }
    }
}
