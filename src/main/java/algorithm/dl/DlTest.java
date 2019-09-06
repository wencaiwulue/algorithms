package algorithm.dl;

import graph.Edge;
import graph.WeightDirectGraph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fengcaiwen
 * @since 8/29/2019
 */
public class DlTest {

    // find the low cost path
    public static void test(WeightDirectGraph g, int from, int to) {
        Map<String, Integer> path = new HashMap<>();
        ArrayDeque<Integer> t = new ArrayDeque<>();
        Stack<Edge> froms = g.edges(to);
        t.addAll(froms.stream().map(Edge::from).collect(Collectors.toList()));

        while (t.size() != 0) {
            for (Edge edge : froms) {
                path.put(edge.from() + "_" + to, edge.weight());
            }
        }


    }

    public static void main(String[] args) {
        List<String> lis = null;
        lis.forEach(e -> System.out.println(e));
    }

}
