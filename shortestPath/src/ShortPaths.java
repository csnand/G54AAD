import java.io.*;
import java.util.Collections;


public class ShortPaths {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java ShortPath pathToTestFile");
            return;
        }

        ShortPaths shortPaths = new ShortPaths();
        String preParsedGraph = shortPaths.readFile(args[0]);
        Graph g = new Graph(preParsedGraph);
        shortPaths.runDijkstra(g);
    }

    public void runDijkstra(Graph graph){
        System.out.println("Dijkstra's Algorithm");
        System.out.println("Shortest Paths from source: ");

        Vertex origin = graph.getOrigin();
        Collections.sort(graph.getAllVertices(), new Vertex.VertexComparator());

        for (Vertex v : graph.getAllVertices()){
            Dijkstra d = new Dijkstra(origin, v, graph);
            d.shortestPath();
        }

    }



    // this function will read test file, convert graph from tuples to
    // INT INT DOUBLE format and return the result
    public String readFile(String path) {

        String testGraph = "";
        try  {
            BufferedReader bf = new BufferedReader(new FileReader(path));

            String s = "";
            while ((s = bf.readLine()) != null){
                testGraph += s;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testGraph.replace(")(", "\n").replace("(", "").replace(")", "").replace(",", " ");
    }
}
