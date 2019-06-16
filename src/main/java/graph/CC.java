package graph;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author fengcaiwen
 * @since 6/15/2019 16:37
 */
//@SuppressWarnings("all")
public class CC {

    /**
     * visited it or not
     */
    private boolean[] marked;

    /**
     * the flag of cc
     */
    private int[] id;

    /**
     * how much flag
     */
    private int count;

    private CC(Graph g) {
        id = new int[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (marked[v]) continue;
            dfs(g, v);
            // if dfs done, means it reach the end, otherwise it will run forever until visit all vertex
            // reach end means already reverse all sub tree node. so it's time to add one
            count++;
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }


    /**
     * vertex v and w is connected or not
     */
    private boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * the number of cc
     */
    int count() {
        return count;
    }

    /**
     * the flag of cc (0~count()-1)
     */
    int id(int v) {
        return id[v];
    }

    public static void main(String[] args) throws IOException {
        String s = "C:\\Users\\Fcw\\Documents\\javaee\\src\\main\\java\\graph\\tinyG.txt";
        Graph f = new Graph(Path.of(s));
        CC c = new CC(f);
        System.out.println(c.connected(8, 9));
        System.out.println(c.connected(1, 9));
        System.out.println(c.connected(7, 8));
    }
}
