package graph;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
public interface IGraphOperation {
    /**
     * judge two adj is connected or not
     */
    boolean isConnected(int i, int j);

    /**
     * judge adj s and adj i  is connected or not
     */
    boolean isConnected(int i);

    /**
     * from adj s can arrived to i or not, if can, return the through adj
     */
    Iterable shortestPath(int i);

    /**
     * the amount of adj from i can arrived adj
     */
    int edgeNum(int i);

    /**
     * minimum generate tree
     */
    Iterable minimumGenerateTree();
}
