package computationalgeometry;

/**
 * 将一个单调多边形三角剖分
 * 将一个多边形单调化
 *
 * @author fengcaiwen
 * @since 8/14/2019
 */
public class PolygonTriangle {
    public static void main(String[] args) {

    }

    /*
     * 输入: 一系列线段
     * 输出: 剖分出来的三角形
     */
    public static void test(String fullName) throws Exception {
        // todo: 如何将一个单调多边形分成单调的两部分
    }

    /*
     * find max y from s, if is a list, return max x of list
     */
    public int HTH(Point[] points, int n) {
        int rank = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[rank].y < points[i].y || (points[rank].y == points[i].y && points[i].x > points[rank].x))
                rank = i;
        }
        return rank;
    }

}
