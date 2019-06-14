package graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Stream;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
@SuppressWarnings("unchecked")
public class Graph {
    /**
     * only have one variable, record every vertex, like hashmap solve conflict use chain
     */
    private Vector[] vertex;

    public Graph() {
    }

    public Graph(Path filePath) throws IOException {

        Files.lines(filePath).forEachOrdered(e -> {
            String[] s = e.split(" ");
            if (s.length == 1) {
                this.vertex = new Vector[Integer.valueOf(s[0])];
                for (int i = 0; i < 10; i++) {
                    this.vertex[i] = new Vector();
                }
            } else {
                int from = Integer.valueOf(s[0]);
                int to = Integer.valueOf(s[1]);
                assert this.vertex != null;
                this.vertex[from].add(to);
            }

        });
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertex=" + Arrays.toString(vertex) +
                '}';
    }

    public static void main(String[] args) throws IOException {
        String s = "C:\\Users\\xiaolu\\Desktop\\New Text Document.txt";
        Graph f = new Graph(Path.of(s));
        System.out.println(f);
    }
}
