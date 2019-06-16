package graph.diagraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 6/16/2019 16:35
 */
public class DirectedDFS {

    private boolean[] marked;

    /**
     * find all vertex from s can arrived in diagraph
     */
    public DirectedDFS(Diagraph g, int s) {
        marked = new boolean[g.V()];
        List<Integer> list = new LinkedList<>();
        dfs(g, s, list);
        System.out.println(Arrays.toString(list.toArray()));
    }

    private void dfs(Diagraph g, int v, List<Integer> result) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                result.add(w);
                dfs(g, w, result);
            }
        }
    }

    /**
     * find all vertex from source can arrived in diagraph
     */
    public DirectedDFS(Diagraph g, Iterable<Integer> source) {
        marked = new boolean[g.V()];
        List<Integer> list = new LinkedList<>();
        for (Integer v : source) {
            if (!marked[v]) {
                dfs(g, v, list);
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * v can be visited or not
     */
    boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {

    }
}
