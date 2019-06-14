package graph;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
@SuppressWarnings("all")
public class Edge {
    int from;
    int to;
    int weight;

    public Edge() {
    }

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
