package graph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 2019年6月15日
 */
@SuppressWarnings("all")
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph g) {
        this.marked = new boolean[g.V()];
    }

    /**
     * find all adj which connected to s
     */
    public Iterable<Integer> search(Graph g, int s) {
        //1, find all adj from s, and foreach
        //2, bfs and push into stack or enqueue
        List<Integer> result = new ArrayList<>();
        dfs(g, s, result);
        return result;
    }

    public void dfs(Graph g, int s, List<Integer> reuslt) {
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

    public static void main(String[] args) throws IOException {
        String s = "C:\\Users\\Fcw\\Documents\\javaee\\src\\main\\java\\graph\\tinyG.txt";
        Graph g = new Graph(Path.of(s));
        Iterable<Integer> integers = new DepthFirstSearch(g).search(g, 0);
        integers.forEach(e -> System.out.println(e));
    }
}
