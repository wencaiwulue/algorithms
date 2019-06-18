package graph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author fengcaiwen
 * @since 2019年6月15日
 */
//@SuppressWarnings("all")
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    private DepthFirstSearch(Graph g) {
        this.marked = new boolean[g.V()];
    }

    /**
     * find all adj which connected to s
     */
    private Iterable<Integer> search(Graph g, int s) {
        //1, find all adj from s, and foreach
        //2, bfs and push into stack or enqueue
        List<Integer> result = new ArrayList<>();
        dfs(g, s, result);
        return result;
    }

    private void dfs(Graph g, int s, List<Integer> reuslt) {
        marked[s] = true;
        count++;
        for (Integer vertex : g.adj(s)) {
            if (marked[vertex]) continue;
            reuslt.add(vertex);
            dfs(g, vertex, reuslt);
        }
    }


    /**
     * adj v is connected to adj s or not
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * the amount of adj which can connected to adj s
     */
    public int count() {
        return count;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("tinyG.txt");
        Graph g = new Graph(Path.of(Objects.requireNonNull(resource).toURI()));
        Iterable<Integer> integers = new DepthFirstSearch(g).search(g, 0);
        integers.forEach(System.out::println);
    }
}
