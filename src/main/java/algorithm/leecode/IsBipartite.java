package algorithm.leecode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IsBipartite {
    @Test
    public void test() {
        int[][] adj = new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
//        adj = new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        boolean bipartite = isBipartite(adj);
        System.out.println(bipartite);
    }

    public static boolean isBipartite(int[][] graph) {
        Graph g = new Graph(graph);
        for (int v = 0; v < g.vertex; v++) {
            if (!g.isMarked(v)) {
                dfs(g, v);
            }
            g.marked[v] = true;
        }
        return g.isBipartite;
    }

    public static void dfs(Graph g, int v) {
        g.marked[v] = true;
        for (Integer e : g.getAdjacentEdge(v)) {
            if (!g.isMarked(e)) {
                g.setColor(e, !g.colored[v]);
                dfs(g, e);
            }
            if (g.isSameColor(v, e)) {
                g.isBipartite = false;
            }
        }
    }

    static class Graph {
        private int vertex;
        private List<Integer>[] adjacentEdge;
        private boolean[] marked;
        private boolean[] colored;// true are blue, false are red
        private boolean isBipartite;

        public Graph(int[][] edges) {
            this.isBipartite = true;
            this.vertex = edges.length;
            this.marked = new boolean[this.vertex];
            this.colored = new boolean[this.vertex];
            this.adjacentEdge = new List[edges.length];
            for (int i = 0; i < edges.length; i++) {
                this.adjacentEdge[i] = new ArrayList<>();
            }
            for (int i = 0; i < edges.length; i++) {
                for (int e : edges[i]) {
                    this.adjacentEdge[i].add(e);
                    this.adjacentEdge[e].add(i);
                }
            }
        }

        public boolean isMarked(int v) {
            return marked[v];
        }

        public boolean isSameColor(int v, int w) {
            return colored[v] == colored[w];
        }

        public int getVertex() {
            return vertex;
        }

        public List<Integer> getAdjacentEdge(int vertex) {
            return adjacentEdge[vertex];
        }

        public boolean isBipartite() {
            return isBipartite;
        }

        public void setColor(int v, boolean color) {
            this.colored[v] = color;
        }
    }
}
