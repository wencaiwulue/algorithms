package graph;

import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
public class DirectionDiagraph implements IGraphOperation {

    /**
     * element i means adj i, adj[i] is all adj i can arrived
     */
    private int s;
    /**
     * adj number
     */
    private int v;

    /**
     * edge number
     */
    private int e;
    /**
     * the last adj to arrived index i
     */
    private Stack<Edge>[] vertex;

    /**
     * marked or not
     */
    private boolean[] marked;

    /**
     * initial a direction diagraph with graph and start adj
     */
    @SuppressWarnings("unchecked")
    public DirectionDiagraph(Graph g, int s) {
        this.s = s;
        this.v = g.V();
        // initial array
        this.vertex = new Stack[v];
        for (int j = 0; j < v; j++) {
            this.vertex[j] = new Stack<>();
        }


    }


    @Override
    public boolean isConnected(int i, int j) {
        return false;
    }

    @Override
    public boolean isConnected(int i) {
        return false;
    }

    @Override
    public Iterable shortestPath(int i) {
        return null;
    }

    @Override
    public int edgeNum(int i) {
        return 0;
    }

    @Override
    public Iterable minimumGenerateTree() {
        return null;
    }
}
