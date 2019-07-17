package computationalGeometry;

/**
 * @author fengcaiwen
 * @since 7/15/2019
 */
public class JudgePointPosition {


    /**
     * step:
     * 1, find the start point
     * 2, from s to each elements, find the next point t, make t's  right have no point
     */
    public void run(Point[] s, int n) {

    }

    /**
     * find min y from s, if is a list, return min x of list
     */
    public Point LTL(Point[] s, int n) {
        int rank = 0;
        for (int i = 0; i < s.length; i++)
            if (s[rank].y > s[i].y || (s[rank].y == s[i].y && s[rank].x > s[i].x))
                rank = i;

        return s[rank];
    }

    /**
     * ->
     * a：(x1, y1), point A:(x2, y2)
     */
    private boolean isLeft(int x1, int y1, int x2, int y2) {
        // a'=(-y1, x1) or (y1, -x1), return a' x A = (-y1, x1) x (x2, y2)
        return (-y1 * x2 + x1 * y2) > 0;
    }

    /**
     * ->
     * pq and point s
     */
    public boolean isLeft(Point p, Point q, Point s) {
        return isLeft(q.x - p.x, q.y - p.y, s.x, s.y);
    }

}
