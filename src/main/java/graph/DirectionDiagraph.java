package graph;

import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
public class DirectionDiagraph implements IGraphOperation {

    /**
     * element i means vertex i, vertex[i] is all vertex i can arrived
     */
    Stack<Edge>[] vertex;
    Boolean[] marked;

    /**
     * initial a direction diagraph with graph and start vertex
     */
    public DirectionDiagraph(Graph graph, int s) {
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
