package graph;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
public interface IGraphOperation {
    /**
     * judge two vertex is connected or not
     */
    boolean isConnected(int i, int j);

    /**
     * judge vertex s and vertex i  is connected or not
     */
    boolean isConnected(int i);

    /**
     * from vertex s can arrived to i or not, if can, return the through vertex
     */
    Iterable shortestPath(int i);

    /**
     * the amount of vertex from i can arrived vertex
     */
    int edgeNum(int i);

    /**
     * minimum generate tree
     */
    Iterable minimumGenerateTree();
}
