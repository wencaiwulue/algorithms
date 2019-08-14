package graph.diagraph;

import graph.Cycle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 6/16/2019 16:49
 */
public class DirectedCycle {

    private boolean[] marked;

    private boolean hasCycle;

    private boolean[] onStack;

    private int[] edgeTo;

    private Stack<Integer> cycleElement;

    private DirectedCycle(Diagraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                if (hasCycle) {
                    return;
                }
                dfs(g, i, i);
            }
        }

    }

    /**
     * is this right ??? TODO
     * refer:
     * {@link Cycle#dfs(graph.Graph, int, int)}
     */
    private void dfs(Diagraph g, int v, int u) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w, v);
            } else if (w != u) {
                hasCycle = true;
                {
                    cycleElement = new Stack<>();
                    for (int x = v; x != w; x = edgeTo[x]) {
                        cycleElement.add(x);
                    }
                    cycleElement.add(w);
                    cycleElement.add(v);
                }

            }
        }
        onStack[v] = false;
    }

    /**
     * has cycle or not
     */
    private boolean hasCycle() {
        return hasCycle;
    }

    /**
     * return cycle all vertex if cycle exist
     */
    Iterable<Integer> cycle() {
        return cycleElement;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("DiagraphTest.txt");
        assert resource != null;
        Diagraph g = new Diagraph(Path.of(resource.toURI()));
        DirectedCycle dc = new DirectedCycle(g);
        System.out.println(dc.hasCycle());
        System.out.println(dc.cycleElement);

    }
}
