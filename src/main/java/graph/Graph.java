package graph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
//@SuppressWarnings("all")
public class Graph {

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

    public Graph() {
    }

    @SuppressWarnings("unchecked")
    Graph(Path filePath) throws IOException {

        List<String> lines = Files.lines(filePath).map(String::valueOf).collect(Collectors.toList());

        this.v = Integer.parseInt(lines.get(0));
        this.e = Integer.parseInt(lines.get(1));

        this.adj = new Stack[this.v];
        for (int j = 0; j < this.v; j++)
            this.adj[j] = new Stack();

        lines.stream().skip(2).forEach(e -> {
            String[] i = e.split(" ");
            int from = Integer.parseInt(i[0]);
            int to = Integer.parseInt(i[1]);
            // this is undirected graph
            this.adj[from].push(to);
            this.adj[to].push(from);
        });
    }

    public int E() {
        return e;
    }

    public int V() {
        return v;
    }

    void addEdge(int v, int w) {
        this.adj[v].push(w);
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
            while (!this.adj(i).isEmpty())
                s.append(adj(i).pop()).append(" ");
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("tinyG.txt");
        assert resource != null;
        Graph f = new Graph(Path.of(resource.toURI()));
        System.out.println(f);
    }
}
