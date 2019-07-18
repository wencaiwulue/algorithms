package computationalGeometry;

/**
 * @author fengcaiwen
 * @since 7/17/2019
 */
public class Point {
    public boolean isEdge;
    public int from;
    public int to;

    public int x;
    public int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point X = new Point(0, 1);
    public static Point Y = new Point(1, 0);
}
