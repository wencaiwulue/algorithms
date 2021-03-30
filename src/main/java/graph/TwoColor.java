package graph;


/**
 * @author fengcaiwen
 * @since 6/15/2019 17:50
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable;


    public TwoColor(Graph g) {
        this.marked = new boolean[g.V()];
        this.color = new boolean[g.V()];
        this.isTwoColorable = true;
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[v] == color[w]) {
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
