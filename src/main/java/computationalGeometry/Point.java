package computationalGeometry;

/**
 * @author fengcaiwen
 * @since 7/17/2019
 */
public class Point {
    public boolean isEdge;

    public double x;
    public double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point X = new Point(0, 1);
    public static Point Y = new Point(1, 0);
}