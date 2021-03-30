package graph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 6/15/2019 15:09
 */
public class DepthFirstPaths {
    /**
     * visited it or not
     */
    private boolean[] marked;
    /**
     * index i, i mean vertex i, isLeaf means the last vertex comes to i
     */
    private int[] edgeTo;
    /**
     * start vertex
     */
    private final int s;

    /**
     * find all path which start point is s
     */
    private DepthFirstPaths(Graph g, int s) {
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, this.s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
//        for (Integer w : g.adj(v)) {
//            if (marked[w]) continue;
//            edgeTo[w] = v;
//            bfs(g, w);
//        }
        while (!g.adj(v).isEmpty()) {
            int w = g.adj(v).pop();
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }


    /**
     * exist a path from s to v or not
     */
    private boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * return path,s --> v, if not exist , return null
     */
    private Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        while (v != this.s) {
            path.push(v);
            v = edgeTo[v];
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("tinyCG.txt");
        assert resource != null;
        Graph g = new Graph(Path.of(resource.toURI()));
        DepthFirstPaths paths = new DepthFirstPaths(g, 0);
        Iterable<Integer> integers = paths.pathTo(5);
        assert integers != null;
        integers.forEach(System.out::println);
    }
}
