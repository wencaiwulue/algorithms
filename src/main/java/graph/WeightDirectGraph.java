package graph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
public class WeightDirectGraph {

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
    private Stack<Edge>[] adj;

    public WeightDirectGraph() {
    }

    public WeightDirectGraph(Path filePath) throws IOException {

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
            int weight = Integer.parseInt(i[2]);
            this.adj[to].push(new Edge(to, from, weight));
        });
    }

    public int E() {
        return e;
    }

    public int V() {
        return v;
    }

    public void addEdge(int v, int w, int weight) {
        this.adj[v].push(new Edge(v, w, weight));
    }

    public Stack<Integer> adj(int v) {
        return this.adj[v].stream().map(Edge::to).collect(Collectors.toCollection(Stack::new));
    }

    public Stack<Edge> edges(int v) {
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
        WeightDirectGraph f = new WeightDirectGraph(Path.of(resource.toURI()));
        System.out.println(f);
    }
}
