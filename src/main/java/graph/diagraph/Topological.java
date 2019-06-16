package graph.diagraph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 6/16/2019 19:12
 */
public class Topological {
    private boolean[] marked;
    private Stack<Integer> reverserPost;

    private Topological(Diagraph g) {
        marked = new boolean[g.V()];
        reverserPost = new Stack<>();
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Diagraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        reverserPost.add(v);
    }

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Fcw\\Documents\\javaee\\src\\main\\java\\graph\\diagraph\\DiagraphTest.txt";
        Diagraph g = new Diagraph(Path.of(filePath));
        Topological dc = new Topological(g);
        while (!dc.reverserPost.isEmpty()) {
            System.out.println(dc.reverserPost.pop());
        }
    }
}
