package graph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author fengcaiwen
 * @since 6/15/2019 15:09
 */
//@SuppressWarnings("all")
public class BreadthFirstPaths {
    /**
     * visited it or not
     */
    private boolean[] marked;
    /**
     * index i, i mean vertex i, value means the last vertex comes to i
     */
    private int[] edgeTo;
    /**
     * start vertex
     */
    private final int s;

    /**
     * find all path which start point is s
     */
    private BreadthFirstPaths(Graph g, int s) {
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        this.s = s;
        bfs(g, this.s);
    }

    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int poll = queue.poll();

            while (!g.adj(poll).isEmpty()) {
                int w = g.adj(poll).pop();
                if (!marked[w]) {
                    edgeTo[w] = poll;
                    queue.add(w);
                    marked[w] = true;
                }
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

    public static void main(String[] args) throws IOException {
        String s = "C:\\Users\\Fcw\\Documents\\javaee\\src\\main\\java\\graph\\tinyCG.txt";
        Graph g = new Graph(Path.of(s));
        BreadthFirstPaths paths = new BreadthFirstPaths(g, 0);
        Objects.requireNonNull(paths.pathTo(1)).forEach(e -> System.out.print(e + " "));
        System.out.println();
        Objects.requireNonNull(paths.pathTo(2)).forEach(e -> System.out.print(e + " "));
        System.out.println();
        Objects.requireNonNull(paths.pathTo(3)).forEach(e -> System.out.print(e + " "));
    }
}
