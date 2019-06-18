package graph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

/**
 * @author fengcaiwen
 * @since 6/15/2019 17:50
 */
public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    private Cycle(Graph g) {
        marked = new boolean[g.V()];
        hasCycle = false;

        for (int v = 0; v < g.V(); v++) {
            if (marked[v]) continue;
            dfs(g, v, v);
        }
    }

    /**
     * @param u is a pointer, always point to the last node which from u to v
     * @param v though the u arrived node
     */
    public void dfs(Graph g, int v, int u) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w, v);
            } else if (w != u) {
                hasCycle = true;
            }
        }
    }

    private boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("tinyG.txt");
        Graph f = new Graph(Path.of(Objects.requireNonNull(resource).toURI()));
        Cycle c = new Cycle(f);
        System.out.println(c.hasCycle());
    }
}
