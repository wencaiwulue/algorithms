package computationalgeometry;

import java.util.Objects;

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

    public static Point of(double x, double y){
        return new Point(x, y);
    }

    public static Point Y = new Point(0, 1);
    public static Point X = new Point(1, 0);

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return isEdge == point.isEdge &&
                Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEdge, x, y);
    }
}
