package computationalgeometry;

import java.util.Objects;

/**
 * @author fengcaiwen
 * @since 8/14/2019
 */
public class Line {
    public Point p;
    public Point q;

    public Line() {
    }

    public Line(Point p, Point q) {
        this.p = p;
        this.q = q;
    }

    public boolean isFrom(Point p) {
        return this.p.equals(p);
    }

    public boolean isTo(Point q) {
        return this.p.equals(q);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(p, line.p) &&
                Objects.equals(q, line.q);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p, q);
    }
}
