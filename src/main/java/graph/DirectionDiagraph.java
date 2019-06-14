package graph;

import com.google.common.collect.Lists;

import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
public class DirectionDiagraph {

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

    /**
     * judge two vertex is connected or not
     */
    public boolean isConnected(int i, int j) {
        return false;
    }

    /**
     * judge vertex s and vertex i  is connected or not
     */
    public boolean isConnected(int i) {
        return false;
    }

    /**
     * from vertex s can arrived to i or not, if can, return the through vertex
     */
    public Iterable shortestPath(int i) {
        return Lists.newArrayList();
    }

    /**
     * the amount of vertex from i can arrived vertex
     */
    public int edgeNum(int i) {
        return -1;
    }


}
