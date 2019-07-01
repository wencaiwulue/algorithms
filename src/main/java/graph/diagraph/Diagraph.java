package graph.diagraph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author fengcaiwen
 * @since 6/16/2019 16:26
 */
//@SuppressWarnings("all")
public class Diagraph {

    /**
     * adj number
     */
    private int v;
    /**
     * edge number
     */
    private int e;
    /**
     * record every adj, like hashmap solve conflict use chain
     */
    private Stack<Integer>[] adj;

    private Diagraph(int v) {
        this.v = v;
    }

    @SuppressWarnings("unchecked")
    Diagraph(Path filePath) throws IOException {

        List<String> lines = Files.lines(filePath).map(String::valueOf).collect(Collectors.toList());

        this.v = Integer.valueOf(lines.get(0));
        this.e = Integer.valueOf(lines.get(1));

        this.adj = new Stack[this.v];
        for (int j = 0; j < this.v; j++) {
            this.adj[j] = new Stack<>();
        }

        lines.stream().skip(2).forEach(e -> {
            String[] i = e.split(" ");
            int v = Integer.valueOf(i[0]);
            int w = Integer.valueOf(i[1]);
            // this is directed graph
            this.adj[v].push(w);
        });
    }

    public int E() {
        return e;
    }

    public int V() {
        return v;
    }

    private void addEdge(int v, int w) {
        this.adj[v].push(w);
        e++;
    }

    Stack<Integer> adj(int v) {
        return this.adj[v];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(v + " vertices, " + e + " edges\n");
        for (int i = 0; i < v; i++) {
            s.append(i).append(": ");
//            for (int w : this.adj(i)) {
//                s += w + " ";
//            }
            while (!this.adj(i).isEmpty()) {
                s.append(adj(i).pop()).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public Diagraph reverse() {
        Diagraph g = new Diagraph(this.v);
        for (int v = 0; v < this.v; v++) {
            for (Integer w : this.adj[this.v]) {
                g.addEdge(w, v);
            }
        }
        return g;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("tinyDG.txt");
        Diagraph f = new Diagraph(Path.of(Objects.requireNonNull(resource).toURI()));
        System.out.println(f);
    }
}

