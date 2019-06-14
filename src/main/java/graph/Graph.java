package graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
@SuppressWarnings("all")
public class Graph {
    /**
     * only have one variable, record every vertex, like hashmap solve conflict use chain
     */
    int v;
    int e;
    public Vector[] vertex;

    public Graph() {
    }

    public Graph(Path filePath) throws IOException {

        List<String> lines = Files.lines(filePath).map(String::valueOf).collect(Collectors.toList());

        this.v = Integer.valueOf(lines.get(0));
        this.e = Integer.valueOf(lines.get(1));

        this.vertex = new Vector[this.v];
        for (int j = 0; j < 10; j++)
            this.vertex[j] = new Vector();

        lines.stream().skip(2).forEach(e -> {
            {
                String[] s = e.split(" ");
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
